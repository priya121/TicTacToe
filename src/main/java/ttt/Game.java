package ttt;

import ttt.board.Board;
import ttt.board.DisplayBoard;
import ttt.inputOutput.IO;
import ttt.player.ComputerPlayer;
import ttt.player.HumanPlayer;

public class Game {
    private Board board;
    private HumanPlayer player;
    private Symbol cross;
    private Symbol nought;
    private Symbol symbol;
    private IO io;
    private ComputerPlayer computerFakeMoves;

    public Game(Board board, IO io, ComputerPlayer computerFakeMoves) {
        cross = Symbol.CROSS;
        nought = Symbol.NOUGHT;
        this.symbol = cross;
        this.board = board;
        this.io = io;
        this.computerFakeMoves = computerFakeMoves;
        this.player = new HumanPlayer(io, board);
    }

    public void gameLoop() {
        while (board.gameNotOver()) {
            showCurrentBoard();
            io.showOutput("Player " + symbol + " Make your move:");
            markBoard();
        }
        endOfGameDisplay();
    }

    public void markBoard() {
        if (board.gameNotOver()) {
            int playerMove = player.move();
            board.markPlayer(playerMove, cross);
        }

        if (board.gameNotOver()) {
            int computerPlayerMove = computerFakeMoves.move();
            board.markPlayer(computerPlayerMove, nought);
            symbol = nought;
            switchPlayerSymbol();
        }
    }

    public void showCurrentBoard() {
        DisplayBoard currentDisplay = new DisplayBoard(board.getCurrentBoard());
        io.showOutput(currentDisplay.showBoard());
    }

    public Symbol switchPlayerSymbol() {
        symbol = symbol.equals(cross) ? nought : cross;
        return symbol;
    }

    public Symbol getNextSymbol() {
        return symbol;
    }

    public Symbol getPreviousSymbol() {
        return symbol;
    }

    private void endOfGameDisplay() {
        if (board.hasWin()) {
            showCurrentBoard();
            io.showOutput("player " + symbol + " has won!");
        }
        io.showOutput("game over");
    }
}
