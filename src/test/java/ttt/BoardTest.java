package ttt;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class BoardTest {
    Symbol empty = Symbol.EMPTY;
    Symbol cross = Symbol.CROSS;
    Symbol nought = Symbol.NOUGHT;
    List<Symbol> emptyBoard = Arrays.asList(empty, empty, empty,
                                            empty, empty, empty,
                                            empty, empty, empty);
        Board currentBoard = new Board(emptyBoard);

        @Test
        public void setsUpThreeByThreeBoard() {
        List<Symbol> expectedBoard = Arrays.asList(cross, empty, empty,
                                                   empty, empty, empty,
                                                   empty, empty, empty);
        Assert.assertEquals(expectedBoard, currentBoard.markPlayer(0, cross));
        }

        @Test
        public void returnsCurrentStateOfTheBoard() {
        List<Symbol> boardAfterTwoPlayerMoves = Arrays.asList(cross, nought, empty,
                                                              empty, empty, empty,
                                                              empty, empty, empty);
            currentBoard.markPlayer(1, nought);
            Assert.assertEquals(boardAfterTwoPlayerMoves, currentBoard.markPlayer(0, cross));
        }

        @Test
        public void getsPositionOfCell() {
            List<Symbol> boardAfterTwoMoves = Arrays.asList(cross, nought, empty,
                                                            empty, empty, empty,
                                                            empty, empty, empty);
            Board newBoard = new Board(boardAfterTwoMoves);
            Assert.assertEquals(cross, newBoard.get(0));
        }

        @Test(expected = RuntimeException.class)
        public void throwsAnExceptionIfMoveIsNotAvailable() {
            Board newBoard = new Board(emptyBoard);
            newBoard.markPlayer(1337, cross);
        }

        @Test
        public void largerThanBoardIsInvalid() {
            Board newBoard = new Board(emptyBoard);
            Assert.assertFalse(newBoard.isValid(37));
        }

        @Test
        public void returnsListOfValidMoves() {
            Board newBoard = new Board(emptyBoard);
            List<Integer> validMoves = moves(0, 9);
            Assert.assertEquals(validMoves, newBoard.validMoves());
        }

        @Test
        public void checksBoardFull() {
            List<Symbol> fullBoard = Arrays.asList(empty, empty, empty,
                                                   empty, empty, empty,
                                                   empty, empty, empty);
            Board board = new Board(fullBoard);
            Assert.assertTrue(board.boardNotFull());
        }

        @Ignore
        @Test
        public void checksBoardNotFull() {
            List<Symbol> emptyBoard  = Arrays.asList(empty, empty, empty,
                                                     empty, empty, empty,
                                                     empty, empty, empty);
            Board board = new Board(emptyBoard);
            Assert.assertFalse(board.boardNotFull());
        }

        public List<Integer> moves(int from, int to) {
            List<Integer> result = new ArrayList<Integer>();
            for (int i = from; i < to; i++) {
                result.add(i);
            }
            return result;
        }
}

