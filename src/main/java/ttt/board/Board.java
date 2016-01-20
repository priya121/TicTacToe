package ttt.board;

import ttt.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static ttt.Symbol.E;
import static ttt.Symbol.X;
import static ttt.Symbol.O;

public class Board {
    List<Symbol> board = Arrays.asList(E, E, E,
            E, E, E,
            E, E, E);
    private int[][] winningLines = new int[][] {{0, 1, 2},
                                                {3, 4, 5},
                                                {6, 7, 8},
                                                {0, 4, 8},
                                                {2, 4, 6},
                                                {0, 3, 6},
                                                {1, 4, 7},
                                                {2, 5, 8}};

    public Board() {
    }

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

    public boolean isPositionEmpty(int move) {
        return validMoves().contains(move);
    }

    public boolean notFull() {
        return board.contains(Symbol.E);
    }

    public boolean isFull() {
        return !board.contains(Symbol.E);
    }

    public List<Integer> validMoves() {
    List<Integer> validMoves = new ArrayList<>();
        IntStream.range(0,9)
            .filter(index -> board.get(index).equals(E))
            .forEach(i -> validMoves.add(i));
        return validMoves;
        }

    public boolean gameNotOver() {
        return !isWin() && notFull();
    }

    public boolean isWin() {
        return (lineIsWin(X) || lineIsWin(O));
    }

    public boolean winningSymbolCross() {
        return lineIsWin(X);
    }

    public boolean winningSymbolNought() {
        return lineIsWin(O);
    }

    public boolean lineIsWin(Symbol symbol) {
        return Arrays.stream(winningLines)
            .anyMatch(winningLine -> Arrays.stream(winningLine)
            .allMatch(index -> board.get(index).equals(symbol)));
    }

    public void clone(Board board) {
        for (int i = 0; i < board.getCurrentBoard().size(); i++) {
            this.markPlayer(i, board.get(i));
        }
    }
}
