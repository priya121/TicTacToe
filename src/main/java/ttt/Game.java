package ttt;

import ttt.board.Board;
import ttt.board.DisplayBoard;
import ttt.inputOutput.IO;
import ttt.player.ComputerPlayer;
import ttt.player.HumanPlayer;
import ttt.player.Player;

import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;

import static ttt.Symbol.E;

public class Game {
    private Board board;
    private IO io;
    private Player currentPlayer;
    private ComputerPlayer computerPlayer;
    private HumanPlayer humanPlayer;

    public Game(Board board, IO io, ComputerPlayer computerPlayer, HumanPlayer humanPlayer) {
        this.board = board;
        this.io = io;
        this.computerPlayer = computerPlayer;
        this.humanPlayer = humanPlayer;
        this.currentPlayer = humanPlayer;
    }

    public void gameLoop() {
        while (board.gameNotOver()) {
            io.showOutput("Enter a position from 0 - " + (board.contentsOfBoard().size() - 1));
            showCurrentBoard();
            computeCurrentPlayer(board);
            board.markPlayer(currentPlayer.move(), currentPlayer.getSymbol());
        }
        endOfGameDisplay();
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
            if (emptyCells % 2 == 0) currentPlayer = humanPlayer;
            else currentPlayer = computerPlayer;
        }
    }

    private void boardOddNumberOfCells(int emptyCells) {
        if (board.contentsOfBoard().size() % 2 != 0) {
            if (emptyCells % 2 == 0) currentPlayer = computerPlayer;
            else currentPlayer = humanPlayer;
        }
    }

    public void showCurrentBoard() {
        DisplayBoard currentDisplay = new DisplayBoard(board.contentsOfBoard());
        io.showOutput(currentDisplay.showBoard());
    }

    private void endOfGameDisplay() {
        showCurrentBoard();
        if (board.isWin()) {
            io.showOutput("player " + currentPlayer.getSymbol() + " has won!");
        }
        io.showOutput("game over");
    }

    public Symbol getPlayer() {
        return currentPlayer.getSymbol();
    }
}
