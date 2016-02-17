package ttt.board;

import ttt.inputOutput.IO;
import ttt.inputOutput.InputValidator;

public class BoardCreator {
    private InputValidator inputValidator;
    private IO io;
    private Board board;
    private String userInput;

    public BoardCreator(IO io) {
        this.io = io;
        this.board = new Board(3);
        this.inputValidator = new InputValidator(io);
    }

    public String takeUserSizeChosen() {
        userInput = io.takeInput();
        return userInput;
    }

    public Board create() {
        io.showOutput("Please enter the size of the board you would like to play on (e.g. 3 for 3x3 or 4 for 4x4): ");
        userInput = takeUserSizeChosen();
            while (!inputValidator.checksDigit(userInput)) {
                takeUserSizeChosen();
            }
            return new Board(Integer.parseInt(userInput));
    }
}
