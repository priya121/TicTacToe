package ttt;

import org.junit.Assert;
import org.junit.Test;
import ttt.board.Board;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.FakeIO;
import ttt.player.ComputerPlayer;
import ttt.player.FakeComputerPlayer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class GameTest {
    Symbol empty = Symbol.EMPTY;
    Symbol cross = Symbol.CROSS;
    Symbol nought = Symbol.NOUGHT;
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
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
    public void tellsUserWhenGameOver() {
        InputStream inputStream = new ByteArrayInputStream("2\n5\n8\n".getBytes());
        FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(1, 3));
        ConsoleIO io = new ConsoleIO(inputStream, out);
        Game newGame = new Game(currentBoard, io, fakeComputerMoves);
        newGame.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("game over"));
    }

    @Test(expected = Exception.class)
    public void userMustEnterDigits() {
        InputStream inputStream = new ByteArrayInputStream("a\n1\n1\n".getBytes());
        FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(1));
        ConsoleIO io = new ConsoleIO(inputStream, out);
        Game newGame = new Game(currentBoard, io, fakeComputerMoves);
        newGame.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("please enter a number from 0-8"));
    }
}

