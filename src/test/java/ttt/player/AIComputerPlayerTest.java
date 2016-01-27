package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.Game;
import ttt.board.Board;
import ttt.inputOutput.FakeIO;
import ttt.inputOutput.IO;

import java.util.Arrays;
import java.util.List;

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
        int[] result = computerPlayer.minimax(intermediateBoard, O);
        Assert.assertEquals(0, result[0]);
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
        //Assert.assertEquals(6, computerPlayer.move());
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

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private Game getIntermediateGame(IO humanMoves, Board board) {
        HumanPlayer human = new HumanPlayer(humanMoves, board);
        AIComputerPlayer computerPlayer = new AIComputerPlayer(board);
        return new Game(board, humanMoves, computerPlayer, human);
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }
}


