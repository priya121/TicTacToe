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
    Observable observable;

    public MoveObserver(Game game, File file) {
        this.game = game;
        this.file = file;
        this.observable = game;
        observable.addObserver(this);
    }

    public List<String> listObservations(String move) {
        movesList.add(move);
        return movesList;
    }

    public void writeToFile(String move, String player, String time) {
        String display =  move + "\n" +
                          time + "\n";
        try {
            gameLog.transfer(display, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        listObservations(String.valueOf(game.move));
        writeToFile(String.valueOf(game.move),
                    String.valueOf(game.currentPlayer.playerSymbol()),
                    String.valueOf(game.date));
    }
}
