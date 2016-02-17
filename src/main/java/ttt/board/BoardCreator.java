package ttt.board;

import ttt.inputOutput.IO;

public class BoardCreator {
    private IO io;
    private Board board;
    private String userInput;

    public BoardCreator(IO io) {
        this.io = io;
        this.board = new Board(3);
    }

    public String takeUserSizeChosen() {
        userInput = io.takeInput();
        return userInput;
    }

    public Board create() {
        io.showOutput("Please enter the size of the board you would like to play on (e.g. 3 for 3x3 or 4 for 4x4): ");
        userInput = takeUserSizeChosen();
            while (!validDigit(userInput)) {
                takeUserSizeChosen();
            }
            return new Board(Integer.parseInt(userInput));
    }

    public boolean validDigit(String userInput) {
            try {
                Integer.parseInt(userInput);
                return true;
            } catch (NumberFormatException e) {
                io.showOutput("Please enter a valid number");
            }
        return false;
    }
}
