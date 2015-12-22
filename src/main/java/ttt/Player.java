package ttt;

public class Player {
    Symbol cross = Symbol.CROSS;
    Symbol nought = Symbol.NOUGHT;
    Symbol symbol = cross;
    String symbolJustPlayed = "";
    IO io;
    Board board;

    public Player(IO io, Board board) {
        this.symbol = cross;
        this.io = io;
        this.board = board;
        this.symbolJustPlayed = cross.getSymbol();
    }

    public String takesUserInput() {
        return io.takeInput();
    }

    public void markBoard() {
        int indexChosen = checkDigitInput();
        while (!board.isValid(indexChosen)) {
            io.showOutput("That position is already taken, try again.");
            indexChosen = Integer.parseInt(takesUserInput());
        }
        board.markPlayer(indexChosen, symbol);
        symbolJustPlayed = symbol.getSymbol();
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
        symbol = symbol.equals(cross) ? nought : cross;
    }

    public String getSymbol() {
        return symbolJustPlayed;
    }
}
