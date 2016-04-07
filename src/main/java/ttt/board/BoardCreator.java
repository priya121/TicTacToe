package ttt.board;

import ttt.inputOutput.IO;
import ttt.inputOutput.DigitValidator;

public class BoardCreator {
    private DigitValidator inputValidator;
    private IO io;
    private String userInput;

    public BoardCreator(IO io) {
        this.io = io;
        this.inputValidator = new DigitValidator();
    }

    public String takeUserSizeChosen() {
        userInput = io.takeInput();
        return userInput;
    }

    public Board create() {
        io.showOutput("Please enter the size of the board you would like to play on (e.g. 3 for 3x3 or 4 for 4x4): ");
        userInput = takeUserSizeChosen();
            while (!inputValidator.check(userInput)) {
                io.showOutput("Please enter a valid number");
                takeUserSizeChosen();
            }
            return new Board(Integer.parseInt(userInput));
    }

    public int getSize() {
        return Integer.parseInt(userInput);
    }
}
