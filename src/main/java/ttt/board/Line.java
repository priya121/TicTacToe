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

}

