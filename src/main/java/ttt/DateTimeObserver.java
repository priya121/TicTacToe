package ttt;

import java.util.ArrayList;
import java.util.List;

public class DateTimeObserver extends Observer {
    public List<String> listOfTimes = new ArrayList<>();

    public DateTimeObserver(Game game) {
        this.game = game;
        this.game.attach(this);
    }

    @Override
    public String update() {
        String timeOfMove = game.getTime();
        listObservations(timeOfMove);
        return game.getTime();
    }

    @Override
    public List<String> listObservations(String observation) {
        listOfTimes.add(observation);
        return listOfTimes;
    }
}
