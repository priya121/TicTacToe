package ttt.board;

import ttt.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private final List<Symbol> row;

    public Line(List<Symbol> symbols) {
        row = symbols;
    }

    public List<Symbol> getSymbols() {
        return row;
    }
}

class RowGenerator {

    public List<Line> createRow(List<Symbol> contentOfBoard, int size) {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < size * size; i += size) {
            List<Symbol> rows = new ArrayList<>();
            for (int j = i; j < i + size; j++) {
                rows.add(contentOfBoard.get(j));
            }
            lines.add(new Line(rows));
        }
        return lines;
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

    public boolean winningRows(List<Symbol> rows, int size, Symbol symbol) {
        List<Line> lines = createRow(rows, size);
        return checkRowWins(lines, symbol);
    }
}

class ColumnGenerator {

    public List<Line> createColumn(List<Symbol> contentOfBoard, int size) {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < (size); i++) {
            List<Symbol> columns = new ArrayList<>();
            for (int j = i; j < (size * size); j += size) {
                columns.add(contentOfBoard.get(j));
            }
            lines.add(new Line(columns));
        }
        return lines;
    }

    public boolean checkColumnsWins(List<Line> columns, Symbol symbol) {
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

    public boolean winningColumns(List<Symbol> columns, int size, Symbol symbol) {
        List<Line> lines = createColumn(columns, size);
        return checkColumnsWins(lines, symbol);
    }
}

class DiagonalGenerator {

    public List<Line> createDiagonal(List<Symbol> contentOfBoard, int size) {
        List<Symbol> leftToRightDiagonal = new ArrayList<>();
        List<Symbol> rightToLeftDiagonal = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < size * size; i += size + 1) {
            leftToRightDiagonal.add(contentOfBoard.get(i));
        }
        for (int j = size - 1; j < size * size - 1; j += size - 1) {
            rightToLeftDiagonal.add(contentOfBoard.get(j));
        }
        lines.add(new Line(leftToRightDiagonal));
        lines.add(new Line(rightToLeftDiagonal));
        return lines;
    }

    public boolean checkDiagonalWins(List<Line> diagonals, Symbol symbol) {
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

    public boolean winningDiagonals(List<Symbol> diagonals, int size, Symbol symbol) {
        List<Line> lines = createDiagonal(diagonals, size);
        return checkDiagonalWins(lines, symbol);
    }

}

