package ttt.player;

import org.junit.Assert;
import org.junit.Test;
import ttt.inputOutput.FakeIO;

import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.O;
import static ttt.Symbol.X;

public class SymbolChooserTest {

    @Test
    public void userChoosesACrossSymbol() {
        SymbolChooser symbolChooser = new SymbolChooser(userInput(Arrays.asList("X")));
       Assert.assertEquals(X, symbolChooser.getSymbol());
    }

    @Test
    public void userChoosesANoughtSymbol() {
        SymbolChooser symbolChooser = new SymbolChooser(userInput(Arrays.asList("O")));
       Assert.assertEquals(O, symbolChooser.getSymbol());
    }

    public FakeIO userInput(List<String> sizeChosen) {
        return getFakeIO(sizeChosen);
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }
}
