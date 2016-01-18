package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.Symbol;
import ttt.board.Board;

import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.CROSS;
import static ttt.Symbol.NOUGHT;
import static ttt.Symbol.EMPTY;

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
