package ttt.menu;

import org.junit.Test;
import ttt.SizeChoice;
import ttt.inputOutput.ConsoleIO;
import ttt.menuitems.TwoPlayerMenuItem;
import ttt.player.PlayerCreator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class TwoPlayerMenuItemTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    @Test
    public void createsAHumanVHumanGame() {
        ConsoleIO io = convertUserInput(new ByteArrayInputStream("1\n3\n0\n1\n2\n3\n4\n5\n6\n".getBytes()));
        PlayerCreator creator = new PlayerCreator(io);
        SizeChoice size = new SizeChoice();
        TwoPlayerMenuItem twoPlayer = new TwoPlayerMenuItem(io, creator, size);
        twoPlayer.perform();
        assertTrue(recordedOutput.toString().contains("Player X has won!"));
    }

    @Test
    public void createsAComputerVsComputerGame() {
        ConsoleIO io = convertUserInput(new ByteArrayInputStream("4\n3\n".getBytes()));
        PlayerCreator creator = new PlayerCreator(io);
        SizeChoice size = new SizeChoice();
        TwoPlayerMenuItem twoPlayer = new TwoPlayerMenuItem(io, creator, size);
        twoPlayer.perform();
        assertTrue(recordedOutput.toString().contains("It's a draw!\n"));
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }
}
