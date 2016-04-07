package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.Game;
import ttt.SizeChoice;
import ttt.TwoVsTwoGameCreator;
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

public class HumanPlayerTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    Board board = new Board(3);

    @Test
    public void playerCanChooseToBeTheCrossSymbol() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("X"));
        HumanPlayer humanPlayer = new HumanPlayer(fakeInput, X);
        Assert.assertEquals(X, humanPlayer.playerSymbol());
    }

    @Test
    public void playerGivesMove() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("0"));
        HumanPlayer humanPlayer = new HumanPlayer(fakeInput, X);
        Assert.assertEquals(0, humanPlayer.move(board));
    }

    @Test
    public void userMustEnterEmptyPositionIndex() {
        Game game = getGame("1\n3\n0\n5\n0\n2\n7\n1\n8\n");
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("That position is already taken or is not on the board, try again."));
    }

    @Test
    public void playerOnlyMakesValidDigitMove() {
        Game game = getGame("1\na\n0\n5\n0\n2\n7\n1\n8\n");
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("Please enter a valid number"));
    }

    @Test
    public void playerOnlyMakesAMoveOnTheBoard() {
        Game game = getGame("1\n3\n100\n1\n5\n2\n7\n0\n8\n");
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("That position is already taken or is not on the board, try again."));
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }

    private Game getGame(String humanMoves) {
        ConsoleIO io = convertUserInput(new ByteArrayInputStream(humanMoves.getBytes()));
        PlayerCreator creator = new PlayerCreator(io);
        SizeChoice size = new SizeChoice();
        return new TwoVsTwoGameCreator(io, creator, size).createGame();
    }
}

