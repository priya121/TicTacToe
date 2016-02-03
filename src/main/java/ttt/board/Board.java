package ttt.board;

import ttt.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static ttt.Symbol.*;

public class Board {
    private List<Symbol> board;
    BoardSize lines;
    int size;

    public Board(int size) {
        List<Symbol> newBoard = new ArrayList<>();
        for (int i = 0; i < (size * size); i++) {
            newBoard.add(E);
        }
        this.board = newBoard;
        this.size = (int) Math.sqrt(newBoard.size());
        this.lines = new BoardSize(this);
    }

    public Board(List<Symbol> board) {
        this.board = board;
        this.size = (int) Math.sqrt(board.size());
        this.lines = new BoardSize(this);
    }

    public List<Line> getRows() {
        return lines.rows();
    }

    public List<Line> getColumns() {
        return lines.columns();
    }

    public List<Line> getDiagonals() {
        return lines.diagonals();
    }

    boolean checkWinningColumns(Symbol symbol) {
        return lines.anyColumnWins(symbol);
    }

    boolean checkWinningRows(Symbol symbol) {
        return lines.anyRowWins(symbol);
    }

    boolean checkWinningDiagonals(Symbol symbol) {
        return lines.anyDiagonalWins(symbol);
    }

    boolean checkWins(Symbol symbol) {
        return (checkWinningRows(symbol) || checkWinningColumns(symbol) || checkWinningDiagonals(symbol));
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

    public boolean winningSymbolCross() {
        return checkWins(X);
    }

    public boolean winningSymbolNought() {
        return checkWins(O);
    }

    public Object clone() {
        List<Symbol> newBoard = new ArrayList<>(board);
        return new Board(newBoard);
    }
}
