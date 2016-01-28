package ttt.board;

import ttt.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static ttt.Symbol.*;

public class Board {
    private List<Symbol> board;
    private int size;

    public Board(int size) {
        List<Symbol> newBoard = new ArrayList<>();
        for (int i = 0; i < (size * size); i++) {
            newBoard.add(E);
        }
        this.board = newBoard;
        this.size = (int) Math.sqrt(newBoard.size());
    }

    public Board(List<Symbol> board) {
        this.board = board;
        this.size = (int) Math.sqrt(board.size());
    }

    public List<Line> getRows() {
        return new RowGenerator().createRow(contentsOfBoard(), size);
    }

    public List<Line> getColumns() {
        return new ColumnGenerator().createColumn(contentsOfBoard(), size);
    }

    public List<Line> getDiagonals() {
        return new DiagonalGenerator().createDiagonal(contentsOfBoard(), size);
    }

    boolean checkWinningColumns(Symbol symbol) {
        return new ColumnGenerator().winningColumns(contentsOfBoard(), size, symbol);
    }

    boolean checkWinningRows(Symbol symbol) {
        return new RowGenerator().winningRows(contentsOfBoard(), size, symbol);
    }

    boolean checkWinningDiagonals(Symbol symbol) {
        return new DiagonalGenerator().winningDiagonals(contentsOfBoard(), size, symbol);
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
        List<Integer> validMoves =
        IntStream.range(0, this.board.size()).boxed()
                .filter(index -> this.board.get(index).equals(E))
                .collect(toList());
        return validMoves;
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

    public void clone(Board board) {
        for (int i = 0; i < board.contentsOfBoard().size(); i++) {
            this.markPlayer(i, board.get(i));
        }
    }
}
