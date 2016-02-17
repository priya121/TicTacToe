package ttt.board;

import java.util.stream.IntStream;
import java.util.stream.Stream;


class LineGenerator {

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
}


