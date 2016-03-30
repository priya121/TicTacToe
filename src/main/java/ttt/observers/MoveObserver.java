package ttt.observers;

import ttt.Game;

import java.io.*;
import java.util.*;

public class MoveObserver implements Observer {
    public Game game;
    public ArrayList<Move> movesInfo;

    public MoveObserver(Game game) {
        this.game = game;
        game.addObserver(this);
        this.movesInfo = new ArrayList<>();
    }

    public void addMove(long move, long time) {
        Move newMove = new Move(move, time);
        movesInfo.add(newMove);
    }

    public List<Move> generateMoves() {
        try {
            FileOutputStream outputStream = new FileOutputStream("/Users/priyapatil/ttt.ser");
            ObjectOutputStream stream = new ObjectOutputStream(outputStream);
            stream.writeObject(movesInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        addMove(game.move, game.time);
        generateMoves();
    }
}
