package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.Symbol;
import ttt.board.Board;
import ttt.Game;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.FakeIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class HumanPlayerTest {
    Symbol nought = Symbol.NOUGHT;
    Symbol cross = Symbol.CROSS;
    Symbol empty = Symbol.EMPTY;
    List<Symbol> emptyBoard = Arrays.asList(empty, empty, empty,
                                            empty, empty, empty,
                                            empty, empty, empty);
    Board currentBoard = new Board(emptyBoard);

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private FakeComputerPlayer getFakeComputerMoves(List<Integer> input) {
        return new FakeComputerPlayer(input);
    }

    @Test
        public void playerMarksMove() {
            FakeIO fakeInput = getFakeIO(Arrays.asList("0"));
            HumanPlayer humanPlayer = new HumanPlayer(fakeInput, currentBoard);
            Assert.assertEquals("0", humanPlayer.takesUserInput());
        }

    @Test
        public void boardChangedWhenPlayerMakesMove() {
        List<Symbol> changedBoard = Arrays.asList(cross, nought, empty,
                empty, empty, empty,
                empty, empty, empty);
            FakeIO fakeInput = getFakeIO(Arrays.asList("0", "2"));
            ComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(1, 7));
            Game game = new Game(currentBoard, fakeInput, fakeComputerMoves);
            game.markBoard();
            Assert.assertEquals(changedBoard, currentBoard.getCurrentBoard());
        }

    @Test
        public void switchesPlayersFromCrossToNought() {
        List<Symbol> changedBoard = Arrays.asList(cross, empty, empty,
                                                  nought, empty, empty,
                                                  empty, empty, empty);
            FakeIO fakeInput = getFakeIO(Arrays.asList("0", "1", "4"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(3, 7, 5));
            Game game = new Game(currentBoard, fakeInput, fakeComputerMoves);
            game.markBoard();
            Assert.assertEquals(changedBoard, currentBoard.getCurrentBoard());
        }

    @Test
        public void switchesPlayerFromNoughtToCross() {
        List<Symbol> changedBoard = Arrays.asList(cross, cross, nought,
                                                  empty, nought, empty,
                                                  empty, nought, cross);
            FakeIO fakeInput = getFakeIO(Arrays.asList("0", "8", "1"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(7, 4, 2));
            Game game = new Game(currentBoard, fakeInput, fakeComputerMoves);
            game.markBoard();
            game.markBoard();
            game.markBoard();
            Assert.assertEquals(changedBoard, currentBoard.getCurrentBoard());
        }

    @Test
        public void getsThePlayerWhoseTurnItIs() {
            FakeIO fakeInput = getFakeIO(Arrays.asList("0", "8", "1"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(7, 4, 2));
            Game game = new Game(currentBoard, fakeInput, fakeComputerMoves);
            game.markBoard();
            game.markBoard();
            game.markBoard();
            Assert.assertEquals(cross, game.getNextSymbol());
        }

    @Test
        public void getsThePlayerWhoJustPlayed() {
            FakeIO fakeInput = getFakeIO(Arrays.asList("0", "8", "1"));
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(7, 4, 2));
            Game game = new Game(currentBoard, fakeInput, fakeComputerMoves);
            game.markBoard();
            game.markBoard();
            Assert.assertEquals(cross, game.getPreviousSymbol());
        }

    @Test(expected = Exception.class)
        public void playerOnlyMakesValidMove() {
        ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(recordedOutput);
        List<Symbol> newBoard = Arrays.asList(empty, cross, empty,
                                              empty, nought, empty,
                                              empty, nought, cross);
        InputStream inputStream = new ByteArrayInputStream("a\n1\n".getBytes());
        FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(5));
        ConsoleIO io = new ConsoleIO(inputStream, out);
        Board currentBoard  = new Board(newBoard);
        Game game = new Game(currentBoard, io, fakeComputerMoves);
        game.markBoard();
        Assert.assertTrue(recordedOutput.toString().contains("Please enter a number from 0-8:"));
        }

    @Test(expected = Exception.class)
    public void playerOnlyMakesAMoveOnTheBoard() {
        ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(recordedOutput);
        List<Symbol> newBoard = Arrays.asList(empty, cross, empty,
                                              empty, nought, empty,
                                              empty, nought, cross);
        InputStream inputStream = new ByteArrayInputStream("100\n".getBytes());
        FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(5));
        ConsoleIO io = new ConsoleIO(inputStream, out);
        Board currentBoard = new Board(newBoard);
        Game game = new Game(currentBoard, io, fakeComputerMoves);
        game.markBoard();
        Assert.assertTrue(recordedOutput.toString().contains("Please enter a number from 0-8:"));
    }
}

