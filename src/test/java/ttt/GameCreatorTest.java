package ttt;

import org.junit.Test;
import ttt.inputOutput.ConsoleIO;
import ttt.player.AIComputerPlayer;
import ttt.player.HumanPlayer;
import ttt.player.PlayerCreator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameCreatorTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    ConsoleIO input = convertUserInput(new ByteArrayInputStream(("1\n3\n").getBytes()));
    PlayerCreator creator = new PlayerCreator(input);
    TwoVsTwoGameCreator newSetup = new TwoVsTwoGameCreator(input, creator);

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }

    @Test
    public void displaysGameTypesToUser() {
        newSetup.displayMessage();
        assertEquals("Hi, choose a game type (Enter 1 - 4): \n" +
                "1) Human vs Human \n" +
                "2) Human vs Computer \n" +
                "3) Computer vs Human \n" +
                "4) Computer vs Computer \n" +
                "Entering any other character will return a default Human v Human game:\n", recordedOutput.toString());
    }

    @Test
    public void createsTheRightTypeOfGameIfUserChoosesOne() {
        TwoVsTwoGameCreator newSetup = new TwoVsTwoGameCreator(input, creator);
        Game game = newSetup.createGame();
        assertTrue(game.playerOne instanceof HumanPlayer);
        assertTrue(game.playerTwo instanceof HumanPlayer);
    }

    @Test
    public void createsTheRightTypeOfGameIfUserChoosesTwo() {
        ConsoleIO input = convertUserInput(new ByteArrayInputStream(("2\n3\n").getBytes()));
        PlayerCreator creator = new PlayerCreator(input);
        TwoVsTwoGameCreator newSetup = new TwoVsTwoGameCreator(input, creator);
        Game game = newSetup.createGame();
        assertTrue(game.playerOne instanceof HumanPlayer);
        assertTrue(game.playerTwo instanceof AIComputerPlayer);
    }

    @Test
    public void createsTheRightGameIfUserChoosesThree() {
        ConsoleIO input = convertUserInput(new ByteArrayInputStream(("3\n3\n").getBytes()));
        PlayerCreator creator = new PlayerCreator(input);
        TwoVsTwoGameCreator newSetup = new TwoVsTwoGameCreator(input, creator);
        Game game = newSetup.createGame();
        assertTrue(game.playerOne instanceof AIComputerPlayer);
        assertTrue(game.playerTwo instanceof HumanPlayer);
    }

    @Test
    public void createsTheRightGameIfUserChoosesFour() {
        ConsoleIO input = convertUserInput(new ByteArrayInputStream(("4\n3\n").getBytes()));
        PlayerCreator creator = new PlayerCreator(input);
        TwoVsTwoGameCreator newSetup = new TwoVsTwoGameCreator(input, creator);
        Game game = newSetup.createGame();
        assertTrue(game.playerOne instanceof AIComputerPlayer);
        assertTrue(game.playerTwo instanceof AIComputerPlayer);
    }
}
