package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.Game;
import ttt.board.Board;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.FakeIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.X;
import static ttt.Symbol.O;

public class HumanPlayerTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    Board board = new Board(3);

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private FakeComputerPlayer getFakeComputerMoves(List<Integer> input) {
        FakeIO fakeInput = getFakeIO(Arrays.asList("X"));
        HumanPlayer humanPlayer = new HumanPlayer(fakeInput, board);
        return new FakeComputerPlayer(input, humanPlayer);
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }

    public FakeComputerPlayer computerMoves(List<Integer> moves) {
        return getFakeComputerMoves(moves);
    }

    private Game getGame(String humanMoves, FakeComputerPlayer computerMoves) {
        ConsoleIO io = convertUserInput(new ByteArrayInputStream(humanMoves.getBytes()));
        HumanPlayer human = new HumanPlayer(io, board);
        return new Game(board, io, human, computerMoves);
    }

    @Test
    public void playerCanChooseToBeTheCrossSymbol() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("X"));
        HumanPlayer humanPlayer = new HumanPlayer(fakeInput, board);
        Assert.assertEquals(X, humanPlayer.setPlayerSymbol());
        Assert.assertEquals(X, humanPlayer.calculateOwnSymbol());
    }

    @Test
    public void playerCanChooseToBeNoughtSymbol() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("O"));
        HumanPlayer humanPlayer = new HumanPlayer(fakeInput, board);
        Assert.assertEquals(O, humanPlayer.setPlayerSymbol());
        Assert.assertEquals(O, humanPlayer.calculateOwnSymbol());
    }

    @Test
    public void playerGivesMove() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("0"));
        HumanPlayer humanPlayer = new HumanPlayer(fakeInput, board);
        Assert.assertEquals(0, humanPlayer.move());
    }

    @Test
    public void userMustEnterEmptyPositionIndex() {
        Game game = getGame("3\nX\n0\n0\n1\n2\nN\n", computerMoves(Arrays.asList(5, 7, 8)));
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("That position is already taken or is not on the board, try again."));
    }

    @Test
    public void playerOnlyMakesValidDigitMove() {
        Game game = getGame("3\na\na\n1\n0\n2\nN\n", computerMoves(Arrays.asList(5, 7, 8)));
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("Please enter a valid number"));
    }

    @Test
    public void playerOnlyMakesAMoveOnTheBoard() {
        Game game = getGame("3\nX\n100\n1\n2\n0\nN\n", computerMoves(Arrays.asList(5, 7, 8)));
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("That position is already taken or is not on the board, try again."));
    }
}

