package ttt;
import ttt.board.Board;
import ttt.board.DisplayBoard;
import ttt.inputOutput.IO;
import ttt.player.ComputerPlayer;
import ttt.player.HumanPlayer;
import ttt.player.Player;

import java.util.ArrayList;
import java.util.List;

import static ttt.Symbol.CROSS;
import static ttt.Symbol.NOUGHT;

public class Game {
    private Board board;
    private Symbol currentSymbol;
    private IO io;
    private Player player;
    private List<Player> players = new ArrayList<>();
    private Player computerPlayer;

    public Game(Board board, IO io, ComputerPlayer computerPlayer) {
        this.board = board;
        this.io = io;
        this.computerPlayer = computerPlayer;
        this.player = new HumanPlayer(io, board);
        this.currentSymbol = player.getSymbol();
        players.add(player);
        players.add(computerPlayer);
    }

    public void gameLoop() {
        io.showOutput("You are the cross symbol (Enter a position from 0 - 8:)");
        int currentPlayer = 0;
        while (board.gameNotOver()) {
            showCurrentBoard();
            board.markPlayer(players.get(currentPlayer).move(), currentSymbol);
            currentPlayer = switchPlayers(currentPlayer);
            switchSymbol();
        }
        endOfGameDisplay();
    }

    public int switchPlayers(int currentPlayer) {
        return currentPlayer =  (currentPlayer == 0) ? 1 : 0;
    }

    public Symbol switchSymbol() {
        return currentSymbol = currentSymbol.equals(CROSS) ? NOUGHT : CROSS;
    }

    public void showCurrentBoard() {
        DisplayBoard currentDisplay = new DisplayBoard(board.getCurrentBoard());
        io.showOutput(currentDisplay.showBoard());
    }

    private void endOfGameDisplay() {
        showCurrentBoard();
        switchSymbol();
        if (board.isWin()) {
            io.showOutput("player " + currentSymbol + " has won!");
        }
        io.showOutput("game over");
    }
}
