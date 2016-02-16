package ttt.board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class LineGenerator {
    List<Line> lines = new ArrayList<>();

    public Stream<IntStream> streamRows(int size) {
        return IntStream.range(0, size)
                .map(index -> index * size)
                .mapToObj(rowStart -> IntStream.range(rowStart, rowStart + size));
    }

    public Stream<IntStream> streamColumns(int size) {
        return IntStream.range(0, size)
                .mapToObj(columnStart -> IntStream.iterate(columnStart, index -> index + size)
                        .limit(size));
    }

    public Stream<IntStream> streamDiagonals(int size) {
        IntStream diagonalOne = IntStream
                .iterate(0, index -> index + size + 1)
                .limit(size);
        IntStream diagonalTwo = IntStream
                .iterate(size - 1, index -> index + size - 1)
                .limit(size);

        return Stream.of(diagonalOne, diagonalTwo);
    }

    public IntStream streamRightToLeft(int size) {
        return IntStream
                .iterate(size - 1, index -> index + size - 1)
                .limit(size);
    }


    public List<Line> createRow(int size) {
        for (int i = 0; i < size * size; i += size) {
            List<Integer> rows = new ArrayList<>();
            for (int j = i; j < i + size; j++) {
                rows.add(j);
            }
            lines.add(new Line(rows));
        }
        return lines;
    }

    public List<Line> createColumns(int size) {
        for (int i = 0; i < (size); i++) {
            List<Integer> columns = new ArrayList<>();
            for (int j = i; j < (size * size); j += size) {
                columns.add(j);
            }
            lines.add(new Line(columns));
        }
        return lines;
    }

    public List<Line> createDiagonal(int size) {
        List<Integer> leftToRightDiagonal = new ArrayList<>();
        List<Integer> rightToLeftDiagonal = new ArrayList<>();
        for (int i = 0; i < size * size; i += size + 1) {
            leftToRightDiagonal.add(i);
        }
        for (int j = size - 1; j < size * size - 1; j += size - 1) {
            rightToLeftDiagonal.add(j);
        }
        lines.add(new Line(leftToRightDiagonal));
        lines.add(new Line(rightToLeftDiagonal));
        return lines;
    }
}

public class Line {
    private final List<Integer> line;

    public Line(List<Integer> symbols) {
        line = symbols;
    }

    public List<Integer> getLineIndices() {
        return line;
    }
}

