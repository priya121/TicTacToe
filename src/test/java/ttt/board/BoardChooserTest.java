package ttt.board;

import org.junit.Assert;
import org.junit.Test;
import ttt.Game;
import ttt.GameCreator;
import ttt.SetupBoard;
import ttt.Symbol;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.FakeIO;

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
        Game game = getGame("a\n3\n1\n6\n1\n7\n0\n8\n2\nN\n");
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("Please enter a valid number"));
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }

    private Game getGame(String humanMoves) {
        ConsoleIO io = convertUserInput(new ByteArrayInputStream(humanMoves.getBytes()));
        return new GameCreator(io).createGame();
    }

    public FakeIO userInput(List<String> sizeChosen) {
        return getFakeIO(sizeChosen);
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }
}


