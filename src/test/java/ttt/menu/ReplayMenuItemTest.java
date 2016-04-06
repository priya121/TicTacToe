package ttt.menu;

import org.junit.Before;
import org.junit.Test;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.FakeIO;
import ttt.menuitems.ReplayMenuItem;
import ttt.menuitems.TwoPlayerMenuItem;
import ttt.player.PlayerCreator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReplayMenuItemTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    private FakeIO io = getFakeIO(Arrays.asList("1", "3", "0", "1", "2", "3", "4", "5", "6", "3"));

    @Before
    public void setUp() {
        PlayerCreator creator = new PlayerCreator(io);
        TwoPlayerMenuItem twoPlayerGame = new TwoPlayerMenuItem(io, creator);
        twoPlayerGame.perform();
    }

    @Test
    public void userReplayMessageDisplayedIfOptionChosen() {
        PlayerCreator creator = new PlayerCreator(io);
        ReplayMenuItem replay = new ReplayMenuItem(io, creator);
        assertEquals("Replaying game...", replay.replayMessage());
    }

    @Test
    public void replaysPreviousGame() {
        ConsoleIO io = convertUserInput(new ByteArrayInputStream("1\n4\n3\n2\n3\n".getBytes()));
        PlayerCreator creator = new PlayerCreator(io);
        ReplayMenuItem replay = new ReplayMenuItem(io, creator);
        replay.perform();
        assertTrue(recordedOutput.toString().contains("Player X has won!"));
        assertTrue(recordedOutput.toString().contains("Replaying game..."));
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }
}
