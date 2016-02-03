package ttt.board;

import org.junit.Test;
import ttt.Symbol;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static ttt.Symbol.E;
import static ttt.Symbol.X;
import static ttt.Symbol.O;

public class BoardSizeTest {
    Board board = new Board(3);
    BoardSize boardSize = new BoardSize(board);

    @Test
    public void getsSizeOfBoard() {
        assertEquals(3, boardSize.size);
    }

    @Test
    public void calculatesRowThreeByThreeBoard() {
        List<Symbol> firstRow = Arrays.asList(E, E, E);
        assertEquals(firstRow, boardSize.rows().get(0).getSymbols());
    }

    @Test
    public void showsWinningRowForOneRow() {
        board.markPlayer(0, X);
        board.markPlayer(1, X);
        board.markPlayer(2, X);
        assertTrue(boardSize.anyRowWins(X));
        assertFalse(boardSize.anyRowWins(O));
    }

    @Test
    public void calculatesColumnsForThreeByThreeBoard() {
        List<Symbol> firstColumn = Arrays.asList(E, E, E);
        assertEquals(firstColumn, boardSize.columns().get(0).getSymbols());
    }

    @Test
    public void showsWinningRowForOneColumns() {
        Board markedBoard = markBoard(board, Arrays.asList(0, 3, 6), O);
        BoardSize boardSize = new BoardSize(markedBoard);
        assertTrue(boardSize.anyColumnWins(O));
        assertFalse(boardSize.anyColumnWins(X));
    }

    @Test
    public void calculatesDiagonalsForThreeByThreeBoard() {
        List<Symbol> firstDiagonal = Arrays.asList(E, E, E);
        assertEquals(firstDiagonal, boardSize.diagonals().get(0).getSymbols());
    }

    @Test
    public void showsWinningRowForOneDiagonal() {
        board.markPlayer(0, O);
        board.markPlayer(4, O);
        board.markPlayer(8, O);
        assertTrue(boardSize.anyDiagonalWins(O));
        assertFalse(boardSize.anyDiagonalWins(X));
    }

    @Test
    public void calculatesWinningLinesForFourByFour() {
        Board fourByFourBoard = new Board(4);
        BoardSize boardSize = new BoardSize(fourByFourBoard);
        List<Symbol> firstRow = Arrays.asList(E, E, E, E);
        assertEquals(firstRow, boardSize.rows().get(0).getSymbols());
    }

    public Board markBoard(Board board, List<Integer> indices, Symbol symbol) {
        for (Integer index : indices) {
            board.markPlayer(index, symbol);
        }
        return board;
    }
}
