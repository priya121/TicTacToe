package ttt.board;

import ttt.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static ttt.Symbol.*;

public final class Board {
    private final List<Symbol> board;
    final BoardSize lines;
    private final int size;

    public Board(int size) {
        List<Symbol> newBoard = new ArrayList<>();
        for (int i = 0; i < (size * size); i++) {
            newBoard.add(E);
        }
        this.board = newBoard;
        this.size = (int) Math.sqrt(newBoard.size());
        this.lines = new BoardSize(size);
    }

    public Board(List<Symbol> board) {
        this.board = board;
        this.size = (int) Math.sqrt(board.size());
        this.lines = new BoardSize(size);
    }

    boolean checkWins(Symbol symbol) {
        return (lines.checkWins(contentsOfBoard(), symbol));
    }

    public List<Symbol> markPlayer(int indexPosition, Symbol player) {
        board.set(indexPosition, player);
        return board;
    }

    public List<Symbol> contentsOfBoard() {
        return board;
    }

    public Symbol get(int index) {
        return board.get(index);
    }

    public boolean isPositionEmpty(int move) {
        return validMoves().contains(move);
    }

    public boolean notFull() {
        return board.contains(E);
    }

    public List<Integer> validMoves() {
        return IntStream.range(0, this.board.size()).boxed()
                .filter(index -> this.board.get(index).equals(E))
                .collect(toList());
    }

    public boolean gameNotOver() {
        return !isWin() && notFull();
    }

    public boolean isWin() {
        return checkWins(O) || checkWins(X);
    }

    public boolean winningSymbol(Symbol symbol) {
        return checkWins(symbol);
    }

    public Object clone() {
        List<Symbol> newBoard = new ArrayList<>(board);
        return new Board(newBoard);
    }
}
