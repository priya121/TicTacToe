package ttt.player;

import org.junit.Assert;
import org.junit.Test;

public class PlayerCreatorTest {

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
