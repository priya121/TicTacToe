package ttt.menuitems;

import ttt.Game;
import ttt.TwoByTwoGameCreator;
import ttt.inputOutput.IO;

public class TwoPlayerMenuItem implements MenuItem {
    private final IO io;
    private final TwoByTwoGameCreator gameCreator;
    public Game game;

    public TwoPlayerMenuItem(IO io) {
        this.io = io;
        this.gameCreator = new TwoByTwoGameCreator(io);
    }

    @Override
    public void show() {
        io.showOutput("1) Two Player Game");
    }

    @Override
    public void perform() {
        game = gameCreator.createGame();
        game.gameLoop();
    }

    public String askTypeOfTwoPlayerGame() {
        return io.showOutput("Hi, choose a two player game type (Enter 1 - 4): \n" +
                "1) Human vs Human \n" +
                "2) Human vs Computer \n" +
                "3) Computer vs Human \n" +
                "4) Computer vs Computer \n" +
                "Entering any other character will return a default Human v Human game:");
    }
}
