package ttt.board;

import org.junit.Assert;
import org.junit.Test;

import ttt.inputOutput.FakeIO;
import ttt.Symbol;
import ttt.GameSetup;

import java.util.Arrays;

import java.util.List;
import static ttt.Symbol.*;

public class BoardChooserTest {
    GameSetup expected = new GameSetup();

    @Test
    public void returnsAnThreeByThreeEmptyBoard() {
        List<Symbol> emptyCells = expected.placeSymbols(Arrays.asList(E, E, E, E,
                    E, E, E, E,
                    E, E, E, E,
                    E, E, E, E));
        Board expectedBoard = new Board(emptyCells);
        BoardChooser boardCreator = new BoardChooser(userInput(Arrays.asList("4")));
        Assert.assertEquals(expectedBoard.contentsOfBoard(), boardCreator.create().contentsOfBoard());
    }

    public FakeIO userInput(List<String> sizeChosen) {
        return getFakeIO(sizeChosen);
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }
}


