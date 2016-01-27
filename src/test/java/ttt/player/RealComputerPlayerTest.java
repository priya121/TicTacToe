package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.board.Board;

import static ttt.Symbol.X;
import static ttt.Symbol.O;

public class RealComputerPlayerTest {
    Board board = new Board(3);

    @Test
        public void computerPlayerIndexGenerated() {
            board.markPlayer(0, X);
            RealComputerPlayer computer = new RealComputerPlayer(board);
            Assert.assertNotEquals(0, computer.move());
        }

    @Test
    public void validComputerMoveGenerated() {
        Board board = new Board(3);
        RealComputerPlayer computer = new RealComputerPlayer(board);
        Assert.assertTrue(computer.move() <= 8);
        Assert.assertTrue(computer.move() >= 0);
    }

    @Test
    public void returnsNoughtSymbolForComputePlayer() {
        RealComputerPlayer computer = new RealComputerPlayer(board);
        Assert.assertEquals(O, computer.getSymbol());
    }
}
