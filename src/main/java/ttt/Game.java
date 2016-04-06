package ttt;

import ttt.board.Board;
import ttt.board.BoardDisplay;
import ttt.inputOutput.IO;
import ttt.observers.MoveObserver;
import ttt.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.IntStream;

import static ttt.Symbol.E;

public class Game extends Observable {
    public Board board;
    IO io;
    public Player currentPlayer;
    public Player playerOne;
    public Player playerTwo;
    public int move;
    public Long time;
    public MoveObserver moveObserver;
    public long startTime;

    public Game(Board board, IO io, Player playerOne, Player playerTwo) {
        this.board = board;
        this.io = io;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;
        createObserver();
    }

    private void createObserver() {
        moveObserver = new MoveObserver(this);
    }

    public void gameLoop() {
        startTime = System.currentTimeMillis();
        while (board.gameNotOver()) {
            display();
            computeCurrentPlayer(board);
            move = currentPlayer.move(board);
            board = board.markPlayer(move, currentPlayer.playerSymbol());
            time = System.currentTimeMillis();
            informObservers();
        }
        endOfGameDisplay();
    }

    private void display() {
        io.showOutput("Enter a position from 0 - " + (board.contentsOfBoard().size() - 1));
        showCurrentBoard();
    }

    private void informObservers() {
        setChanged();
        notifyObservers(this);
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
        } else {
            io.showOutput("It's a draw!");
        }
    }

    public Symbol getPlayerOne() {
        return currentPlayer.playerSymbol();
    }

}
