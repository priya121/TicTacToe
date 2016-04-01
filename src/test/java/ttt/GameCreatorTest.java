package ttt;

import org.junit.Test;
import ttt.inputOutput.ConsoleIO;
import ttt.player.AIComputerPlayer;
import ttt.player.HumanPlayer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameCreatorTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    GameCreator newSetup = new GameCreator(convertUserInput(new ByteArrayInputStream(("1\n3\n").getBytes())));

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
        GameCreator newSetup = new GameCreator(convertUserInput(new ByteArrayInputStream(("3\n1\n").getBytes())));
        Game game = newSetup.createGame();
        assertTrue(game.playerOne instanceof HumanPlayer);
        assertTrue(game.playerTwo instanceof HumanPlayer);
    }

    @Test
    public void createsTheRightTypeOfGameIfUserChoosesTwo() {
        GameCreator newSetup = new GameCreator(convertUserInput(new ByteArrayInputStream(("3\n2\n").getBytes())));
        Game game = newSetup.createGame();
        assertTrue(game.playerOne instanceof HumanPlayer);
        assertTrue(game.playerTwo instanceof AIComputerPlayer);
    }

    @Test
    public void createsTheRightGameIfUserChoosesThree() {
        GameCreator newSetup = new GameCreator(convertUserInput(new ByteArrayInputStream(("3\n3\n").getBytes())));
        Game game = newSetup.createGame();
        assertTrue(game.playerOne instanceof AIComputerPlayer);
        assertTrue(game.playerTwo instanceof HumanPlayer);
    }

    @Test
    public void createsTheRightGameIfUserChoosesFour() {
        GameCreator gameCreator = new GameCreator(convertUserInput(new ByteArrayInputStream(("3\n4\n").getBytes())));
        Game game = gameCreator.createGame();
        assertTrue(game.playerOne instanceof AIComputerPlayer);
        assertTrue(game.playerTwo instanceof AIComputerPlayer);
    }
}
