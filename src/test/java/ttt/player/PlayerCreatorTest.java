package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.inputOutput.FakeIO;

import java.util.Arrays;
import java.util.List;

public class PlayerCreatorTest {

    @Test
    public void createsHumanPlayerAsPlayerOneIfUserEntersOne() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("1"));
        PlayerCreator playerCreator = new PlayerCreator(fakeInput);
        playerCreator.createPlayers(fakeInput);
        Assert.assertTrue(playerCreator.createX() instanceof HumanPlayer);
    }

    @Test
    public void createsHumanPlayerAsPlayerOneIfUserEntersTwo() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("2"));
        PlayerCreator playerCreator = new PlayerCreator(fakeInput);
        playerCreator.createPlayers(fakeInput);
        Assert.assertTrue(playerCreator.createX() instanceof HumanPlayer);
    }

    @Test
    public void createsPlayerOneAsComputerIfUserEntersThree() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("3"));
        PlayerCreator playerCreator = new PlayerCreator(fakeInput);
        playerCreator.createPlayers(fakeInput);
        Assert.assertTrue(playerCreator.createX() instanceof AIComputerPlayer);
    }

    @Test
    public void createsPlayerOneAsComputerPlayerIfUserEntersFour() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("4"));
        PlayerCreator playerCreator = new PlayerCreator(fakeInput);
        playerCreator.createPlayers(fakeInput);
        Assert.assertTrue(playerCreator.createX() instanceof AIComputerPlayer);
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }
}
