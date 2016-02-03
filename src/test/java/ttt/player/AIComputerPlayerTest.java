package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.board.Board;
import ttt.inputOutput.FakeIO;

import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.*;

public class AIComputerPlayerTest {

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    @Test
    public void placesAIInWinningPositionOnFirstRow() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("X", "X"));
        Board intermediateBoard = new Board(Arrays.asList(O, O, E,
                                                          X, X, E,
                                                          X, E, E));
        HumanPlayer human = new HumanPlayer(fakeInput, intermediateBoard);
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard, human);
        Assert.assertEquals(2, computerPlayer.move());
    }

    @Test
    public void placesAIInWinningPositionOnSecondRow() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("X", "X"));
        Board intermediateBoard = new Board(Arrays.asList(X, X, E,
                                                          O, O, E,
                                                          X, E, E));
        HumanPlayer human = new HumanPlayer(fakeInput, intermediateBoard);
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard, human);
        Assert.assertEquals(5, computerPlayer.move());
    }

    @Test
    public void placesAIInWinningPosition() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("X", "X"));
        Board intermediateBoard = new Board(Arrays.asList(E, X, E,
                                                          X, X, E,
                                                          O, O, E));
        HumanPlayer human = new HumanPlayer(fakeInput, intermediateBoard);
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard, human);
        Assert.assertEquals(8, computerPlayer.move());
    }

    @Test
    public void placesAInWinningCell() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("X", "X"));
        Board intermediateBoard = new Board(Arrays.asList(E, X, X,
                                                          E, O, O,
                                                          X, E, E));
        HumanPlayer human = new HumanPlayer(fakeInput, intermediateBoard);
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard, human);
        Assert.assertEquals(3, computerPlayer.move());
    }

    @Test
    public void AIPlayerMakesBlockingMove() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("X", "X"));
        Board intermediateBoard = new Board(Arrays.asList(O, X, O,
                                                          E, E, X,
                                                          X, X, O));
        HumanPlayer human = new HumanPlayer(fakeInput, intermediateBoard);
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard, human);
        Assert.assertEquals(4, computerPlayer.move());
    }

    @Test
    public void AIMakesWinningMoveInFirstColumn() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("X", "X"));
        Board intermediateBoard = new Board(Arrays.asList(O, X, X,
                                                          O, E, E,
                                                          E, X, E));
        HumanPlayer human = new HumanPlayer(fakeInput, intermediateBoard);
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard, human);
        Assert.assertEquals(6, computerPlayer.move());
    }

    @Test
    public void playerMakesCorrectMoveWithFourEmptyCells() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("X", "X"));
        Board intermediateBoard = new Board(Arrays.asList(E, X, O,
                                                          E, E, O,
                                                          X, X, E));
        HumanPlayer human = new HumanPlayer(fakeInput, intermediateBoard);
        human.setPlayerSymbol();
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard, human);
        Assert.assertEquals(8, computerPlayer.move());
    }

    @Test
    public void playerMakesCorrectMoveWithAnotherFourEmptyCells() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("X", "X"));
        Board intermediateBoard = new Board(Arrays.asList(X, X, O,
                                                          O, E, O,
                                                          X, E, X));
        HumanPlayer human = new HumanPlayer(fakeInput, intermediateBoard);
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard, human);
        Assert.assertEquals(4, computerPlayer.move());
    }
}


