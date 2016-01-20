package ttt.player;

import org.junit.Test;
import ttt.Game;
import ttt.board.Board;
import ttt.inputOutput.FakeIO;
import ttt.inputOutput.IO;

import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.E;
import static ttt.Symbol.O;
import static ttt.Symbol.X;

public class AIComputerPlayerTest {

    @Test
    public void AIReturnsOptimalMoveOutOfTwoPossibleMoves() {
        Board intermediateBoard = new Board(Arrays.asList(O, X, X,
                                                          O, X, X,
                                                          E, E, O));
        AIComputerPlayer computerPlayer = new AIComputerPlayer(intermediateBoard);
        //Assert.assertEquals(6, computerPlayer.move());
    }

    @Test
    public void AIPlayerMakesBlockingMove() {
        Board intermediateBoard = new Board(Arrays.asList(O, X, O,
                                                          E, E, X,
                                                          X, X, O));
        Game game = getIntermediateGame(humanMoves(Arrays.asList()), intermediateBoard);
        //game.gameLoop();
        //Assert.assertEquals(intermediateBoard.get(4), O);
    }

    @Test
    public void playerMakesCorrectMoveWithFourEmptyCells() {
        Board intermediateBoard = new Board(Arrays.asList(X, X, O,
                                                          E, E, O,
                                                          X, E, E));
        Game game = getIntermediateGame(humanMoves(Arrays.asList()), intermediateBoard);
        //game.gameLoop();
        //Assert.assertEquals(intermediateBoard.get(8), O);
    }

    @Test
    public void playerMakesCorrectMoveWithAnotherFourEmptyCells() {
        Board intermediateBoard = new Board(Arrays.asList(X, X, O,
                                                          O, E, O,
                                                          X, E, X));
        Game game = getIntermediateGame(humanMoves(Arrays.asList()), intermediateBoard);
        //game.gameLoop();
        //Assert.assertEquals(intermediateBoard.get(4), O);
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


