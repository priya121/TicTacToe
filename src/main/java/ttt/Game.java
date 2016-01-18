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

import static ttt.Symbol.CROSS;
import static ttt.Symbol.NOUGHT;
import static ttt.Symbol.EMPTY;

public class Game {
    private Board board;
    private Symbol currentSymbol;
    private IO io;
    private Player currentPlayer;
    private Player computerPlayer;
    private Player humanPlayer;

    public Game(Board board, IO io, ComputerPlayer computerPlayer, HumanPlayer humanPlayer) {
        this.board = board;
        this.io = io;
        this.computerPlayer = computerPlayer;
        this.humanPlayer = humanPlayer;
        this.currentPlayer = humanPlayer;
        this.currentSymbol = currentPlayer.getSymbol();
    }

    public void gameLoop() {
        io.showOutput("You are the cross symbol (Enter a position from 0 - 8:)");
        while (board.gameNotOver()) {
            showCurrentBoard();
            getCurrentPlayer(board);
            board.markPlayer(currentPlayer.move(), currentPlayer.getSymbol());
        }
        endOfGameDisplay();
    }

    public void getCurrentPlayer(Board board) {
        List<Integer> emptyCells = new ArrayList<>();
        IntStream.range(0, 9)
            .filter(cell -> board.get(cell).equals(EMPTY))
            .forEach(i -> emptyCells.add(i));

        switchPlayers(emptyCells.size());
    }

    private void switchPlayers(int emptyCells) {
        if ((emptyCells % 2) == 0 ) {
            currentPlayer = computerPlayer;
        } else {
            currentPlayer = humanPlayer;
        }
    }

    public void showCurrentBoard() {
        DisplayBoard currentDisplay = new DisplayBoard(board.getCurrentBoard());
        io.showOutput(currentDisplay.showBoard());
    }

    private void endOfGameDisplay() {
        showCurrentBoard();
        if (board.isWin()) {
            io.showOutput("player " + currentSymbol + " has won!");
        }
        io.showOutput("game over");
    }
}
