package ttt.board;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

public class WinningLines {
    final int size;
    private LineGenerator lines;

    public WinningLines(int size) {
        this.size = size;
        this.lines = new LineGenerator();
    }

    public Stream<IntStream> allWinningLines() {
        return concat(lines.streamRows(size), concat(lines.streamColumns(size), lines.streamDiagonals(size)));
    }
}
