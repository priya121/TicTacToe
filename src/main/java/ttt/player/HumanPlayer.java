package ttt.player;

import ttt.Symbol;
import ttt.board.Board;
import ttt.inputOutput.DigitValidator;
import ttt.inputOutput.IO;

public class HumanPlayer implements Player {
    private IO io;
    private int indexChosen;
    private String userInput;
    private Symbol symbol;

    public HumanPlayer(IO io, Symbol symbol) {
        this.io = io;
        this.symbol = symbol;
    }

    public String takesUserInput() {
        userInput = io.takeInput();
        return userInput;
    }

    private int validMove(Board board) {
        while (!checksDigit(userInput) || !board.isPositionEmpty(userInput)) {
            io.showOutput("That position is already taken or is not on the board, try again.");
            takesUserInput();
        }
        return Integer.parseInt(userInput);
    }

    public boolean checksDigit(String userInput) {
        DigitValidator digitValidator = new DigitValidator();
        return digitValidator.check(userInput);
    }

    public int move(Board board) {
        takesUserInput();
        return validMove(board);
    }

    public Symbol playerSymbol() {
        return symbol;
    }
}
