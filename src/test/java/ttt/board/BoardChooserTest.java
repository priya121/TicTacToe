package ttt.board;

import org.junit.Assert;
import org.junit.Test;
import ttt.Game;
import ttt.SetupBoard;
import ttt.Symbol;
import ttt.TwoVsTwoGameCreator;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.FakeIO;
import ttt.player.PlayerCreator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.E;

public class BoardChooserTest {
    SetupBoard expected = new SetupBoard();
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    @Test
    public void returnsAnThreeByThreeEmptyBoard() {
        List<Symbol> emptyCells = expected.placeSymbols(Arrays.asList(E, E, E, E,
                                                                      E, E, E, E,
                                                                      E, E, E, E,
                                                                      E, E, E, E));
        Board expectedBoard = new Board(emptyCells);
        BoardCreator boardCreator = new BoardCreator(userInput(Arrays.asList("4")));
        Assert.assertEquals(expectedBoard.contentsOfBoard(), boardCreator.create().contentsOfBoard());
    }

    @Test
    public void playerCanOnlyEnterDigit() {
        Game game = getGame("1\na\n3\n6\n1\n7\n0\n8\n2\n");
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("Please enter a valid number"));
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }

    private Game getGame(String humanMoves) {
        ConsoleIO io = convertUserInput(new ByteArrayInputStream(humanMoves.getBytes()));
        PlayerCreator creator = new PlayerCreator(io);
        return new TwoVsTwoGameCreator(io, creator).createGame();
    }

    public FakeIO userInput(List<String> sizeChosen) {
        return getFakeIO(sizeChosen);
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }
}


