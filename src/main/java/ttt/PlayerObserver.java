package ttt;

import java.util.ArrayList;
import java.util.List;

public class PlayerObserver extends Observer{
    public List<String> ordered = new ArrayList<>();

    public PlayerObserver(Game game) {
        this.game = game;
        this.game.attach(this);
    }

    @Override
    public String update() {
        String currentPlayer = String.valueOf(game.getCurrentPlayer());
        listObservations(currentPlayer);
        return currentPlayer;
    }

    @Override
    public List<String> listObservations(String observation) {
        ordered.add(observation);
        return ordered;
    }
}
