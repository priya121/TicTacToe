package ttt.board;

import ttt.Symbol;

import java.util.List;
import java.util.ArrayList;

public class BoardSize {
    final int size;
    LineGenerator lines;

    public BoardSize(int size) {
        this.size = size;
        this.lines = new LineGenerator();
    }

    public List<Line> lines() {
        lines.createRow(size);
        lines.createColumn(size);
        return lines.createDiagonal(size);
    }

    public boolean checkLineWins(List<Symbol> symbols, Symbol symbol) {
        boolean winningLine = false;
        for (Line line : lines()) {
            for (Integer index : line.getLineIndices()) {
                winningLine = (symbols.get(index) == symbol);
                if (!winningLine) break;
            }
            if (winningLine) return true;
        }
        return winningLine;
    }
}
