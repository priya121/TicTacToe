package ttt.observers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ttt.Game;
import ttt.GameCreator;
import ttt.inputOutput.FakeIO;
import ttt.inputOutput.ReplayIO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ObserverTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    File file = new File("/Users/priyapatil/TTT/gameLog.txt");
    ReplayIO replayIO = new ReplayIO(file, out);

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void setsLastUpdatedMoveAsCellTwoForTheFirstHumanPlayer() throws IOException {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "0", "8", "1", "7", "3", "5", "2", "N")));
        File output = temporaryFolder.newFile("output.txt");
        moveObserverGame(game, output);
        assertEquals(Arrays.asList("0", "8", "1", "7", "3", "5", "2"), game.moveObserver.movesList);
    }

    @Test
    public void setsLastUpdatedMoveAsCellSevenForTheFirstHumanPlayer() throws IOException {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "3", "6", "2", "7", "N")));
        File output = temporaryFolder.newFile("output.txt");
        moveObserverGame(game, output);
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
        return new GameCreator(humanMoves, replayIO).createGame();
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }
}
