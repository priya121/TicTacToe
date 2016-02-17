package ttt.player;

import ttt.Symbol;
import ttt.board.Board;
import ttt.inputOutput.DigitValidator;
import ttt.inputOutput.IO;

public class HumanPlayer implements Player {
    private DigitValidator digitValidator;
    private IO io;
    private Symbol symbol;

    public HumanPlayer(IO io, Symbol symbol) {
        this.io = io;
        this.symbol = symbol;
        this.digitValidator = new DigitValidator();
    }

    public String takesUserInput() {
        return io.takeInput();
    }

    private int validMove(Board board) {
        String userInput = takesUserInput();
        while (!digitValidator.check(userInput) || !board.isPositionEmpty(userInput)) {
            io.showOutput("That position is already taken or is not on the board, try again.");
            userInput = takesUserInput();
        }
        return Integer.parseInt(userInput);
    }

    public int move(Board board) {
        return validMove(board);
    }

    public Symbol playerSymbol() {
        return symbol;
    }
}
