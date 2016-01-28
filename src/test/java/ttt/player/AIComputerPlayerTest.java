package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.board.Board;

import java.util.Arrays;

import static ttt.Symbol.*;

public class AIComputerPlayerTest {

    @Test
    public void placesAIInWinningPositionOnFirstRow() {
        Board intermediateBoard = new Board(Arrays.asList(O, O, E,
                                                          X, X, E,
                                                          X, E, E));
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard);
        Assert.assertEquals(2, computerPlayer.move());
    }

    @Test
    public void placesAIInWinningPositionOnSecondRow() {
        Board intermediateBoard = new Board(Arrays.asList(X, X, E,
                                                          O, O, E,
                                                          X, E, E));
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard);
        Assert.assertEquals(5, computerPlayer.move());
    }

    @Test
    public void placesAIInWinningPosition() {
        Board intermediateBoard = new Board(Arrays.asList(E, X, E,
                                                          X, X, E,
                                                          O, O, E));
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard);
        Assert.assertEquals(8, computerPlayer.move());
    }

    @Test
    public void placesAInWinningCell() {
        Board intermediateBoard = new Board(Arrays.asList(E, X, X,
                                                          E, O, O,
                                                          E, E, O));
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard);
        Assert.assertEquals(3, computerPlayer.move());
    }

    @Test
    public void AIPlayerMakesBlockingMove() {
        Board intermediateBoard = new Board(Arrays.asList(O, X, O,
                                                          E, E, X,
                                                          X, X, O));
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard);
        Assert.assertEquals(4, computerPlayer.move());
    }

    @Test
    public void AIMakesWinningMoveInFirstColumn() {
        Board intermediateBoard = new Board(Arrays.asList(O, X, X,
                                                          O, E, E,
                                                          E, X, E));
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard);
        Assert.assertEquals(6, computerPlayer.move());
    }

    @Test
    public void playerMakesCorrectMoveWithFourEmptyCells() {
        Board intermediateBoard = new Board(Arrays.asList(E, X, O,
                                                          E, E, O,
                                                          X, X, E));
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard);
        Assert.assertEquals(8, computerPlayer.move());
    }

    @Test
    public void playerMakesCorrectMoveWithAnotherFourEmptyCells() {
        Board intermediateBoard = new Board(Arrays.asList(X, X, O,
                                                          O, E, O,
                                                          X, E, X));
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard);
        Assert.assertEquals(4, computerPlayer.move());
    }
}


