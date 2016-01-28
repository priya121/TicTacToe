package ttt.board;

import ttt.inputOutput.IO;

public class BoardChooser {
    private IO io;

    public BoardChooser(IO io) {
        this.io = io;
    }

    public Board create() {
        io.showOutput("Please enter the size of the board you would like to play on (e.g. 3 for 3x3 or 4 for 4x4): ");
        return new Board(Integer.parseInt(io.takeInput()));
    }
}
