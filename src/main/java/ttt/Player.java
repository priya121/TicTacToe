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
        int indexChosen = Integer.parseInt(takesUserInput());
        board.markPlayer(indexChosen, symbol);
        symbolJustPlayed = symbol;
        switchPlayers();
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
