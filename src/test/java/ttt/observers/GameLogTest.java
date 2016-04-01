package ttt.observers;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ttt.Game;
import ttt.GameCreator;
import ttt.inputOutput.FakeIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class GameLogTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Ignore
    @Test
    public void combinesAllObservationsAndWritesThemToAFile() throws IOException {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "3", "6", "2", "7", "N")));
        File output = temporaryFolder.newFile("output.txt");
        new MoveObserver(game);
        game.gameLoop();
        assertEquals("Player: X\n" +
                "Made a index at position 4\n" +
                "Played At: Thu Mar 10 11:31:16 GMT 2016\n" +
                "Player: O\n" +
                "Made a index at position 0\n" +
                "Played At: Thu Mar 10 11:31:16 GMT 2016\n" +
                "Player: X\n" +
                "Made a index at position 5\n" +
                "Played At: Thu Mar 10 11:31:16 GMT 2016\n" +
                "Player: O\n" +
                "Made a index at position 3\n" +
                "Played At: Thu Mar 10 11:31:16 GMT 2016\n" +
                "Player: X\n" +
                "Made a index at position 6\n" +
                "Played At: Thu Mar 10 11:31:16 GMT 2016\n" +
                "Player: O\n" +
                "Made a index at position 2\n" +
                "Played At: Thu Mar 10 11:31:16 GMT 2016\n" +
                "Player: X\n" +
                "Made a index at position 7\n" +
                "Played At: Thu Mar 10 11:31:16 GMT 2016", read(output.getPath(), 532));
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

    public String read(String inputFilePath, int amountOfBytes) throws IOException {
        File inputFile = new File(inputFilePath);
        InputStream input = new FileInputStream(inputFile);
        byte[] buffer = new byte[amountOfBytes];
        input.read(buffer);
        return new String(buffer);
    }
}
