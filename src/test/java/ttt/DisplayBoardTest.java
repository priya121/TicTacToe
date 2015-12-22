package ttt;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;

import java.util.Arrays;
import java.util.List;

public class DisplayBoardTest {
    Symbol empty = Symbol.EMPTY;
    Symbol cross = Symbol.CROSS;
    Symbol nought = Symbol.NOUGHT;

    @Ignore
    @Test
    public void displaysEmptyBoard() {
        String expectedBoard =   " -  -  -  \n"
                               + " -  -  -  \n"
                               + " -  -  -  \n";
        List<Symbol> emptyBoard = Arrays.asList(empty, empty, empty,
                                                empty, empty, empty,
                                                empty, empty, empty);
        DisplayBoard initialDisplay = new DisplayBoard(emptyBoard);
        Assert.assertEquals(expectedBoard, initialDisplay.showBoard());
    }

    @Ignore
    @Test
        public void displaysCurrentBoard() {
            String expectedBoard =    " O  X  O \n"
                                    + " X  O  X \n"
                                    + " -  X  X \n";
            List<Symbol> intermediateBoard = Arrays.asList(nought, cross, nought,
                                                            cross, nought, cross,
                                                            empty, cross, cross);
            DisplayBoard currentDisplay = new DisplayBoard(intermediateBoard);
            Assert.assertEquals(expectedBoard, currentDisplay.showBoard());
        }
}
