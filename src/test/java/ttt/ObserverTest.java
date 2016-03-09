package ttt;

import org.junit.Test;
import ttt.inputOutput.FakeIO;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ObserverTest {

    @Test
    public void setsUpdatedMoveAsCellTwoForTheFirstHumanPlayer() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "0", "8", "1", "7", "3", "5", "2", "N")));
        game.gameLoop();
        Observer moveObserver = new MoveObserver(game);
        assertEquals(2, moveObserver.updateMove());
    }

    @Test
    public void setsUpdatedMoveAsCellSevenForTheFirstHumanPlayer() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "3", "6", "2", "7", "N")));
        game.gameLoop();
        Observer moveObserver = new MoveObserver(game);
        assertEquals(7, moveObserver.updateMove());
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private Game getFourByFourGame(FakeIO humanMoves) {
        return new GameCreator(humanMoves).createGame();
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }
}
