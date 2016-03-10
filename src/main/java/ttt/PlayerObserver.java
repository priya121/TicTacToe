package ttt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerObserver extends Observer{
    private final File file;
    public List<String> ordered = new ArrayList<>();
    MoveLog moveLog = new MoveLog();

    public PlayerObserver(Game game, File file) {
        this.game = game;
        this.game.attach(this);
        this.file = file;
    }

    @Override
    public String update() {
        String currentPlayer = String.valueOf(game.getCurrentPlayer());
        listObservations(currentPlayer);
        try {
            writeToFile(currentPlayer, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentPlayer;
    }

    @Override
    public List<String> listObservations(String observation) {
        ordered.add(observation);
        return ordered;
    }

    public void writeToFile(String player, File file) throws IOException {
        String display = "Player: " + player + "\n";
        moveLog.transfer(display, file);
    }
}
