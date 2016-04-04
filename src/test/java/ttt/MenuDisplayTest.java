package ttt;

import org.junit.Test;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.FakeIO;
import ttt.menuitems.ExitMenuItem;
import ttt.menuitems.ReplayMenuItem;
import ttt.menuitems.TwoPlayerMenuItem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MenuDisplayTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    MenuDisplay display = new MenuDisplay(convertUserInput(new ByteArrayInputStream("1\n".getBytes())));

    @Test
    public void displaysGreeting() {
        String greeting = "Welcome! Please choose an option from the following list: \n";
        display.showGreeting();
        assertEquals(greeting, recordedOutput.toString());
    }

    @Test
    public void displayMenuOfChoices() {
        String choice = "Please choose from the following options: \n\n"  +
                          "1) Two Player Game\n" +
                          "2) Replay Game\n" +
                          "3) Exit Game\n";
        display.showChoices();
        assertEquals(choice, recordedOutput.toString());
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

    @Test
    public void userEntering1ReturnsTwoPlayerGameType() {
        FakeIO io = new FakeIO(Arrays.asList("1"));
        MenuDisplay display = new MenuDisplay(io);
        assertTrue(display.chooseGameType() instanceof TwoPlayerMenuItem);
    }

    @Test
    public void userEntering2ReturnsReplayGameType() {
        FakeIO io = new FakeIO(Arrays.asList("2"));
        MenuDisplay display = new MenuDisplay(io);
        assertTrue(display.chooseGameType() instanceof ReplayMenuItem);
    }

    @Test
    public void userEntering3ReturnsExitGameType() {
        FakeIO io = new FakeIO(Arrays.asList("3"));
        MenuDisplay display = new MenuDisplay(io);
        assertTrue(display.chooseGameType() instanceof ExitMenuItem);
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }
}
