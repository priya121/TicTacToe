package ttt;

import java.util.Arrays;
import java.util.List;

public class Game {
    private int counter = 0;
    private List<String> emptyBoard;
    private Board board;
    private DisplayBoard display;
    private Player player;
    private String cross;
    private String nought;
    IO io;

    public enum Status {
        CROSS("X"),
        NOUGHT("O");

        private String symbol;

        Status(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    public Game(Board board, IO io) {
        String empty = "-";
        emptyBoard = Arrays.asList(empty, empty, empty,
                                   empty, empty, empty,
                                   empty, empty, empty);
        cross = Status.CROSS.getSymbol();
        nought = Status.NOUGHT.getSymbol();
        this.board = board;
        this.io = io;
        this.player = new Player(io, board);
    }

    public void gameLoop() {
        while (!checkForWin() && counter < 9) {
            io.showOutput("Player as symbol " + player.getSymbol() + " make your move:");
            player.markBoard();
            DisplayBoard currentDisplay = new DisplayBoard(board.getCurrentBoard());
            counter += 1;
            io.showOutput(currentDisplay.showBoard());
        }
        io.showOutput("Player " + player.getSymbol() + " has won!");
        io.showOutput("game over");
    }

    public boolean checkForWin() {
        if (isWinningRow(cross) || isWinningDiagonal(cross) || isWinningColumn(cross))  {
            return true;
        }
        if (isWinningRow(nought) || isWinningDiagonal(nought) || isWinningColumn(nought)) {
            return true;
        }
        return false;
    }

    public boolean isWinningRow(String symbol) {
        if (board.get(0).equals(symbol) && board.get(1).equals(symbol) && board.get(2).equals(symbol)) {
            return true;
        }
        if (board.get(3).equals(symbol) && board.get(4).equals(symbol) && board.get(5).equals(symbol)) {
            return true;
        }
        if (board.get(6).equals(symbol) && board.get(7).equals(symbol) && board.get(8).equals(symbol)) {
            return true;
        }
        return false;
    }

    public boolean isWinningColumn(String symbol) {
        if (board.get(0).equals(symbol) && board.get(3).equals(symbol) && board.get(6).equals(symbol)) {
            return true;
        }
        if (board.get(1).equals(symbol) && board.get(4).equals(symbol) && board.get(7).equals(symbol)) {
            return true;
        }
        if (board.get(2).equals(symbol) && board.get(5).equals(symbol) && board.get(8).equals(symbol)) {
            return true;
        }
        return false;
    }


    public boolean isWinningDiagonal(String symbol) {
        if (board.get(0).equals(symbol) && board.get(4).equals(symbol) && board.get(8).equals(symbol)) {
            return true;
        }
        if (board.get(2).equals(symbol) && board.get(4).equals(symbol) && board.get(6).equals(symbol)) {
            return true;
        }
        return false;
    }
}
