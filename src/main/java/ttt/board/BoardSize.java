package ttt.board;

import ttt.Symbol;

import java.util.List;

public class BoardSize {
    final int size;
    LineGenerator lines;

    public BoardSize(int size) {
        this.size = size;
        this.lines = new LineGenerator();
    }

    public List<Line> rows() {
        return lines.createRow(size);
    }

    public List<Line> columns() {
        return lines.createColumn(size);
    }

    public List<Line> diagonals() {
        return lines.createDiagonal(size);
    }

    public boolean checkRowWins(List<Symbol> symbols, Symbol symbol) {
        boolean winningLine = false;
        for (Line row : rows()) {
            for (Integer index : row.getSymbols()) {
                winningLine = (symbols.get(index) == symbol);
                if (!winningLine) break;
            }
            if (winningLine) return true;
        }
        return winningLine;
    }

    public boolean checkDiagonalWins(List<Symbol> symbols, Symbol symbol) {
        boolean winningLine = false;
        for (Line diagonal : diagonals()) {
            for (Integer index : diagonal.getSymbols()) {
                winningLine = (symbols.get(index) == symbol);
                if (!winningLine) break;
            }
            if (winningLine) return true;
        }
        return winningLine;
    }

    public boolean checkColumnWins(List<Symbol> symbols, Symbol symbol) {
        boolean winningLine = false;
        for (Line column : columns()) {
            for (Integer index : column.getSymbols()) {
                winningLine = (symbols.get(index) == symbol);
                if (!winningLine) break;
            }
            if (winningLine) return true;
        }
        return winningLine;
    }

    public boolean checkWins(List<Symbol> symbols, Symbol symbol) {
        return checkRowWins(symbols, symbol) || checkColumnWins(symbols, symbol) || checkDiagonalWins(symbols, symbol);
    }
}
