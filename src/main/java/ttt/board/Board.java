package ttt.board;

import ttt.Symbol;

import java.util.List;
import java.util.ArrayList;

public class Board {
    List<Symbol> board;
    Symbol empty = Symbol.EMPTY;
    Symbol cross = Symbol.CROSS;
    Symbol nought = Symbol.NOUGHT;
    private int[][] winningLines = new int[][] {{0, 1, 2},
                                                {3, 4, 5},
                                                {6, 7, 8},
                                                {0, 4, 8},
                                                {2, 4, 6},
                                                {0, 3, 6},
                                                {1, 4, 7},
                                                {2, 5, 8}};

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

    public boolean notFull() {
        return board.contains(empty);
    }

    public List<Integer> validMoves() {
        List<Integer> validMoves = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).equals(empty)) {
                validMoves.add(i);
            }
        }
        return validMoves;
    }

    public boolean gameNotOver() {
        return !hasWin() && notFull();
    }

    public boolean hasWin() {
        if (lineIsWin(cross) || lineIsWin(nought)) return true;
        return false;
    }

    public boolean lineIsWin(Symbol symbol) {
        for (int[] winningLine : winningLines) {
            if (board.get(winningLine[0]).equals(symbol) && board.get(winningLine[1]).equals(symbol) && board.get(winningLine[2]).equals(symbol)) {
                return true;
            }
        }
        return false;
    }
}
