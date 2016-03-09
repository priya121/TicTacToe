package ttt;

import java.util.ArrayList;
import java.util.List;

public class MoveObserver extends Observer {
    List<String> movesList = new ArrayList<>();

    public MoveObserver(Game game) {
        this.game = game;
        this.game.attach(this);
    }

    @Override
    public String update() {
        String move = String.valueOf(game.getMove());
        listObservations(move);
        return move;
    }

    @Override
    public List<String> listObservations(String move) {
        movesList.add(move);
        return movesList;
    }
}
