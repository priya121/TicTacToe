package ttt.observers;

import ttt.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class PlayerObserver implements Observer {
    private final File file;
    private final Game game;
    public List<String> ordered = new ArrayList<>();
    GameLog gameLog = new GameLog();

    public PlayerObserver(Game game, File file) {
        this.game = game;
        this.game.attach(this);
        this.file = file;
    }

    public List<String> listObservations(String observation) {
        ordered.add(observation);
        return ordered;
    }

    public void writeToFile(String player) {
        String display = "Player: " + player + "\n";
        try {
            gameLog.transfer(display, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable game, Object player) {
        listObservations(String.valueOf(player));
        writeToFile(String.valueOf(player));
    }
}
