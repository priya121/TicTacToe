package ttt.observers;

import ttt.Game;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MoveObserver implements Observer {
    private final Game game;
    File file;
    public List<String> movesList = new ArrayList<>();
    GameLog gameLog = new GameLog();

    public MoveObserver(Game game, File file) {
        this.game = game;
        this.game.attach(this);
        this.file = file;
    }

    public List<String> listObservations(String move) {
        movesList.add(move);
        return movesList;
    }

    public void writeToFile(String move) {
        String display = "Made a move at position " + move + "\n";
        try {
            gameLog.transfer(display, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable game, Object move) {
        listObservations(String.valueOf(move));
        writeToFile(String.valueOf(move));
    }
}
