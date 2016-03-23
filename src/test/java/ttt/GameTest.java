package ttt;

import org.junit.Assert;
import org.junit.Test;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.FakeIO;
import ttt.inputOutput.IO;
import ttt.inputOutput.ReplayIO;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ttt.Symbol.*;

public class GameTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    File file = new File("/Users/priyapatil/TTT/gameLog.txt");
    ReplayIO replayIO = new ReplayIO(file, out);
    SetupBoard expected = new SetupBoard();

    @Test
    public void boardChangedWhenPlayerMakesMove() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, X, X, E, E, E, E, O, O));
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("3", "1", "0", "7", "2", "8", "1", "N")));
        game.gameLoop();
        assertEquals(expectedBoard, game.getBoard());
    }

    @Test
    public void switchesPlayersFromCrossToNought() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, X, E, E, X, E, O, O, O));
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("3", "1", "0", "6", "1", "7", "4", "8", "N")));
        game.gameLoop();
        assertEquals(expectedBoard, game.getBoard());
    }

    @Test
    public void switchesPlayersFromCrossToNoughtOnFourByFourBoard() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, X, X, X,
                                                                         E, E, O, O,
                                                                         O, E, E, E,
                                                                         E, E, E, E));
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "1", "6", "3", "7", "2", "8", "0", "N")));
        game.gameLoop();
        assertEquals(expectedBoard, game.getBoard());
    }

    @Test
    public void userMustEnterDigits() {
        Game game = getThreeByThreeGame(convertUserInput(new ByteArrayInputStream("X\na\n1\n1\n0\n2\nN\n".getBytes())));
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("Please enter a valid number"));
    }

    @Test
    public void computesCurrentPlayer() {
        Game game = getThreeByThreeGame(convertUserInput(new ByteArrayInputStream("X\n1\n6\n0\n8\n2\n4\n1\nN\n".getBytes())));
        game.gameLoop();
        assertEquals(X, game.getPlayerOne());
    }

    @Test
    public void updatesTheLastMovePlayedToThree() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "0", "8", "1", "7", "2", "5", "3", "N")));
        game.gameLoop();
        assertEquals(3, game.move);
    }

    @Test
    public void updatesTheLastMovePlayedAsTwo() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "0", "8", "1", "7", "3", "5", "2", "N")));
        game.gameLoop();
        assertEquals(2, game.move);
    }

    @Test
    public void replaysAGameIfUserChoosesR() {
        GameCreator gameCreator = gameCreator(convertUserInput(new ByteArrayInputStream("3\n1\n0\n1\n2\n3\n4\n5\n6\nR\n".getBytes())));
        gameCreator.gameStart();
        Assert.assertTrue(recordedOutput.toString().contains("Replaying previous game..."));
    }


    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private GameCreator gameCreator(IO humanMoves) {
        return new GameCreator(humanMoves, replayIO);
    }

    private Game getThreeByThreeGame(IO humanMoves) {
        return new GameCreator(humanMoves, replayIO).createGame();
    }

    private Game getFourByFourGame(FakeIO humanMoves) {
        return new GameCreator(humanMoves, replayIO).createGame();
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }
}
