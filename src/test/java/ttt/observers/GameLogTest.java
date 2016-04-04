package ttt.observers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameLogTest {

    @Test
    public void gameLogReceivesMoves() {
        GameLog log = new GameLog();
        assertEquals(0L, log.moveList.get(0).index);
    }
}


