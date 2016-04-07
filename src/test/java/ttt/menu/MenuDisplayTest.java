package ttt.menu;

import org.junit.Test;
import ttt.MenuDisplay;
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
    MenuDisplay display = new MenuDisplay(convertUserInput(new ByteArrayInputStream("1\n1\n3\n0\n1\n2\n3\n4\n5\n6\n3\n".getBytes())));

    @Test
    public void displaysGreeting() {
        String greeting = "Welcome!\n";
        display.showGreeting();
        assertEquals(greeting, recordedOutput.toString());
    }

   @Test
   public void displaysTwoByTwoGameAndExitOnFirstOnFirstMenuDisplay() {
       String choice = "Please choose from the following options: \n\n" +
               "1) " + "\nTwo Player Game\n" +
               "2) " + "\nExit Game\n";
       display.showMenuItems();
       assertEquals(choice, recordedOutput.toString());
   }

    @Test
    public void displayMenuOfChoicesAfterFirstGamePlayed() {
        MenuDisplay display = new MenuDisplay(convertUserInput(new ByteArrayInputStream("1\n1\n3\n0\n1\n2\n3\n4\n5\n6\n3\n".getBytes())));
        String choice = "Please choose from the following options: \n\n" +
                "1) " + "\nTwo Player Game\n" +
                "2) " + "\nReplay Game\n" +
                "3) " + "\nExit Game\n";
        display.start();
        assertTrue(recordedOutput.toString().contains(choice));
    }

    @Test
    public void firstMenuItemIsTwoPlayer() {
        assertTrue(display.menuItems.get(0) instanceof TwoPlayerMenuItem);
    }

    @Test
    public void secondMenuItemIsReplayPlayer() {
        assertTrue(display.menuItems.get(1) instanceof ReplayMenuItem);
    }

    @Test
    public void thirdMenuItemIsExitMenuItem() {
        assertTrue(display.menuItems.get(2) instanceof ExitMenuItem);
    }

    @Test
    public void userEntering1ReturnsTwoPlayerGameType() {
        FakeIO io = new FakeIO(Arrays.asList("1", "1", "3", "0", "1", "2", "3", "4", "5", "6", "3"));
        MenuDisplay display = new MenuDisplay(io);
        assertTrue(display.chooseGameType() instanceof TwoPlayerMenuItem);
    }

    @Test
    public void allowsTheUserToPlayAgain() {
        display.start();
        assertTrue(recordedOutput.toString().contains("Player X has won!\n"));
    }

    @Test
    public void userEntering2ReturnsExitGameType() {
        FakeIO io = new FakeIO(Arrays.asList("2"));
        MenuDisplay display = new MenuDisplay(io);
        assertTrue(display.chooseGameType() instanceof ExitMenuItem);
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }
}
