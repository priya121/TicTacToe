package ttt;

import ttt.board.Board;
import ttt.board.BoardDisplay;
import ttt.inputOutput.IO;
import ttt.observers.DateTimeObserver;
import ttt.observers.MoveObserver;
import ttt.observers.Observer;
import ttt.observers.PlayerObserver;
import ttt.player.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static ttt.Symbol.E;

public class Game {
    public Board board;
    IO io;
    Player currentPlayer;
    Player playerOne;
    Player playerTwo;
    File file;
    List<Observer> observers = new ArrayList<>();
    int move;

    public Game(Board board, IO io, Player playerOne, Player playerTwo, File file) {
        this.board = board;
        this.io = io;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;
        this.file = file;
        createObservers(file);
    }

    private void createObservers(File file) {
        new PlayerObserver(this, file);
        new MoveObserver(this, file);
        new DateTimeObserver(this, file);
    }

    public void gameLoop() {
        while (board.gameNotOver()) {
            io.showOutput("Enter a position from 0 - " + (board.contentsOfBoard().size() - 1));
            showCurrentBoard();
            computeCurrentPlayer(board);
            move = currentPlayer.move(board);
            board = board.markPlayer(move, currentPlayer.playerSymbol());
            notifyObservers();
        }
        endOfGameDisplay();
    }

    public Symbol getCurrentPlayer() {
        return currentPlayer.playerSymbol();
    }

    public int getMove() {
        return move;
    }

    public Date getTime() {
        Date date = Calendar.getInstance().getTime();
        return date;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public List<Symbol> getBoard() {
        return board.contentsOfBoard();
    }

    public void computeCurrentPlayer(Board board) {
        List<Integer> emptyCells = new ArrayList<>();
        IntStream.range(0, board.contentsOfBoard().size())
                .filter(cell -> board.get(cell).equals(E))
                .forEach(i -> emptyCells.add(i));
        switchPlayers(emptyCells.size());
    }

    private void switchPlayers(int emptyCells) {
        boardOddNumberOfCells(emptyCells);
        boardEvenNumberOfCells(emptyCells);
    }

    private void boardEvenNumberOfCells(int emptyCells) {
        if (board.contentsOfBoard().size() % 2 == 0) {
            if (emptyCells % 2 == 0) currentPlayer = playerOne;
            else currentPlayer = playerTwo;
        }
    }

    private void boardOddNumberOfCells(int emptyCells) {
        if (board.contentsOfBoard().size() % 2 != 0) {
            if (emptyCells % 2 == 0) currentPlayer = playerTwo;
            else currentPlayer = playerOne;
        }
    }

    public void showCurrentBoard() {
        BoardDisplay currentDisplay = new BoardDisplay(board.contentsOfBoard());
        io.showOutput(currentDisplay.showBoard());
    }


    private void endOfGameDisplay() {
        showCurrentBoard();
        if (board.isWin()) {
            io.showOutput("Player " + currentPlayer.playerSymbol() + " has won!");
        }
    }

    public Symbol getPlayerOne() {
        return currentPlayer.playerSymbol();
    }

}
