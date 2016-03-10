package ttt.observers;

import ttt.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DateTimeObserver extends Observer {
    private final File file;
    public List<String> listOfTimes = new ArrayList<>();
    private MoveLogger moveLog = new MoveLogger();

    public DateTimeObserver(Game game, File file) {
        this.game = game;
        this.game.attach(this);
        this.file = file;
    }

    @Override
    public String update() {
        String timeOfMove = String.valueOf(game.getTime());
        listObservations(timeOfMove);
        writeToFile(timeOfMove, file);
        return timeOfMove;
    }

    @Override
    public List<String> listObservations(String observation) {
        listOfTimes.add(observation);
        return listOfTimes;
    }

    @Override
    public void writeToFile(String time, File file) {
        String display = "Played At: " + time + "\n";
        try {
            moveLog.transfer(display, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
