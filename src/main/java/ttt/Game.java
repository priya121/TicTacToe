package ttt;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<String> board = new ArrayList<String>();

    public Game(List<String> board) {
        this.board = board;
    }

    public List<String> move(String symbol) {
        board.add(symbol);
        return board;
    }

    public boolean win() {
        return true;
    }


}
