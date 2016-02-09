package ttt.player;

import ttt.GameType;
import ttt.Symbol;
import ttt.inputOutput.IO;

import static ttt.GameType.*;
import static ttt.Symbol.O;
import static ttt.Symbol.X;

public class PlayerCreator {
    private IO io;
    private Symbol symbol;

    public PlayerCreator(IO io) {
        this.io = io;
    }

    public Player playerOne(GameType gameType) {
        if (gameType == HvH || gameType == HvC) {
            return new HumanPlayer(io, X);
        } else {
            return new AIComputerPlayer(O);
        }
    }

    public Player playerTwo(GameType gameType) {
        if (gameType == CvH || gameType == HvH) {
            return new HumanPlayer(io, O);
        } else {
            return new AIComputerPlayer(O);
        }
    }
}


