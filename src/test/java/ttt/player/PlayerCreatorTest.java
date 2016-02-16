package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.inputOutput.ConsoleIO;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class PlayerCreatorTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }

    @Test
    public void createsHumanPlayerAsPlayerOneIfUserEntersOne() {
        PlayerCreator playerCreator = new PlayerCreator(null, "1");
        Assert.assertTrue(playerCreator.createX() instanceof HumanPlayer);
    }

    @Test
    public void createsHumanPlayerAsPlayerOneIfUserEntersTwo() {
        PlayerCreator playerCreator = new PlayerCreator(null, "2");
        Assert.assertTrue(playerCreator.createX() instanceof HumanPlayer);
    }

    @Test
    public void createsPlayerOneAsComputerIfUserEntersThree() {
        PlayerCreator playerCreator = new PlayerCreator(null, "3");
        Assert.assertTrue(playerCreator.createX() instanceof AIComputerPlayer);
    }

    @Test
    public void createsPlayerOneAsComputerPlayerIfUserEntersFour() {
        PlayerCreator playerCreator = new PlayerCreator(null, "4");
        Assert.assertTrue(playerCreator.createX() instanceof AIComputerPlayer);
    }
}
