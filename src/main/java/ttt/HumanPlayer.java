package ttt;

public class HumanPlayer implements Player{
    Symbol cross = Symbol.CROSS;
    Symbol symbol = cross;
    IO io;
    Board board;
    int indexChosen = 0;

    public HumanPlayer(IO io, Board board) {
        this.io = io;
        this.board = board;
    }

    public String takesUserInput() {
        return io.takeInput();
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

    public int move() {
        int indexChosen = checkDigitInput();
        indexChosen = isValidMove(indexChosen);
        return indexChosen;
    }

    public Symbol getSymbol() {
        return cross;
    }
}
