package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.board.Board;

import static ttt.Symbol.CROSS;
import static ttt.Symbol.NOUGHT;

public class RealComputerPlayerTest {
    Board board = new Board();

    @Test
        public void computerPlayerIndexGenerated() {
            board.markPlayer(0, CROSS);
            RealComputerPlayer computer = new RealComputerPlayer(board);
            Assert.assertNotEquals(0, computer.move());
        }

    @Test
    public void validComputerMoveGenerated() {
        Board board = new Board();
        RealComputerPlayer computer = new RealComputerPlayer(board);
        Assert.assertTrue(computer.move() <= 8);
        Assert.assertTrue(computer.move() >= 0);
    }

    @Test
    public void returnsNoughtSymbolForComputePlayer() {
        Board board = new Board();
        RealComputerPlayer computer = new RealComputerPlayer(board);
        Assert.assertEquals(NOUGHT, computer.getSymbol());
    }
}
