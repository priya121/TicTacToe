package ttt.inputOutput;

import org.junit.Ignore;
import org.junit.Test;
import ttt.Game;
import ttt.GameCreator;
import ttt.observers.MoveObserver;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class ReplayIOTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    @Test
    public void readsMoveListFromFile() throws IOException {
        File tempFile = File.createTempFile("/Users/priyapatil/TTT/game", ".txt");
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "3", "6", "2", "7", "N")));
        moveObserverGame(game, tempFile);
        ReplayIO replayMoves = new ReplayIO(tempFile, out);
        assertEquals(Arrays.asList("4", "0", "5", "3", "6", "2", "7"), replayMoves.getMovesFromFile());
    }

    @Ignore
    @Test
    public void readsTimeStampsFromFile() throws IOException {
        File tempFile = File.createTempFile("/Users/priyapatil/TTT/game", ".txt");
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "3", "6", "2", "7", "N")));
        moveObserverGame(game, tempFile);
        ReplayIO replayMoves = new ReplayIO(tempFile, out);
        assertEquals("14588", replayMoves.getMoveTimes().get(0).toString().substring(0,5));
    }

    @Test
    public void readsTheTimeDifferenceBetweenMoves() throws IOException {
        File tempFile = File.createTempFile("/Users/priyapatil/TTT/game", ".txt");
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "3", "6", "2", "7", "N")));
        moveObserverGame(game, tempFile);
        ReplayIO replayMoves = new ReplayIO(tempFile, out);
        String timeOfFirstMove = replayMoves.getMoveTimes().get(0).toString();
        String timeOfSecondMove = replayMoves.getMoveTimes().get(5).toString();
        assertNotEquals(timeOfFirstMove, timeOfSecondMove);
    }

    @Test
    public void boardAtEndOfReplayGameSameAsPreviousGame() throws IOException {
        File tempFile = File.createTempFile("/Users/priyapatil/TTT/game", ".txt");
        GameCreator game = getGame(convertUserInput(new ByteArrayInputStream("3\n1\n0\n1\n2\n3\n4\n5\n6\n3\nR\n".getBytes())));
        Game startGame = game.createGame();
        moveObserverGame(startGame, tempFile);
        ReplayIO replayIO = new ReplayIO(tempFile, out);
        game.createReplayGame(replayIO);
        assertTrue(recordedOutput.toString().contains("Replaying previous game..."));
    }

    private MoveObserver moveObserverGame(Game game, File output) {
        MoveObserver moveObserver = new MoveObserver(game, output);
        game.gameLoop();
        return moveObserver;
    }

    private GameCreator getGame(IO humanMoves) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("/Users/priyapatil/TTT/game", ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReplayIO io = new ReplayIO(tempFile, out);
        return new GameCreator(humanMoves, io);
    }

    private Game getFourByFourGame(FakeIO humanMoves) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("/Users/priyapatil/TTT/game", ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReplayIO io = new ReplayIO(tempFile, out);
        return new GameCreator(humanMoves, io).createGame();
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }
}
