package ttt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DateTimeObserver extends Observer {
    private final File file;
    public List<String> listOfTimes = new ArrayList<>();
    private MoveLog moveLog = new MoveLog();

    public DateTimeObserver(Game game, File file) {
        this.game = game;
        this.game.attach(this);
        this.file = file;
    }

    @Override
    public String update() {
        String timeOfMove = game.getTime();
        listObservations(timeOfMove);
        try {
            writeToFile(timeOfMove, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return game.getTime();
    }

    @Override
    public List<String> listObservations(String observation) {
        listOfTimes.add(observation);
        return listOfTimes;
    }

    public void writeToFile(String time, File file) throws IOException {
        String display = "Played At: " + time + "\n";
        moveLog.transfer(display, file);
    }
}
