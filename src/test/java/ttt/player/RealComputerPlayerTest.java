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

    @Test
        public void computerPlayerIndexGenerated() {
            List<Symbol> changedBoard = Arrays.asList(CROSS, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY);
            Board board = new Board(changedBoard);
            RealComputerPlayer computer = new RealComputerPlayer(board);
            Assert.assertNotEquals(0, computer.move());
        }

    @Test
    public void validComputerMoveGenerated() {
        List<Symbol> changedBoard = Arrays.asList(NOUGHT, CROSS, NOUGHT,
                NOUGHT, CROSS, NOUGHT,
                NOUGHT, EMPTY, CROSS);
        Board board = new Board(changedBoard);
        RealComputerPlayer computer = new RealComputerPlayer(board);
        Assert.assertEquals(7, computer.move());
    }

    @Test
    public void returnsNoughtSymbolForComputePlayer() {
        List<Symbol> changedBoard = Arrays.asList(NOUGHT, CROSS, NOUGHT,
                                                  NOUGHT, CROSS, NOUGHT,
                                                  NOUGHT, EMPTY, CROSS);
        Board board = new Board(changedBoard);
        RealComputerPlayer computer = new RealComputerPlayer(board);
        Assert.assertEquals(NOUGHT, computer.getSymbol());
    }
}
