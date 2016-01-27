package ttt.board;

import ttt.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static ttt.Symbol.E;
import static ttt.Symbol.X;
import static ttt.Symbol.O;

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

    boolean checkWinningLine(List<Line> lines, Symbol symbol) {
        boolean winningLine = false;
        for (Line line : lines) {
            for (int i = 0; i < line.getSymbols().size(); i++) {
                winningLine = line.getSymbols().get(i) == symbol;
                if (!winningLine) break;
            }
            if (winningLine) return true;
        }
        return winningLine;
    }

    boolean checkWins(Symbol symbol) {
        return (checkWinningLine(getRows(), symbol) || checkWinningLine(getColumns(), symbol) || checkWinningLine(getDiagonals(), symbol));
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
        List<Integer> validMoves = new ArrayList<>();
        IntStream.range(0, this.board.size())
                .filter(index -> this.board.get(index).equals(E))
                .forEach(i -> validMoves.add(i));
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
