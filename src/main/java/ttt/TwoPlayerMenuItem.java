package ttt;

import ttt.inputOutput.IO;
import ttt.player.Player;
import ttt.player.PlayerCreator;

public class TwoPlayerMenuItem implements MenuItem {
    private final IO io;
    private final PlayerCreator creator;

    public TwoPlayerMenuItem(IO io) {
        this.io = io;
        this.creator = new PlayerCreator(io);
    }

    @Override
    public void show() {
    }

    @Override
    public void perform() {
    }

    public Player playerX() {
        return creator.createX();
    }

    public Player playerO() {
        return creator.createO();
    }
}
