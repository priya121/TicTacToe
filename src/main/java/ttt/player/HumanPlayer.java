package ttt.player;

import ttt.Symbol;
import ttt.board.Board;
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
        while (!digitInput(userInput) || !board.isPositionEmpty(indexChosen)) {
            io.showOutput("That position is already taken or is not on the board, try again.");
            takesUserInput();
        }
        return indexChosen;
    }

    public boolean digitInput(String userInput) {
        boolean digitInput = false;
        while (!digitInput) {
            try {
                indexChosen = Integer.parseInt(userInput);
                digitInput = true;
            } catch (Exception e) {
                io.showOutput("Please enter a valid number");
                userInput = takesUserInput();
            }
        }
        return digitInput;
    }

    public int move(Board board) {
        takesUserInput();
        return validMove(board);
    }

    public Symbol playerSymbol() {
        return symbol;
    }
}
