package ttt.player;

import ttt.Symbol;
import ttt.board.Board;

public interface Player {
    int move(Board board);

    Symbol playerSymbol();
}
