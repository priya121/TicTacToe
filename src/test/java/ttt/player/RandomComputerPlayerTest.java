package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.board.Board;

import static ttt.Symbol.X;

public class RandomComputerPlayerTest {
    Board board = new Board(3);

    @Test
    public void computerPlayerIndexGenerated() {
        board.markPlayer(0, X);
        RandomComputerPlayer computer = new RandomComputerPlayer(board);
        Assert.assertNotEquals(0, computer.move(board));
    }

    @Test
    public void validComputerMoveGenerated() {
        Board board = new Board(3);
        RandomComputerPlayer computer = new RandomComputerPlayer(board);
        Assert.assertTrue(computer.move(board) <= 8);
        Assert.assertTrue(computer.move(board) >= 0);
    }
}
