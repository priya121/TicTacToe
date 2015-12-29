package ttt;

import java.util.List;
import java.util.ArrayList;

public class Board {
    List<Symbol> board;
    Symbol empty = Symbol.EMPTY;
    Symbol cross = Symbol.CROSS;
    Symbol nought = Symbol.NOUGHT;

    public Board(List<Symbol> board) {
        this.board = board;
    }

    public List<Symbol> markPlayer(int indexPosition, Symbol player) {
        board.set(indexPosition, player);
        return board;
    }

    public List<Symbol> getCurrentBoard() {
        return board;
    }

    public Symbol get(int index) {
        return board.get(index);
    }

    public boolean isValid(int move) {
        return validMoves().contains(move);
    }

    public boolean boardNotFull() {
        return board.contains(empty);
    }

    public List<Integer> validMoves() {
        List<Integer> validMoves = new ArrayList<Integer>();
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).equals(empty)) {
                validMoves.add(i);
            }
        }
        return validMoves;
    }
}
