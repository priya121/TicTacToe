package ttt;

import ttt.board.Board;
import ttt.board.BoardChooser;
import ttt.board.DisplayBoard;
import ttt.inputOutput.IO;
import ttt.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static ttt.Symbol.E;

public class Game {
    private Board board;
    private IO io;
    private Player currentPlayer;
    private Player playerOne;
    private Player playerTwo;

    public Game(Board board, IO io, Player playerOne, Player playerTwo) {
        this.board = board;
        this.io = io;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;
    }

    public void gameLoop() {
        setFirstPlayerSymbol();
        while (board.gameNotOver()) {
            io.showOutput("Enter a position from 0 - " + (board.contentsOfBoard().size() - 1));
            showCurrentBoard();
            computeCurrentPlayer(board);
            board.markPlayer(currentPlayer.move(), currentPlayer.calculateOwnSymbol());
        }
        endOfGameDisplay();
    }

    private void setFirstPlayerSymbol() {
        io.showOutput("Which symbol would you like player one to be? ");
        playerOne.setPlayerSymbol();
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
        DisplayBoard currentDisplay = new DisplayBoard(board.contentsOfBoard());
        io.showOutput(currentDisplay.showBoard());
    }

    private void endOfGameDisplay() {
        showCurrentBoard();
        if (board.isWin()) {
            io.showOutput("playerOne " + currentPlayer.calculateOwnSymbol() + " has won!");
        }
        io.showOutput("game over");
        replayGameOption();
    }

    private void replayGameOption() {
        io.showOutput("Would you like to play again? Y/N");
        String replayChosen = io.takeInput();
        if (replayChosen.equals("Y")) {
            board = new BoardChooser(io).create();
            gameLoop();
        }
            io.showOutput("Goodbye!");
    }

    public Symbol getPlayerOne() {
        return currentPlayer.calculateOwnSymbol();
    }
}
