package ttt.menu;

import org.junit.Test;
import ttt.TwoPlayerMenuItem;
import ttt.inputOutput.FakeIO;
import ttt.player.HumanPlayer;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TwoPlayerMenuItemTest {
    private FakeIO io = getFakeIO(Arrays.asList("1"));
    TwoPlayerMenuItem twoPlayer = new TwoPlayerMenuItem(io);

    @Test
    public void createsAHumanVHumanGame() {
        assertTrue(twoPlayer.playerX() instanceof HumanPlayer);
        assertTrue(twoPlayer.playerO() instanceof HumanPlayer);
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }
}
