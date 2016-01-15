package ttt.board;

import org.junit.Assert;
import org.junit.Test;
import ttt.Game;
import ttt.Symbol;
import ttt.inputOutput.FakeIO;
import ttt.player.FakeComputerPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.EMPTY;
import static ttt.Symbol.CROSS;
import static ttt.Symbol.NOUGHT;

public class BoardTest {
    List<Symbol> emptyBoard = emptyBoard(3, 3);
    Board currentBoard = new Board(emptyBoard);
    Board board = new Board(emptyBoard);

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private FakeComputerPlayer getFakeComputerMoves(List<Integer> input) {
        return new FakeComputerPlayer(input);
    }

    public List<Symbol> emptyBoard(int height, int width) {
        List<Symbol> board = new ArrayList<Symbol>();
        for (int i = 0; i < height * width; i++) {
            board.add(EMPTY);
        }
        return board;
    }

    public List<Symbol> placeSymbols(List<Integer> crossPositions, List<Integer> noughtPositions) {
        List<Symbol> board = emptyBoard(3, 3);
        for (int index : crossPositions) {
            for (int j = 0; j < noughtPositions.size(); j++) {
                board.set(noughtPositions.get(j), NOUGHT);
            }
            board.set(crossPositions.get(index), CROSS);
        }
        return board;
    }

    @Test
    public void checksWinFirstRowForCross() {
            FakeIO io = getFakeIO(Arrays.asList("0", "1", "2"));
            FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(3, 6));
            Game game = new Game(board, io, computerMoves);
            game.gameLoop();
            Assert.assertEquals(true, board.lineIsWin(CROSS));
        }

    @Test
    public void checksWinLastRowForCross() {
            FakeIO io = getFakeIO(Arrays.asList("1", "5", "2"));
            FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(6, 7, 8));
            Game game = new Game(currentBoard, io, computerMoves);
            game.gameLoop();
            Assert.assertEquals(true, board.lineIsWin(NOUGHT));
        }

    @Test
    public void checksWinSecondRowForCross() {
            FakeIO io = getFakeIO(Arrays.asList("3", "4", "5"));
            FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(0, 6));
            Game game = new Game(currentBoard, io, computerMoves);
            game.gameLoop();
            Assert.assertEquals(true, board.lineIsWin(CROSS));
        }

    @Test
        public void checksNoughtWinsDiagonally() {
            FakeIO io = getFakeIO(Arrays.asList("1", "3", "7"));
            FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(0, 4, 8));
            Game game = new Game(currentBoard, io, computerMoves);
            game.gameLoop();
            Assert.assertEquals(false, board.lineIsWin(CROSS));
            Assert.assertEquals(true, board.lineIsWin(NOUGHT));
        }

    @Test
        public void checksAnotherDiagonalWin() {
            FakeIO io = getFakeIO(Arrays.asList("1", "3", "7"));
            FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(0, 4, 8));
            Game game = new Game(currentBoard, io, computerMoves);
            game.gameLoop();
            Assert.assertEquals(true, board.lineIsWin(NOUGHT));
        }

    @Test
        public void checksDiagonalWinForCross() {
            FakeIO io = getFakeIO(Arrays.asList("0", "4", "8"));
            FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(1, 6));
            Game game = new Game(currentBoard, io, computerMoves);
            game.gameLoop();
            Assert.assertEquals(true, board.isWin());
        }

    @Test
        public void checksDiagonalWinForNought() {
            FakeIO io = getFakeIO(Arrays.asList("3", "0", "5"));
            FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(2, 6, 4));
            Game game = new Game(currentBoard, io, computerMoves);
            game.gameLoop();
            Assert.assertEquals(true, board.isWin());
        }

    @Test
        public void checksVerticalWinMiddleColumnForCross() {
            FakeIO io = getFakeIO(Arrays.asList("1", "4", "7"));
            FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(0, 6));
            Game game = new Game(currentBoard, io, computerMoves);
            game.gameLoop();
            Assert.assertEquals(true, board.isWin());
        }

    @Test
        public void returnsFalseIfNoWin() {
            FakeIO io = getFakeIO(Arrays.asList("0", "2", "3", "7", "8"));
            FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(1, 4, 5, 6));
            Game game = new Game(currentBoard, io, computerMoves);
            game.gameLoop();
            Assert.assertEquals(false, board.isWin());
        }

    @Test
    public void checksVerticalWinFirstColumnForCross() {
        FakeIO io = getFakeIO(Arrays.asList("0", "3", "6"));
        FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(1, 8));
        Game game = new Game(currentBoard, io, computerMoves);
        game.gameLoop();
        Assert.assertEquals(true, board.lineIsWin(CROSS));
        Assert.assertEquals(false, board.lineIsWin(NOUGHT));
    }

    @Test
    public void checksVerticalWinLastColumn() {
        FakeIO io = getFakeIO(Arrays.asList("2", "1", "5", "3", "8"));
        FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(1, 3));
        Game game = new Game(currentBoard, io, computerMoves);
        game.gameLoop();
        Assert.assertEquals(true, board.lineIsWin(CROSS));
        Assert.assertEquals(false, board.lineIsWin(NOUGHT));
    }

    @Test
    public void setsUpThreeByThreeBoard() {
        List<Symbol> expectedBoard = Arrays.asList(CROSS, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY);
        Assert.assertEquals(expectedBoard, currentBoard.markPlayer(0, CROSS));
    }

    @Test
    public void returnsCurrentStateOfTheBoard() {
        List<Symbol> boardAfterTwoPlayerMoves = Arrays.asList(CROSS, NOUGHT, EMPTY,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY);
        currentBoard.markPlayer(1, NOUGHT);
        Assert.assertEquals(boardAfterTwoPlayerMoves, currentBoard.markPlayer(0, CROSS));
    }

    @Test
    public void getsPositionOfCell() {
        List<Symbol> boardAfterTwoMoves = Arrays.asList(CROSS, NOUGHT, EMPTY,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY);
        Board newBoard = new Board(boardAfterTwoMoves);
        Assert.assertEquals(CROSS, newBoard.get(0));
    }

    @Test(expected = RuntimeException.class)
    public void throwsAnExceptionIfMoveIsNotAvailable() {
        Board newBoard = new Board(emptyBoard);
        newBoard.markPlayer(1337, CROSS);
    }

    @Test
    public void largerThanBoardIsInvalid() {
        Board newBoard = new Board(emptyBoard);
        Assert.assertFalse(newBoard.isPositionEmpty(37));
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
        List<Integer> result = new ArrayList<>();
        for (int i = from; i < to; i++) {
            result.add(i);
        }
        return result;
    }
}

