package ttt;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class ComputerPlayerTest {
    Symbol nought = Symbol.NOUGHT;
    Symbol cross = Symbol.CROSS;
    Symbol empty = Symbol.EMPTY;
    List<Symbol> emptyBoard = Arrays.asList(empty, empty, empty,
                                            empty, empty, empty,
                                            empty, empty, empty);
    Board board = new Board(emptyBoard);

    @Test
        public void computerPlayerIndexGenerated() {
            ComputerPlayer computer = new ComputerPlayer(0, board);
            Assert.assertEquals(0, computer.numberGenerated());
        }

    @Test
        public void computerPlayerMarksBoard() {
            List<Symbol> changedBoard = Arrays.asList(nought, empty, empty,
                                                      empty, empty, empty,
                                                      empty, empty, empty);
            ComputerPlayer computer = new ComputerPlayer(0, board);
            computer.markBoard();
            Assert.assertEquals(changedBoard, board.getCurrentBoard());
        }
}

