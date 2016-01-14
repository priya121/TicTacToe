package ttt;

import java.util.ArrayList;
import java.util.List;

import static ttt.Symbol.EMPTY;
import static ttt.Symbol.CROSS;
import static ttt.Symbol.NOUGHT;

public class GameSetup {
    private List<Symbol> initialBoard;

    public GameSetup() {
    }

    public List<Symbol> emptyBoard(int height, int width) {
        List<Symbol> board = new ArrayList<Symbol>();
        for (int i = 0; i < height * width; i++) {
            board.add(EMPTY);
        }
        return board;
    }

    public List<Symbol> placeSymbols(List<Integer> crossPositions, List<Integer> noughtPositions) {
        List<Symbol> board = emptyBoard(3, 3);
        for (int i = 0; i < crossPositions.size(); i++) {
            for (int j = 0; j < noughtPositions.size(); j++) {
                board.set(noughtPositions.get(j), NOUGHT);
            }
            board.set(crossPositions.get(i), CROSS);
        }
        return board;
    }
}
