package ttt.menu;

import org.junit.Test;
import ttt.inputOutput.ConsoleIO;
import ttt.menuitems.ExitMenuItem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class ExitMenuItemTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    ConsoleIO io = convertUserInput(new ByteArrayInputStream("3\n".getBytes()));

    @Test
    public void displaysExitMessageIfUserChoosesOption() {
        ExitMenuItem exit = new ExitMenuItem(io);
        exit.show();
        assertTrue(recordedOutput.toString().contains("Exit Game\n"));
    }
    
    @Test
    public void exitsGameIfUserChooses3() {
        ExitMenuItem exit = new ExitMenuItem(io);
        exit.perform();
        assertTrue(recordedOutput.toString().contains("Bye!"));
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }
}
