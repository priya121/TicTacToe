package ttt;

import org.junit.Assert;
import org.junit.Test;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.FakeIO;
import ttt.inputOutput.IO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.*;

public class GameTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    SetupBoard expected = new SetupBoard();

    @Test
    public void boardChangedWhenPlayerMakesMove() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, X, X, E, E, E, E, O, O));
        Game game = getGame(humanMoves(Arrays.asList("3", "1", "0", "7", "2", "8", "1", "N")));
        game.gameLoop();
        Assert.assertEquals(expectedBoard, game.getBoard());
    }

    @Test
    public void switchesPlayersFromCrossToNought() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, X, E, E, X, E, O, O, O));
        Game game = getGame(humanMoves(Arrays.asList("3", "1", "0", "6", "1", "7", "4", "8", "N")));
        game.gameLoop();
        Assert.assertEquals(expectedBoard, game.getBoard());
    }

    @Test
    public void switchesPlayersFromCrossToNoughtOnFourByFourBoard() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, X, X, X,
                                                                         E, E, O, O,
                                                                         O, E, E, E,
                                                                         E, E, E, E));
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "1", "6", "3", "7", "2", "8", "0", "N")));
        game.gameLoop();
        Assert.assertEquals(expectedBoard, game.getBoard());
    }

    @Test
    public void asksUserToEnterDimensionOfBoard() {
        Game game = getGame(convertUserInput(new ByteArrayInputStream("3\n1\n2\n1\n5\n4\n8\n7\n".getBytes())));
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("game over"));
    }

    @Test
    public void userMustEnterDigits() {
        Game game = getGame(convertUserInput(new ByteArrayInputStream("X\na\n1\n1\n0\n2\nN\n".getBytes())));
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("Please enter a valid number"));
    }

    @Test
    public void computesCurrentPlayer() {
        Game game = getGame(convertUserInput(new ByteArrayInputStream("X\n1\n6\n0\n8\n2\n4\n1\nN\n".getBytes())));
        game.gameLoop();
        Assert.assertEquals(X, game.getPlayerOne());
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private Game getGame(IO humanMoves) {
        return new Game(humanMoves);
    }

    private Game getFourByFourGame(FakeIO humanMoves) {
        return new Game(humanMoves);
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }
}
