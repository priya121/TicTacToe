package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.Symbol;
import ttt.board.Board;

import java.util.Arrays;
import java.util.List;

public class RealComputerPlayerTest {
    Symbol nought = Symbol.NOUGHT;
    Symbol cross = Symbol.CROSS;
    Symbol empty = Symbol.EMPTY;

    @Test
        public void computerPlayerIndexGenerated() {
            List<Symbol> changedBoard = Arrays.asList(cross, empty, empty,
                                                      empty, empty, empty,
                                                      empty, empty, empty);
            Board board = new Board(changedBoard);
            RealComputerPlayer computer = new RealComputerPlayer(board);
            Assert.assertNotEquals(0, computer.move());
        }

    @Test
        public void validComputerMoveGenerated() {
            List<Symbol> changedBoard = Arrays.asList(nought, cross, nought,
                                                      nought, cross, nought,
                                                      nought, empty, cross);
            Board board = new Board(changedBoard);
            RealComputerPlayer computer = new RealComputerPlayer(board);
            Assert.assertEquals(7, computer.move());
        }
}
