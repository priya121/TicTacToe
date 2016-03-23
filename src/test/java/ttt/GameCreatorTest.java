package ttt;

import org.junit.Test;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.ReplayIO;
import ttt.player.AIComputerPlayer;
import ttt.player.HumanPlayer;

import java.io.*;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class GameCreatorTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    File file = new File("/Users/priyapatil/TTT/gameLog.txt");
    ReplayIO replayIO = new ReplayIO(file, out);
    GameCreator newSetup = new GameCreator(convertUserInput(new ByteArrayInputStream(("1\n3\n").getBytes())), replayIO);

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
        GameCreator newSetup = new GameCreator(convertUserInput(new ByteArrayInputStream(("3\n1\n").getBytes())), replayIO);
        Game game = newSetup.createGame();
        assertTrue(game.playerOne instanceof HumanPlayer);
        assertTrue(game.playerTwo instanceof HumanPlayer);
    }

    @Test
    public void createsTheRightTypeOfGameIfUserChoosesTwo() {
        GameCreator newSetup = new GameCreator(convertUserInput(new ByteArrayInputStream(("3\n2\n").getBytes())), replayIO);
        Game game = newSetup.createGame();
        assertTrue(game.playerOne instanceof HumanPlayer);
        assertTrue(game.playerTwo instanceof AIComputerPlayer);
    }

    @Test
    public void createsTheRightGameIfUserChoosesThree() {
        GameCreator newSetup = new GameCreator(convertUserInput(new ByteArrayInputStream(("3\n3\n").getBytes())), replayIO);
        Game game = newSetup.createGame();
        assertTrue(game.playerOne instanceof AIComputerPlayer);
        assertTrue(game.playerTwo instanceof HumanPlayer);
    }

    @Test
    public void createsTheRightGameIfUserChoosesFour() {
        GameCreator gameCreator = new GameCreator(convertUserInput(new ByteArrayInputStream(("3\n4\n").getBytes())), replayIO);
        Game game = gameCreator.createGame();
        assertTrue(game.playerOne instanceof AIComputerPlayer);
        assertTrue(game.playerTwo instanceof AIComputerPlayer);
    }
}
