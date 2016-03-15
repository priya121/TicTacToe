package ttt.observers;

import ttt.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerObserver extends Observer {
    private final File file;
    public List<String> ordered = new ArrayList<>();
    GameLog gameLog = new GameLog();

    public PlayerObserver(Game game, File file) {
        this.game = game;
        this.game.attach(this);
        this.file = file;
    }

    @Override
    public String update() {
        String currentPlayer = String.valueOf(game.getCurrentPlayer());
        listObservations(currentPlayer);
        writeToFile(currentPlayer, file);
        return currentPlayer;
    }

    @Override
    public List<String> listObservations(String observation) {
        ordered.add(observation);
        return ordered;
    }

    @Override
    public void writeToFile(String player, File file) {
        String display = "Player: " + player + "\n";
        try {
            gameLog.transfer(display, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
