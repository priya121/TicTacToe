package ttt.board;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import ttt.Symbol;
import java.util.List;

import static ttt.Symbol.E;
import static ttt.Symbol.X;
import static ttt.Symbol.O;

public class DisplayBoardTest {

    @Test
    public void displaysEmptyBoard() {
        String expectedBoard =   " -  -  - \n"
                               + " -  -  - \n"
                               + " -  -  - \n";
        List<Symbol> emptyBoard = Arrays.asList(E, E, E,
                E, E, E,
                E, E, E);
        DisplayBoard initialDisplay = new DisplayBoard(emptyBoard);
        Assert.assertEquals(expectedBoard, initialDisplay.showBoard());
    }

    @Test
        public void displaysCurrentBoard() {
            String expectedBoard =    " O  X  O \n"
                                    + " X  O  X \n"
                                    + " -  X  X \n";
            List<Symbol> intermediateBoard = Arrays.asList(O, X, O,
                    X, O, X,
                    E, X, X);
            DisplayBoard currentDisplay = new DisplayBoard(intermediateBoard);
            Assert.assertEquals(expectedBoard, currentDisplay.showBoard());
        }
}
