package ttt;
import ttt.board.Board;
import ttt.board.DisplayBoard;
import ttt.inputOutput.IO;
import ttt.player.ComputerPlayer;
import ttt.player.HumanPlayer;
import ttt.player.Player;

import static ttt.Symbol.CROSS;
import static ttt.Symbol.NOUGHT;

public class Game {
    private Board board;
    private HumanPlayer player;
    private Symbol currentSymbol;
    private IO io;
    private ComputerPlayer computerFakeMoves;

    public Game(Board board, IO io, ComputerPlayer computerFakeMoves) {
        this.currentSymbol = CROSS;
        this.board = board;
        this.io = io;
        this.computerFakeMoves = computerFakeMoves;
        this.player = new HumanPlayer(io, board);
    }

    public void gameLoop() {
        io.showOutput("You are the cross symbol (Enter a position from 0 - 8:)");
        while (board.gameNotOver()) {
            showCurrentBoard();
            markBoard();
        }
        endOfGameDisplay();
    }

    public void markBoard() {
        playerMove();
        computerMove();
    }

    private void playerMove() {
        if (board.gameNotOver()) {
            int playerMove = player.move();
            board.markPlayer(playerMove, CROSS);
        }
    }

    private void computerMove() {
        if (board.gameNotOver()) {
            int computerPlayerMove = computerFakeMoves.move();
            board.markPlayer(computerPlayerMove, NOUGHT);
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
