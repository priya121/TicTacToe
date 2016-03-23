package ttt;

import ttt.board.Board;
import ttt.board.BoardDisplay;
import ttt.inputOutput.IO;
import ttt.observers.MoveObserver;
import ttt.player.Player;

import java.io.File;
import java.util.*;
import java.util.stream.IntStream;

import static ttt.Symbol.E;

public class Game extends Observable implements Runnable {
    public Board board;
    IO io;
    public Player currentPlayer;
    Player playerOne;
    Player playerTwo;
    File file;
    public int move;
    public Date date;
    public MoveObserver moveObserver;

    public Game(Board board, IO io, Player playerOne, Player playerTwo, File file) {
        this.board = board;
        this.io = io;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;
        this.file = file;
        createObserver(file);
    }

    private void createObserver(File file) {
        moveObserver = new MoveObserver(this, file);
    }

    public void gameLoop() {
        while (board.gameNotOver()) {
            io.showOutput("Enter a position from 0 - " + (board.contentsOfBoard().size() - 1));
            showCurrentBoard();
            computeCurrentPlayer(board);
            move = currentPlayer.move(board);
            board = board.markPlayer(move, currentPlayer.playerSymbol());
            date = Calendar.getInstance().getTime();
            setChanged();
            notifyObservers(this);
        }
        endOfGameDisplay();
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

    @Override
    public void run() {
        gameLoop();
    }
}
