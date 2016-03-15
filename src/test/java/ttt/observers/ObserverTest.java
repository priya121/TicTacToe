package ttt.observers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ttt.Game;
import ttt.GameCreator;
import ttt.inputOutput.FakeIO;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ObserverTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void setsLastUpdatedMoveAsCellTwoForTheFirstHumanPlayer() throws IOException {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "0", "8", "1", "7", "3", "5", "2", "N")));
        File output = temporaryFolder.newFile("output.txt");
        MoveObserver moveObserver = moveObserverGame(game, output);
        assertEquals(Arrays.asList("0", "8", "1", "7", "3", "5", "2"), moveObserver.movesList);
    }

    @Test
    public void setsLastUpdatedMoveAsCellSevenForTheFirstHumanPlayer() throws IOException {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "3", "6", "2", "7", "N")));
        File output = temporaryFolder.newFile("output.txt");
        MoveObserver moveObserver = moveObserverGame(game, output);
        assertEquals(Arrays.asList("4", "0", "5", "3", "6", "2", "7"), moveObserver.movesList);
    }

    @Test
    public void setsLastUpdateCurrentPlayerAsXForPlayerObserver() throws IOException {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "3", "6", "2", "7", "N")));
        File output = temporaryFolder.newFile("output.txt");
        PlayerObserver playerObserver = playerObserverGame(game, output);
        assertEquals(Arrays.asList("X", "O", "X", "O", "X", "O", "X"), playerObserver.ordered);
    }

    @Test
    public void setsLastUpdateCurrentPlayerAsOForPlayerObserver() throws IOException {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "2", "6", "3", "15", "1", "N")));
        File output = temporaryFolder.newFile("output.txt");
        PlayerObserver playerObserver = playerObserverGame(game, output);
        assertEquals(Arrays.asList("X", "O", "X", "O", "X", "O", "X", "O"), playerObserver.ordered);
    }
    @Test
    public void testsTimeGivenForEachMove() throws IOException {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "2", "6", "3", "15", "1", "N")));
        File output = temporaryFolder.newFile("output.txt");
        DateTimeObserver timeObserver =  timeObserverGame(game, output);
        assertEquals("Tue Mar ", timeObserver.update().substring(0, 8));
    }

    private PlayerObserver playerObserverGame(Game game, File output) {
        PlayerObserver playerObserver = new PlayerObserver(game, output);
        game.gameLoop();
        return playerObserver;
    }

    private MoveObserver moveObserverGame(Game game, File output) {
        MoveObserver moveObserver = new MoveObserver(game, output);
        game.gameLoop();
        return moveObserver;
    }

    private DateTimeObserver timeObserverGame(Game game, File output) {
        DateTimeObserver dateTimeObserver = new DateTimeObserver(game, output);
        game.gameLoop();
        return dateTimeObserver;
    }


    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private Game getFourByFourGame(FakeIO humanMoves) {
        return new GameCreator(humanMoves).createGame();
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }
}
