package ttt.menu;

import org.junit.Test;
import ttt.menuitems.TwoPlayerMenuItem;
import ttt.inputOutput.FakeIO;
import ttt.player.AIComputerPlayer;
import ttt.player.HumanPlayer;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TwoPlayerMenuItemTest {
    private FakeIO io = getFakeIO(Arrays.asList("1", "3", "0", "1", "2", "3", "4", "5", "6"));
    TwoPlayerMenuItem twoPlayer = new TwoPlayerMenuItem(io);

    @Test
    public void asksUserTypeOfTwoPlayerGame() {
        TwoPlayerMenuItem twoPlayer = new TwoPlayerMenuItem(io);
        assertEquals("Hi, choose a two player game type (Enter 1 - 4): \n" +
                "1) Human vs Human \n" +
                "2) Human vs Computer \n" +
                "3) Computer vs Human \n" +
                "4) Computer vs Computer \n" +
                "Entering any other character will return a default Human v Human game:", twoPlayer.askTypeOfTwoPlayerGame());
    }

    @Test
    public void createsAHumanVHumanGame() {
        twoPlayer.show();
        twoPlayer.perform();
        assertTrue(twoPlayer.playerX() instanceof HumanPlayer);
        assertTrue(twoPlayer.playerO() instanceof HumanPlayer);
    }

    @Test
    public void createsAHumanVComputerGame() {
        FakeIO io = getFakeIO(Arrays.asList("2", "3", "0", "1", "2", "3", "4", "5", "6"));
        TwoPlayerMenuItem twoPlayer = new TwoPlayerMenuItem(io);
        twoPlayer.show();
        twoPlayer.perform();
        assertTrue(twoPlayer.playerX() instanceof HumanPlayer);
        assertTrue(twoPlayer.playerO() instanceof AIComputerPlayer);
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }
}
