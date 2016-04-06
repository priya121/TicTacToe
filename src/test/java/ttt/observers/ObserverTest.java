package ttt.observers;

import org.junit.Test;
import ttt.Game;
import ttt.TwoVsTwoGameCreator;
import ttt.inputOutput.FakeIO;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ObserverTest {

    @Test
    public void addsGameMoveToAList() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("1", "4", "4", "0", "5", "3", "6", "2", "7", "N")));
        MoveObserver moveObserver = moveObserverGame(game);
        moveObserver.generateMoves();
        assertEquals(4, moveObserver.movesInfo.get(0).index);
    }

    @Test
    public void addsAllMovesOfGameToList() throws IOException {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("1", "4", "4", "0", "5", "3", "6", "2", "7", "N")));
        MoveObserver moveObserver = moveObserverGame(game);
        moveObserver.generateMoves();
        assertEquals(7, moveObserver.movesInfo.size());
    }

    private MoveObserver moveObserverGame(Game game) {
        MoveObserver moveObserver = new MoveObserver(game);
        game.gameLoop();
        return moveObserver;
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private Game getFourByFourGame(FakeIO humanMoves) {
        return new TwoVsTwoGameCreator(humanMoves).createGame();
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }
}
