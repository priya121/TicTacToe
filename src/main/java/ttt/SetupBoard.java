package ttt;

import java.util.ArrayList;
import java.util.List;

import static ttt.Symbol.E;

public class SetupBoard {

    public SetupBoard() {
    }

    public List<Symbol> emptyBoard(int height, int width) {
        List<Symbol> board = new ArrayList<>();
        for (int i = 0; i < height * width; i++) {
            board.add(E);
        }
        return board;
    }

    public List<Symbol> placeSymbols(List<Symbol> symbols) {
        List<Symbol> board = new ArrayList<>();
        for (Symbol symbol : symbols) {
            board.add(symbol);
        }
        return board;
    }
}
