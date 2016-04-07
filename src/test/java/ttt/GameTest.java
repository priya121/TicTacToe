package ttt;

import org.junit.Assert;
import org.junit.Test;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.FakeIO;
import ttt.inputOutput.IO;
import ttt.player.PlayerCreator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ttt.Symbol.*;

public class GameTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    SetupBoard expected = new SetupBoard();

    @Test
    public void boardChangedWhenPlayerMakesMove() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, X, X, E, E, E, E, O, O));
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("1", "3", "0", "7", "2", "8", "1")));
        game.gameLoop();
        assertEquals(expectedBoard, game.getBoard());
    }

    @Test
    public void switchesPlayersFromCrossToNought() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, X, E, E, X, E, O, O, O));
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("1", "3", "0", "6", "1", "7", "4", "8")));
        game.gameLoop();
        assertEquals(expectedBoard, game.getBoard());
    }

    @Test
    public void switchesPlayersFromCrossToNoughtOnFourByFourBoard() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, X, X, X,
                                                                         E, E, O, O,
                                                                         O, E, E, E,
                                                                         E, E, E, E));
        Game game = getFourByFourGame(humanMoves(Arrays.asList("1", "4", "1", "6", "3", "7", "2", "8", "0")));
        game.gameLoop();
        assertEquals(expectedBoard, game.getBoard());
    }

    @Test
    public void userMustEnterDigits() {
        Game game = getThreeByThreeGame(convertUserInput(new ByteArrayInputStream("X\na\n1\n1\n0\n2\n".getBytes())));
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("Please enter a valid number"));
    }

    @Test
    public void computesCurrentPlayer() {
        Game game = getThreeByThreeGame(convertUserInput(new ByteArrayInputStream("X\n1\n6\n0\n8\n2\n4\n1\n".getBytes())));
        game.gameLoop();
        assertEquals(X, game.getPlayerOne());
    }

    @Test
    public void updatesTheLastMovePlayedToThree() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("1", "4", "0", "8", "1", "7", "2", "5", "3")));
        game.gameLoop();
        assertEquals(3, game.move);
    }

    @Test
    public void updatesTheLastMovePlayedAsTwo() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("1", "4", "0", "8", "1", "7", "3", "5", "2")));
        game.gameLoop();
        assertEquals(2, game.move);
    }

    @Test
    public void showsEndOfGameMessageWhenXWon() {
        Game game = getThreeByThreeGame(convertUserInput(new ByteArrayInputStream("1\n3\n0\n8\n2\n4\n1\n".getBytes())));
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("Player X has won!"));
    }

    @Test
    public void showsDrawMessageWhenGameIdADraw() {
        Game game = getThreeByThreeGame(convertUserInput(new ByteArrayInputStream("1\n3\n0\n1\n2\n5\n3\n6\n4\n8\n7\n".getBytes())));
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("It's a draw!"));
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private Game getThreeByThreeGame(IO humanMoves) {
        PlayerCreator creator = new PlayerCreator(humanMoves);
        SizeChoice size = new SizeChoice();
        return new TwoVsTwoGameCreator(humanMoves, creator, size).createGame();
    }

    private Game getFourByFourGame(FakeIO humanMoves) {
        PlayerCreator creator = new PlayerCreator(humanMoves);
        SizeChoice size = new SizeChoice();
        return new TwoVsTwoGameCreator(humanMoves, creator, size).createGame();
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }
}
