package ttt.menu;

import org.junit.Test;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.FakeIO;
import ttt.menuitems.ExitMenuItem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExitMenuItemTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    private FakeIO io = getFakeIO(Arrays.asList("1", "3", "0", "1", "2", "3", "4", "5", "6"));

    @Test
    public void displaysExitMessageIfUserChoosesOption() {
        ExitMenuItem exit = new ExitMenuItem(io);
        assertEquals("Exiting game...\n" +
                     "Bye!", exit.exitMessage());
    }
    
    @Test
    public void exitsGameIfUserChooses3() {
        ConsoleIO io = convertUserInput(new ByteArrayInputStream("3\n".getBytes()));
        ExitMenuItem exit = new ExitMenuItem(io);
        exit.perform();
        assertTrue(recordedOutput.toString().contains("Bye!"));
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }
}
