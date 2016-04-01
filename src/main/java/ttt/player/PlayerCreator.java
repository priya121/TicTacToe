package ttt.player;

import ttt.GameType;
import ttt.Symbol;
import ttt.inputOutput.IO;
import ttt.observers.GameLog;

import static ttt.GameType.*;
import static ttt.Symbol.O;
import static ttt.Symbol.X;

public class PlayerCreator {
    private final IO io;
    private final GameLog gameLog;
    private GameType choice;

    public PlayerCreator(IO io, String userInput) {
        this.io = io;
        this.parseGameType(userInput);
        gameLog = new GameLog();
    }

    public Player createX() {
        return createPlayer(HvH, HvC, RvR, X);
    }

    public Player createO() {
        return createPlayer(CvH, HvH, RvR, O);
    }

    private Player createPlayer(GameType choiceOne, GameType choiceTwo, GameType choiceThree, Symbol player) {
        if (choice == choiceThree) {
            return new ReplayPlayer(player, gameLog);
        } else if (choice == choiceOne || choice == choiceTwo) {
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
            case "5":
                choice = RvR;
                break;
            default:
                choice = HvH;
        }
    }
}


