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

    public PlayerCreator(IO io) {
        this.io = io;
        gameLog = new GameLog();
    }

    public void createPlayers(IO io) {
        parseGameType(io);
    }

    public Player createX() {
        return createPlayer(HvH, HvC, X);
    }

    public Player createReplayX() {
        return createReplayPlayer(X);
    }

    public Player createReplayO() {
        return createReplayPlayer(O);
    }

    public Player createO() {
        return createPlayer(CvH, HvH, O);
    }

    private Player createReplayPlayer(Symbol symbol) {
        return new ReplayPlayer(symbol, gameLog);
    }

    private Player createPlayer(GameType choiceOne, GameType choiceTwo, Symbol player) {
         if (choice == choiceOne || choice == choiceTwo) {
            return new HumanPlayer(io, player);
        } else {
            return new AIComputerPlayer(player);
        }
    }

    private void parseGameType(IO io) {
        String userInput = io.takeInput();
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


