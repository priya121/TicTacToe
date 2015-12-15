package ttt;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BoardTest {
    String empty = " ";
    String cross = "X";
    String nought = "O";
    List<String> emptyBoard = Arrays.asList(empty, empty, empty,
                                             empty, empty, empty,
                                            empty, empty, empty);
    List<String> expectedBoard = Arrays.asList(cross, empty, empty,
            empty, empty, empty,
            empty, empty, empty);
        Board currentBoard = new Board(emptyBoard);

        @Test
        public void setsUpThreeByThreeBoard() {
            Assert.assertEquals(expectedBoard, currentBoard.markPlayer(0, cross));
        }

        @Test
        public void returnsCurrentStateOfTheBoard() {
        List<String> boardAfterTwoPlayerMoves =
        Arrays.asList(cross, nought, empty,
                      empty, empty, empty,
                      empty, empty, empty);
            currentBoard.markPlayer(1, nought);
            Assert.assertEquals(boardAfterTwoPlayerMoves, currentBoard.markPlayer(0, cross));
        }

        @Test
        public void getsPositionOfCell() {
        List<String> boardAfterTwoMoves =
        Arrays.asList(cross, nought, empty,
                      empty, empty, empty,
                      empty, empty, empty);
        Board newBoard = new Board(boardAfterTwoMoves);
        Assert.assertEquals(cross, newBoard.get(0));
        }
}
