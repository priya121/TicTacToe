package ttt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoveObserver extends Observer {
    File file;
    List<String> movesList = new ArrayList<>();
    MoveLog moveLog = new MoveLog();

    public MoveObserver(Game game, File file) {
        this.game = game;
        this.game.attach(this);
        this.file = file;
    }

    @Override
    public String update() {
        String move = String.valueOf(game.getMove());
        listObservations(move);
        try {
            writeToFile(move, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return move;
    }

    @Override
    public List<String> listObservations(String move) {
        movesList.add(move);
        return movesList;
    }

    public void writeToFile(String move, File file) throws IOException {
        String display = "Made a move at position " + move + "\n";
        moveLog.transfer(display, file);
    }
}
