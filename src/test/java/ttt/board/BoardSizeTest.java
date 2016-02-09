package ttt.board;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static ttt.Symbol.O;
import static ttt.Symbol.X;

public class BoardSizeTest {
    Board board = new Board(3);
    BoardSize boardSize = new BoardSize(3);

    @Test
    public void getsSizeOfBoard() {
        assertEquals(3, boardSize.size);
    }

    @Test
    public void calculatesRowThreeByThreeBoard() {
        List<Integer> firstRow = Arrays.asList(0, 1, 2);
        assertEquals(firstRow, boardSize.lines().get(0).getLineIndices());
    }

    @Test
    public void showsWinningRowForOneRow() {
        board = board.markPlayer(0, X);
        board = board.markPlayer(1, X);
        board = board.markPlayer(2, X);
        assertTrue(boardSize.checkLineWins(board.contentsOfBoard(), X));
        assertFalse(boardSize.checkLineWins(board.contentsOfBoard(), O));
    }

    @Test
    public void calculatesColumnsForThreeByThreeBoard() {
        List<Integer> firstColumn = Arrays.asList(0, 3, 6);
        assertEquals(firstColumn, boardSize.lines().get(3).getLineIndices());
    }

    @Test
    public void calculatesDiagonalsForThreeByThreeBoard() {
        List<Integer> firstDiagonal = Arrays.asList(0, 4, 8);
        assertEquals(firstDiagonal, boardSize.lines().get(6).getLineIndices());
    }

    @Test
    public void calculatesWinningLinesForFourByFour() {
        BoardSize boardSize = new BoardSize(4);
        List<Integer> firstRow = Arrays.asList(0, 1, 2, 3);
        assertEquals(firstRow, boardSize.lines().get(0).getLineIndices());
    }
}
