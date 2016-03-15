package ttt.observers;

import ttt.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoveObserver extends Observer {
    File file;
    public List<String> movesList = new ArrayList<>();
    GameLog gameLog = new GameLog();

    public MoveObserver(Game game, File file) {
        this.game = game;
        this.game.attach(this);
        this.file = file;
    }

    @Override
    public String update() {
        String move = String.valueOf(game.getMove());
        listObservations(move);
        writeToFile(move, file);
        return move;
    }

    @Override
    public List<String> listObservations(String move) {
        movesList.add(move);
        return movesList;
    }

    @Override
    public void writeToFile(String move, File file) {
        String display = "Made a move at position " + move + "\n";
        try {
            gameLog.transfer(display, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
