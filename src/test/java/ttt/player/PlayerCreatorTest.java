package ttt;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import ttt.inputOutput.IO;
import ttt.player.*;
import ttt.inputOutput.ConsoleIO;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

import static ttt.GameType.*;

public class PlayerCreatorTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }

    @Test
    public void createsHumanPlayerAsPlayerOneIfUserEntersOne() {
        PlayerCreator playerCreator = new PlayerCreator(convertUserInput(new ByteArrayInputStream(("1\n").getBytes())));
        Assert.assertTrue(playerCreator.playerOne(HvH) instanceof HumanPlayer);
    }

        @Test
        public void createsHumanPlayerAsPlayerOneIfUserEntersTwo() {
        PlayerCreator playerCreator = new PlayerCreator(convertUserInput(new ByteArrayInputStream(("2\n").getBytes())));
        Assert.assertTrue(playerCreator.playerOne(HvC) instanceof HumanPlayer);
        }

        @Test
        public void createsPlayerOneAsComputerIfUserEntersThree() {
            PlayerCreator playerCreator = new PlayerCreator(convertUserInput(new ByteArrayInputStream(("2\n").getBytes())));
            Assert.assertTrue(playerCreator.playerOne(CvH) instanceof ComputerPlayer);
        }

        @Test
        public void createsPlayerOneAsComputerPlayerIfUserEntersFour() {
            PlayerCreator playerCreator = new PlayerCreator(convertUserInput(new ByteArrayInputStream(("4\n").getBytes())));
            Assert.assertTrue(playerCreator.playerOne(CvC) instanceof ComputerPlayer);
        }
}
