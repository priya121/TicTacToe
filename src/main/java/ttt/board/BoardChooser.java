package ttt.board;

import ttt.inputOutput.IO;

public class BoardChooser {
    private IO io;


    public BoardChooser(IO io) {
        this.io = io;
    }

    public Board create() {
        return new Board(Integer.parseInt(io.takeInput()));
    }
}
