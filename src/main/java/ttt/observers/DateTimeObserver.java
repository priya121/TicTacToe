package ttt.observers;

import ttt.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class DateTimeObserver implements Observer {
    private final File file;
    private final Game game;
    public List<String> listOfTimes = new ArrayList<>();
    private GameLog gameLog = new GameLog();

    public DateTimeObserver(Game game, File file) {
        this.game = game;
        this.game.attach(this);
        this.file = file;
    }

    public List<String> listObservations(String observation) {
        listOfTimes.add(observation);
        return listOfTimes;
    }

    public void writeToFile(String time) {
        String display = "Played At: " + time + "\n\n";
        try {
            gameLog.transfer(display, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable game, Object time) {
        listObservations(String.valueOf(time));
        writeToFile(String.valueOf(time));
    }
}
