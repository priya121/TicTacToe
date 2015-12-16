package ttt;

public class Player {
    String cross = "X";
    String nought = "O";
    String symbol = cross;
    String symbolJustPlayed = "";
    IO io;
    Board board;

    public Player(IO io, Board board) {
        this.symbol = cross;
        this.io = io;
        this.board = board;
        this.symbolJustPlayed = symbol;
    }

    public String takesUserInput() {
        return io.takeInput();
    }

    public void markBoard() {
        int indexChosen = checkDigitInput();
        while (!board.isValid(indexChosen)) {
            io.showOutput("that position is already taken, try again.");
            indexChosen = Integer.parseInt(takesUserInput());
        }
        board.markPlayer(indexChosen, symbol);
        symbolJustPlayed = symbol;
        switchPlayers();
    }

    public int checkDigitInput() {
        try {
            int indexChosen = Integer.parseInt(takesUserInput());
            return indexChosen;
        } catch (Exception e) {
            io.showOutput("Please enter a number from 0-8:");
            int indexChosen = Integer.parseInt(takesUserInput());
            return indexChosen;
        }
    }

    public void switchPlayers() {
        if (symbol.equals(cross)) {
            symbol = nought;
        } else if (symbol.equals(nought)) {
            symbol = cross;
        }
    }

    public String getSymbol() {
        return symbolJustPlayed;
    }
}
