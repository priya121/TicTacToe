package ttt.board;

import ttt.Symbol;

import java.util.List;

public class BoardSize {
    private final Board board;
    final int size;

    public BoardSize(Board board) {
        this.board = board;
        this.size = board.size;
    }

    public List<Line> rows() {
        return new RowGenerator().createRow(board.contentsOfBoard(), size);
    }

    public List<Line> columns() {
        return new ColumnGenerator().createColumn(board.contentsOfBoard(), size);
    }

    public List<Line> diagonals() {
        return new DiagonalGenerator().createDiagonal(board.contentsOfBoard(), size);
    }

    public boolean anyRowWins(Symbol symbol) {
        return checkRowWins(rows(), symbol);
    }

    public boolean anyColumnWins(Symbol symbol) {
        return checkColumnWins(columns(), symbol);
    }

    public boolean anyDiagonalWins(Symbol symbol) {
        return checkDiagonalWins(diagonals(), symbol);
    }

    private boolean checkDiagonalWins(List<Line> diagonals, Symbol symbol) {
        boolean winningLine = false;
        for (Line diagonal : diagonals) {
            for (int i = 0; i < diagonal.getSymbols().size(); i++) {
                winningLine = diagonal.getSymbols().get(i) == symbol;
                if (!winningLine) break;
            }
            if (winningLine) return true;
        }
        return winningLine;
    }

    private boolean checkColumnWins(List<Line> columns, Symbol symbol) {
        boolean winningLine = false;
        for (Line column : columns) {
            for (int i = 0; i < column.getSymbols().size(); i++) {
                winningLine = column.getSymbols().get(i) == symbol;
                if (!winningLine) break;
            }
            if (winningLine) return true;
        }
        return winningLine;
    }

    public boolean checkRowWins(List<Line> rows, Symbol symbol) {
        boolean winningLine = false;
        for (Line row : rows) {
            for (int i = 0; i < row.getSymbols().size(); i++) {
                winningLine = row.getSymbols().get(i) == symbol;
                if (!winningLine) break;
            }
            if (winningLine) return true;
        }
        return winningLine;
    }
}
