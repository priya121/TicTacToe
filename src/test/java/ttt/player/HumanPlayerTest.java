package ttt.player;

import ttt.GameSetup;
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

import static ttt.Symbol.CROSS;
import static ttt.Symbol.NOUGHT;
import static ttt.Symbol.EMPTY;

public class HumanPlayerTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    GameSetup initialSetup = new GameSetup();
    List<Symbol> emptyBoard = initialSetup.emptyBoard(3, 3);
    Board currentBoard = new Board(emptyBoard);
    Game game;

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
        Assert.assertTrue(recordedOutput.toString().contains("That position is already taken or is not on the ttt.board, try again."));
    }

    @Test
    public void playerOnlyMakesValidDigitMove() {
        ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(recordedOutput);
        List<Symbol> newBoard = Arrays.asList(EMPTY, CROSS, EMPTY,
                EMPTY, NOUGHT, EMPTY,
                EMPTY, NOUGHT, CROSS);
        InputStream inputStream = new ByteArrayInputStream("a\na\n1\n0\n2".getBytes());
        FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(5, 7, 8));
        ConsoleIO io = new ConsoleIO(inputStream, out);
        Board currentBoard  = new Board(newBoard);
        Game game = new Game(currentBoard, io, fakeComputerMoves);
        game.gameLoop();
    }

    @Test
    public void playerOnlyMakesAMoveOnTheBoard() {
        ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(recordedOutput);
        List<Symbol> currentBoard = Arrays.asList(EMPTY, CROSS, EMPTY,
                EMPTY, NOUGHT, EMPTY,
                EMPTY, NOUGHT, CROSS);
        InputStream inputStream = new ByteArrayInputStream("100\n0\n1\n2\n".getBytes());
        FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(5, 7, 8));
        ConsoleIO io = new ConsoleIO(inputStream, out);
        Board board = new Board(currentBoard);
        Game game = new Game(board, io, fakeComputerMoves);
        game.gameLoop();
    }
}

