package ttt;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DisplayBoardTest {
    String cross = "X";
    String nought = "O";
    String empty = "-";

    @Test
    public void displaysEmptyBoard() {
        String empty = " ,";
        String expectedBoard =   "  ,  ,  ,  ,  ,  ,  ,  ,  ,";
        List<String> emptyBoard = Arrays.asList(empty, empty, empty,
                empty, empty, empty,
                empty, empty, empty);
        DisplayBoard initialDisplay = new DisplayBoard(emptyBoard);
        Assert.assertEquals(expectedBoard, initialDisplay.showBoard());
    }

    @Test
        public void displaysCurrentBoard() {
            String expectedBoard =    " O  X  O \n"
                                    + " X  O  X \n"
                                    + " -  X  X \n";
            List<String> intermediateBoard = Arrays.asList(nought, cross, nought,
                                                            cross, nought, cross,
                                                            empty, cross, cross);
            DisplayBoard currentDisplay = new DisplayBoard(intermediateBoard);
            System.out.print(expectedBoard);
            System.out.print(currentDisplay.showBoard());
            Assert.assertEquals(expectedBoard, currentDisplay.showBoard());
        }
}
