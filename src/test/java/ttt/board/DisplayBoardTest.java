package ttt.board;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import ttt.Symbol;
import java.util.List;

import ttt.Symbol;
import static ttt.Symbol.EMPTY;
import static ttt.Symbol.CROSS;
import static ttt.Symbol.NOUGHT;

public class DisplayBoardTest {

    @Test
    public void displaysEmptyBoard() {
        String expectedBoard =   " -  -  - \n"
                               + " -  -  - \n"
                               + " -  -  - \n";
        List<Symbol> emptyBoard = Arrays.asList(EMPTY, EMPTY, EMPTY,
                                                EMPTY, EMPTY, EMPTY,
                                                EMPTY, EMPTY, EMPTY);
        DisplayBoard initialDisplay = new DisplayBoard(emptyBoard);
        Assert.assertEquals(expectedBoard, initialDisplay.showBoard());
    }

    @Test
        public void displaysCurrentBoard() {
            String expectedBoard =    " O  X  O \n"
                                    + " X  O  X \n"
                                    + " -  X  X \n";
            List<Symbol> intermediateBoard = Arrays.asList(NOUGHT, CROSS, NOUGHT,
                                                            CROSS, NOUGHT, CROSS,
                                                            EMPTY, CROSS, CROSS);
            DisplayBoard currentDisplay = new DisplayBoard(intermediateBoard);
            Assert.assertEquals(expectedBoard, currentDisplay.showBoard());
        }
}
