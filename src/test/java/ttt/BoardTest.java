package ttt;

import org.junit.Assert;
import org.junit.Test;

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

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    @Test
        public void checksWinFirstRowForCross() {
            FakeIO io = getFakeIO(Arrays.asList("0", "3", "1", "6", "2"));
            Board board = new Board(emptyBoard);
            Player player = new Player(io, board);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, board.lineIsWin(cross));
        }

    @Test
        public void checksWinLastRowForCross() {
            FakeIO io = getFakeIO(Arrays.asList("1", "6", "5", "7", "2", "8"));
            Board board = new Board(emptyBoard);
            Player player = new Player(io, board);
            for (int i = 0; i < 6; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, board.lineIsWin(nought));
        }

    @Test
        public void checksWinSecondRowForCross() {
            FakeIO io = getFakeIO(Arrays.asList("3", "0", "4", "6", "5"));
            Board board = new Board(emptyBoard);
            Player player = new Player(io, board);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, board.lineIsWin(cross));
        }

    @Test
        public void checksNoughtWinsDiagonally() {
            FakeIO io = getFakeIO(Arrays.asList("1", "0", "3", "4", "7", "8"));
            Board board = new Board(emptyBoard);
            Player player = new Player(io, board);
            for (int i = 0; i < 6; i++) {
                player.markBoard();
            }
            Assert.assertEquals(false, board.lineIsWin(cross));
            Assert.assertEquals(true, board.lineIsWin(nought));
        }

    @Test
        public void checksAnotherDiagonalWin() {
            FakeIO io = getFakeIO(Arrays.asList("1", "0", "3", "4", "7", "8"));
            Board board = new Board(emptyBoard);
            Player player = new Player(io, board);
            for (int i = 0; i < 6; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, board.lineIsWin(nought));
        }

    @Test
        public void checksDiagonalWinForCross() {
            FakeIO io = getFakeIO(Arrays.asList("0", "1", "4", "6", "8"));
            Board board = new Board(emptyBoard);
            Player player = new Player(io, board);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, board.hasWin());
        }

    @Test
        public void checksDiagonalWinForNought() {
            FakeIO io = getFakeIO(Arrays.asList("3", "2", "0", "6", "5", "4"));
            Board board = new Board(emptyBoard);
            Player player = new Player(io, board);
            for (int i = 0; i < 6; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, board.hasWin());
        }

    @Test
        public void checksVerticalWinMiddleColumnForCross() {
            FakeIO io = getFakeIO(Arrays.asList("1", "0", "4", "6", "7"));
            Board board = new Board(emptyBoard);
            Player player = new Player(io, board);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, board.hasWin());
        }

    @Test
        public void returnsFalseIfNoWin() {
            FakeIO io = getFakeIO(Arrays.asList("8", "0", "4", "6", "7"));
            Board board = new Board(emptyBoard);
            Player player = new Player(io, board);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(false, board.hasWin());
        }

    @Test
        public void checksVerticalWinFirstColumnForCross() {
            FakeIO io = getFakeIO(Arrays.asList("0", "1", "3", "8", "6"));
            Board board = new Board(emptyBoard);
            Player player = new Player(io, board);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, board.lineIsWin(cross));
            Assert.assertEquals(false, board.lineIsWin(nought));
        }

    @Test
        public void checksVerticalWinLastColumn() {
            FakeIO io = getFakeIO(Arrays.asList("2", "1", "5", "3", "8"));
            Board board = new Board(emptyBoard);
            Player player = new Player(io, board);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, board.lineIsWin(cross));
            Assert.assertEquals(false, board.lineIsWin(nought));
        }

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
            Assert.assertTrue(board.notFull());
        }

    @Test
        public void checksBoardNotFull() {
            List<Symbol> emptyBoard  = Arrays.asList(empty, empty, empty,
                                                     empty, empty, empty,
                                                     empty, empty, empty);
            Board board = new Board(emptyBoard);
            Assert.assertTrue(board.notFull());
        }

    public List<Integer> moves(int from, int to) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = from; i < to; i++) {
            result.add(i);
        }
        return result;
    }
}

