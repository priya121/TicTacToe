package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.board.Board;
import ttt.inputOutput.FakeIO;

import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.O;
import static ttt.Symbol.X;

public class RandomComputerPlayerTest {
    Board board = new Board(3);

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    @Test
    public void computerPlayerIndexGenerated() {
        board.markPlayer(0, X);
        FakeIO fakeInput = getFakeIO(Arrays.asList("O"));
        HumanPlayer human = new HumanPlayer(fakeInput, board);
        RandomComputerPlayer computer = new RandomComputerPlayer(board, human);
        Assert.assertNotEquals(0, computer.move());
    }

    @Test
    public void validComputerMoveGenerated() {
        Board board = new Board(3);
        FakeIO fakeInput = getFakeIO(Arrays.asList("O"));
        HumanPlayer human = new HumanPlayer(fakeInput, board);
        RandomComputerPlayer computer = new RandomComputerPlayer(board, human);
        Assert.assertTrue(computer.move() <= 8);
        Assert.assertTrue(computer.move() >= 0);
    }

    @Test
    public void returnsNoughtSymbolForComputerPlayer() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("X"));
        HumanPlayer human = new HumanPlayer(fakeInput, board);
        human.setPlayerSymbol();
        RandomComputerPlayer computer = new RandomComputerPlayer(board, human);
        Assert.assertEquals(O, computer.calculateOwnSymbol());
    }

    @Test
    public void returnCrossSymbolForComputerPlayer() {
        FakeIO fakeInput = getFakeIO(Arrays.asList("O"));
        HumanPlayer human = new HumanPlayer(fakeInput, board);
        human.setPlayerSymbol();
        RandomComputerPlayer computer = new RandomComputerPlayer(board, human);
        Assert.assertEquals(X, computer.calculateOwnSymbol());
    }
}
