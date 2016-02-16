package ttt.board;

import ttt.Symbol;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

public class BoardSize {
    final int size;
    private LineGenerator lines;

    public BoardSize(int size) {
        this.size = size;
        this.lines = new LineGenerator();
    }

    public List<Line> createLines() {
        lines.createRow(size);
        lines.createColumns(size);
        return lines.createDiagonal(size);
    }

    public Stream<IntStream> joinAllLines() {
        //return lines.streamColumns(size);
        return concat(lines.streamRows(size), concat(lines.streamColumns(size), lines.streamDiagonals(size)));
    }

    public boolean checkLineWins(List<Symbol> symbols, Symbol symbol) {
        boolean winningLine = false;
        for (Line line : createLines()) {
            for (Integer index : line.getLineIndices()) {
                winningLine = (symbols.get(index) == symbol);
                if (!winningLine) break;
            }
            if (winningLine) return true;
        }
        return winningLine;
    }

}
