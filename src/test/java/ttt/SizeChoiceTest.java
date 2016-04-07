package ttt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SizeChoiceTest {

    @Test
    public void sizeChoiceSavesBoardSizeAfterUserEntersSize() {
        SizeChoice sizeChoice = new SizeChoice();
        sizeChoice.setBoardSize(9);
        assertEquals(3, sizeChoice.getBoardSize());
    }
}
