package ttt.board;

import org.junit.Assert;
import org.junit.Test;
import ttt.Game;
import ttt.SetupBoard;
import ttt.Symbol;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.FakeIO;
import ttt.player.FakeComputerPlayer;
import ttt.player.HumanPlayer;

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
    Board board = new Board(3);

    @Test
    public void returnsAnThreeByThreeEmptyBoard() {
        List<Symbol> emptyCells = expected.placeSymbols(Arrays.asList(E, E, E, E,
                    E, E, E, E,
                    E, E, E, E,
                    E, E, E, E));
        Board expectedBoard = new Board(emptyCells);
        BoardChooser boardCreator = new BoardChooser(userInput(Arrays.asList("4")));
        Assert.assertEquals(expectedBoard.contentsOfBoard(), boardCreator.create().contentsOfBoard());
    }

    @Test
    public void playerCanOnlyEnterDigit() {
        Game game = getGame("a\n3\nX\n1\n0\n2\nN\n", computerMoves(Arrays.asList(5, 7, 8)));
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("Please enter a valid number"));
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }

    private Game getGame(String humanMoves, FakeComputerPlayer computerMoves) {
        ConsoleIO io = convertUserInput(new ByteArrayInputStream(humanMoves.getBytes()));
        HumanPlayer human = new HumanPlayer(io, board);
        return new Game(board, io, human, computerMoves);
    }

    private FakeComputerPlayer getFakeComputerMoves(List<Integer> input) {
        FakeIO fakeInput = getFakeIO(Arrays.asList("X"));
        HumanPlayer humanPlayer = new HumanPlayer(fakeInput, board);
        return new FakeComputerPlayer(input, humanPlayer);
    }

    public FakeIO userInput(List<String> sizeChosen) {
        return getFakeIO(sizeChosen);
    }

    public FakeComputerPlayer computerMoves(List<Integer> moves) {
        return getFakeComputerMoves(moves);
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }
}


