package ttt.player;

import ttt.Symbol;
import ttt.inputOutput.IO;

import static ttt.Symbol.O;
import static ttt.Symbol.X;

public class SymbolChooser {
    private IO io;

    public SymbolChooser(IO io) {
        this.io = io;
    }

    public Symbol getSymbol() {
        if (io.takeInput().equals("X")) {
            return X;
        } else {
            return O;
        }
    }
}
