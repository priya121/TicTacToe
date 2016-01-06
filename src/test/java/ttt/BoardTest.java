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
    Board board = new Board(emptyBoard);

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private FakeComputerPlayer getFakeComputerMoves(List<Integer> input) {
        return new FakeComputerPlayer(input);
    }

    @Test
        public void checksWinFirstRowForCross() {
            FakeIO io = getFakeIO(Arrays.asList("0", "1", "2"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(3, 6));
            Game game = new Game(board, io, fakeComputerMoves);
            for (int i = 0; i < 3; i++) {
                game.markBoard();
            }
            Assert.assertEquals(true, board.lineIsWin(cross));
        }

    @Test
        public void checksWinLastRowForCross() {
            FakeIO io = getFakeIO(Arrays.asList("1", "5", "2"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(6, 7, 8));
            Game game = new Game(currentBoard, io, fakeComputerMoves);
            for (int i = 0; i < 6; i++) {
                game.markBoard();
            }
            Assert.assertEquals(true, board.lineIsWin(nought));
        }

    @Test
        public void checksWinSecondRowForCross() {
            FakeIO io = getFakeIO(Arrays.asList("3", "4", "5"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(0, 6));
            Game game = new Game(currentBoard, io, fakeComputerMoves);
            for (int i = 0; i < 3; i++) {
                game.markBoard();
            }
            Assert.assertEquals(true, board.lineIsWin(cross));
        }

    @Test
        public void checksNoughtWinsDiagonally() {
            FakeIO io = getFakeIO(Arrays.asList("1", "3", "7"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(0, 4, 8));
            Game game = new Game(currentBoard, io, fakeComputerMoves);
            for (int i = 0; i < 3; i++) {
                game.markBoard();
            }
            Assert.assertEquals(false, board.lineIsWin(cross));
            Assert.assertEquals(true, board.lineIsWin(nought));
        }

    @Test
        public void checksAnotherDiagonalWin() {
            FakeIO io = getFakeIO(Arrays.asList("1", "3", "7"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(0, 4, 8));
            Game game = new Game(currentBoard, io, fakeComputerMoves);
            for (int i = 0; i < 3; i++) {
                game.markBoard();
            }
            Assert.assertEquals(true, board.lineIsWin(nought));
        }

    @Test
        public void checksDiagonalWinForCross() {
            FakeIO io = getFakeIO(Arrays.asList("0", "4", "8"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(1, 6));
            Game game = new Game(currentBoard, io, fakeComputerMoves);
            for (int i = 0; i < 5; i++) {
                game.markBoard();
            }
            Assert.assertEquals(true, board.hasWin());
        }

    @Test
        public void checksDiagonalWinForNought() {
            FakeIO io = getFakeIO(Arrays.asList("3", "0", "5"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(2, 6, 4));
            Game game = new Game(currentBoard, io, fakeComputerMoves);
            for (int i = 0; i < 3; i++) {
                game.markBoard();
            }
            Assert.assertEquals(true, board.hasWin());
        }

    @Test
        public void checksVerticalWinMiddleColumnForCross() {
            FakeIO io = getFakeIO(Arrays.asList("1", "4", "7"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(0, 6));
            HumanPlayer humanPlayer = new HumanPlayer(io, currentBoard);
            Game game = new Game(currentBoard, io, fakeComputerMoves);
            for (int i = 0; i < 5; i++) {
                game.markBoard();
            }
            Assert.assertEquals(true, board.hasWin());
        }

    @Test
        public void returnsFalseIfNoWin() {
            FakeIO io = getFakeIO(Arrays.asList("8", "4", "7"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(0, 6, 2));
            Game game = new Game(currentBoard, io, fakeComputerMoves);
            for (int i = 0; i < 3; i++) {
                game.markBoard();
            }
            Assert.assertEquals(false, board.hasWin());
        }

    @Test
        public void checksVerticalWinFirstColumnForCross() {
            FakeIO io = getFakeIO(Arrays.asList("0", "3", "6"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(1, 8));
            Game game = new Game(currentBoard, io, fakeComputerMoves);
            for (int i = 0; i < 5; i++) {
                game.markBoard();
            }
            Assert.assertEquals(true, board.lineIsWin(cross));
            Assert.assertEquals(false, board.lineIsWin(nought));
        }

    @Test
        public void checksVerticalWinLastColumn() {
            FakeIO io = getFakeIO(Arrays.asList("2", "1", "5", "3", "8"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(1, 3));
            Game game = new Game(currentBoard, io, fakeComputerMoves);
            for (int i = 0; i < 5; i++) {
                game.markBoard();
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
            Assert.assertTrue(board.notFull());
        }

    @Test
        public void checksBoardNotFull() {
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

