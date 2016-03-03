package ttt;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static ttt.Symbol.E;

public class SetupBoardTest {

    public List<Symbol> emptyBoard(int height, int width) {
        List<Symbol> board = new ArrayList<Symbol>();
        for (int i = 0; i < height * width; i++) {
            board.add(E);
        }
        return board;
    }

    @Test
    public void returnsEmptyBoard() {
        SetupBoard newGame = new SetupBoard();
        List<Symbol> empty = emptyBoard(3, 3);
        Assert.assertEquals(empty, newGame.emptyBoard(3, 3));
    }
}
