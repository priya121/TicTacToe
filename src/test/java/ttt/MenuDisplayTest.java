package ttt;

import org.junit.Test;
import ttt.inputOutput.ConsoleIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MenuDisplayTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    MenuDisplay display = new MenuDisplay(convertUserInput(new ByteArrayInputStream("1\n1\n".getBytes())));

    @Test
    public void displaysGreeting() {
        String greeting = "Welcome! Please choose an option from the following list: \n";
        display.showGreeting();
        assertEquals(greeting, recordedOutput.toString());
    }

    @Test
    public void firstMenuItemIsTwoPlayer() {
        assertEquals(display.menuItems.get(0).getClass(), TwoPlayerMenuItem.class);
    }

    @Test
    public void secondMenuItemIsReplayPlayer() {
        assertEquals(display.menuItems.get(1).getClass(), ReplayMenuItem.class);
    }
    
    @Test
    public void thirdMenuItemIsExitMenuItem() {
        assertEquals(display.menuItems.get(2).getClass(), ExitMenuItem.class);
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }
}
