package ttt.player;

import ttt.GameType;
import ttt.Symbol;
import ttt.inputOutput.IO;

import static ttt.GameType.*;
import static ttt.Symbol.O;
import static ttt.Symbol.X;

public class PlayerCreator {
    private final IO io;
    private GameType choice;

    public PlayerCreator(IO io, String userInput) {
        this.io = io;
        this.parseGameType(userInput);
    }

    public Player createX() {
        return createPlayer(HvH, HvC, X);
    }

    public Player createO() {
        return createPlayer(CvH, HvH, O);
    }

    private Player createPlayer(GameType choiceOne, GameType choiceTwo, Symbol player) {
        if (choice == choiceOne || choice == choiceTwo) {
            return new HumanPlayer(io, player);
        } else {
            return new AIComputerPlayer(player);
        }
    }

    private void parseGameType(String userInput) {
        switch (userInput) {
            case "1":
                choice = HvH;
                break;
            case "2":
                choice = HvC;
                break;
            case "3":
                choice = CvH;
                break;
            case "4":
                choice = CvC;
                break;
            default:
                choice = HvH;
        }
    }
}


