package ttt.observers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class GameLog implements AutoCloseable {
    public List<Move> moveList;
    public boolean isEmpty;

    public GameLog() {
        this.moveList = receiveMoves();
        this.isEmpty = (moveList.size() == 0);
    }

    public List<Move> receiveMoves() {
        try {
            FileInputStream readMoves = new FileInputStream("/Users/priyapatil/ttt.ser");
            ObjectInputStream allMoves = new ObjectInputStream(readMoves);
            List<Move> listMoves = (List<Move>) allMoves.readObject();
            return listMoves;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() throws Exception {
    }

}
