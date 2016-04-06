package ttt.menuitems;

import ttt.Game;
import ttt.TwoVsTwoGameCreator;
import ttt.inputOutput.IO;
import ttt.player.PlayerCreator;

public class TwoPlayerMenuItem implements MenuItem {
    private final IO io;
    private final TwoVsTwoGameCreator gameCreator;
    public Game game;

    public TwoPlayerMenuItem(IO io, PlayerCreator playerCreator) {
        this.io = io;
        this.gameCreator = new TwoVsTwoGameCreator(io, playerCreator);
    }

    @Override
    public void show() {
        io.showOutput("1) Two Player Game");
    }

    @Override
    public void perform() {
        gameCreator.gameStart();
    }

    @Override
    public boolean shouldAppear() {
        return true;
    }

}
