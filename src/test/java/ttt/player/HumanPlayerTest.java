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
    public void playerGivesMove() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("0"));
        HumanPlayer humanPlayer = new HumanPlayer(fakeInput, currentBoard);
        Assert.assertEquals(0, humanPlayer.move());
    }

    @Test
    public void userMustEnterEmptyPositionIndex() {
        InputStream inputStream = new ByteArrayInputStream("0\n0\n1\n2\n".getBytes());
        FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(8, 4, 7));
        ConsoleIO io = new ConsoleIO(inputStream, out);
        Game newGame = new Game(currentBoard, io, fakeComputerMoves);
        newGame.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("That position is already taken or is not on the board, try again."));
    }

    @Test(expected = Exception.class)
    public void playerOnlyMakesValidDigitMove() {
        ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(recordedOutput);
        List<Symbol> newBoard = Arrays.asList(empty, cross, empty,
                empty, nought, empty,
                empty, nought, cross);
        InputStream inputStream = new ByteArrayInputStream("a\na\n1".getBytes());
        FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(5));
        ConsoleIO io = new ConsoleIO(inputStream, out);
        Board currentBoard  = new Board(newBoard);
        Game game = new Game(currentBoard, io, fakeComputerMoves);
        game.markBoard();
    }

    @Test(expected = Exception.class)
    public void playerOnlyMakesAMoveOnTheBoard() {
        ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(recordedOutput);
        List<Symbol> currentBoard = Arrays.asList(empty, cross, empty,
                empty, nought, empty,
                empty, nought, cross);
        InputStream inputStream = new ByteArrayInputStream("100\n".getBytes());
        FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(5));
        ConsoleIO io = new ConsoleIO(inputStream, out);
        Board board = new Board(currentBoard);
        Game game = new Game(board, io, fakeComputerMoves);
        game.markBoard();
    }
}

