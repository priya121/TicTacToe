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
        assertEquals(Arrays.asList("0", "8", "1", "7", "3", "5", "2"), game.moveObserver.movesList);
    }

    @Test
    public void setsLastUpdatedMoveAsCellSevenForTheFirstHumanPlayer() throws IOException {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "3", "6", "2", "7", "N")));
        File output = temporaryFolder.newFile("output.txt");
        MoveObserver moveObserver = moveObserverGame(game, output);
        assertEquals(Arrays.asList("4", "0", "5", "3", "6", "2", "7"), game.moveObserver.movesList);
    }

    private MoveObserver moveObserverGame(Game game, File output) {
        MoveObserver moveObserver = new MoveObserver(game, output);
        game.gameLoop();
        return moveObserver;
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
