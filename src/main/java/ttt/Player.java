package ttt;

public class Player {
    Symbol cross = Symbol.CROSS;
    Symbol nought = Symbol.NOUGHT;
    Symbol symbol = cross;
    Symbol symbolTurn;
    Symbol symbolJustPlayed;
    IO io;
    Board board;
    int indexChosen = 0;

    public Player(IO io, Board board) {
        this.symbol = cross;
        this.io = io;
        this.board = board;
        this.symbolTurn = cross;
    }

    public String takesUserInput() {
        return io.takeInput();
    }

    public void markBoard() {
        int indexChosen = checkDigitInput();
        indexChosen = isValidMove(indexChosen);
        board.markPlayer(indexChosen, symbol);
        symbolJustPlayed = symbol;
        switchPlayers();
        symbolTurn = symbol;
    }

    public int isValidMove(int indexChosen) {
        while (!board.isValid(indexChosen)) {
            io.showOutput("That position is already taken, try again.");
            indexChosen = Integer.parseInt(takesUserInput());
        }
        return indexChosen;
    }

    public int checkDigitInput() {
        boolean digitInput = false;
        while (!digitInput) {
            try {
                indexChosen = Integer.parseInt(takesUserInput());
                digitInput = true;
            } catch (Exception e) {
                io.showOutput("Please enter a number from 0-8:");
                continue;
            }
        }
        return indexChosen;
    }

    public void switchPlayers() {
        symbol = symbol.equals(cross) ? nought : cross;
    }

    public Symbol getNextSymbol() {
        return symbolTurn;
    }

    public Symbol getPreviousSymbol() {
        return symbolJustPlayed;
    }
}
