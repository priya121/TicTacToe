package ttt;

import org.junit.Assert;
import org.junit.Test;
import ttt.board.Board;
import ttt.inputOutput.IO;
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

import static ttt.Symbol.X;
import static ttt.Symbol.O;
import static ttt.Symbol.E;

public class GameTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    Board threeByThreeBoard = new Board(3);
    Board fourByFour = new Board(4);
    GameSetup expected = new GameSetup();

    @Test
    public void boardChangedWhenPlayerMakesMove() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, X, X, O, E, E, E, O, E));
        Game game = getGame(humanMoves(Arrays.asList("0", "2", "1")), computerMoves(Arrays.asList(3, 7)));
        game.gameLoop();
        Assert.assertEquals(expectedBoard, threeByThreeBoard.contentsOfBoard());
    }

    @Test
    public void switchesPlayersFromCrossToNought() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, X, E, E, X, E, O, O, O));
        Game game = getGame(humanMoves(Arrays.asList("0", "1", "4")), computerMoves(Arrays.asList(6, 7, 8)));
        game.gameLoop();
        Assert.assertEquals(expectedBoard, threeByThreeBoard.contentsOfBoard());
    }

    @Test
    public void switchesPlayersFromCrossToNoughtOnFourByFourBoard() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, X, X, X,
                                                                         E, E, O, O,
                                                                         O, E, E, E,
                                                                         E, E, E, E));
        Game game = getFourByFourGame(humanMoves(Arrays.asList("0", "1", "3", "2")), computerMoves(Arrays.asList(6, 7, 8)));
        game.gameLoop();
        Assert.assertEquals(expectedBoard, fourByFour.contentsOfBoard());
    }

    @Test
    public void asksUserToEnterDimensionOfBoard() {
        Game game = getGame(convertUserInput(new ByteArrayInputStream("2\n5\n8\n".getBytes())), computerMoves(Arrays.asList(1, 4, 7)));
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("game over"));
    }

    @Test
    public void tellsUserWhenGameOver() {
        Game game = getGame(convertUserInput(new ByteArrayInputStream("2\n5\n8\n".getBytes())), computerMoves(Arrays.asList(1, 4, 7)));
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("game over"));
    }

    @Test
    public void userMustEnterDigits() {
        Game game = getGame(convertUserInput(new ByteArrayInputStream("a\n1\n1\n0\n2\n".getBytes())), computerMoves(Arrays.asList(5, 7, 8)));
        game.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("Please enter a valid number"));
    }

    @Test
    public void computesCurrentPlayer() {
        Game game = getGame(convertUserInput(new ByteArrayInputStream("1\n0\n2\n".getBytes())), computerMoves(Arrays.asList(5, 7, 8)));
        game.gameLoop();
        Assert.assertEquals(X, game.getPlayer());
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private FakeComputerPlayer getFakeComputerMoves(List<Integer> input) {
        return new FakeComputerPlayer(input);
    }

    private Game getGame(IO humanMoves, FakeComputerPlayer computerMoves) {
        HumanPlayer human = new HumanPlayer(humanMoves, threeByThreeBoard);
        return new Game(threeByThreeBoard, humanMoves, computerMoves, human);
    }

    private Game getFourByFourGame(FakeIO humanMoves, FakeComputerPlayer computerMoves) {
        HumanPlayer human = new HumanPlayer(humanMoves, fourByFour);
        return new Game(fourByFour, humanMoves, computerMoves, human);
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }

    public ConsoleIO convertUserInput(InputStream userInput) {
        return new ConsoleIO(userInput, out);
    }

    public FakeComputerPlayer computerMoves(List<Integer> moves) {
        return getFakeComputerMoves(moves);
    }
}

