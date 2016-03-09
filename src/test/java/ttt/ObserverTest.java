package ttt;

import org.junit.Test;
import ttt.inputOutput.FakeIO;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ObserverTest {

    @Test
    public void setsLastUpdatedMoveAsCellTwoForTheFirstHumanPlayer() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "0", "8", "1", "7", "3", "5", "2", "N")));
        MoveObserver moveObserver = new MoveObserver(game);
        game.gameLoop();
        assertEquals(Arrays.asList("0", "8", "1", "7", "3", "5", "2"), moveObserver.movesList);
    }

    @Test
    public void setsLastUpdatedMoveAsCellSevenForTheFirstHumanPlayer() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "3", "6", "2", "7", "N")));
        MoveObserver moveObserver = new MoveObserver(game);
        game.gameLoop();
        assertEquals(Arrays.asList("4", "0", "5", "3", "6", "2", "7"), moveObserver.movesList);
    }

    @Test
    public void setsLastUpdateCurrentPlayerAsXForPlayerObserver() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "3", "6", "2", "7", "N")));
        PlayerObserver playerObserver = new PlayerObserver(game);
        game.gameLoop();
        assertEquals(Arrays.asList("X", "O", "X", "O", "X", "O", "X"), playerObserver.ordered);
    }

    @Test
    public void setsLastUpdateCurrentPlayerAsOForPlayerObserver() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "2", "6", "3", "15", "1", "N")));
        PlayerObserver playerObserver = new PlayerObserver(game);
        game.gameLoop();
        assertEquals(Arrays.asList("X", "O", "X", "O", "X", "O", "X", "O"), playerObserver.ordered);
    }

    @Test
    public void testsTimeGivenForEachMove() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "4", "0", "5", "2", "6", "3", "15", "1", "N")));
        DateTimeObserver timeObserver = new DateTimeObserver(game);
        game.gameLoop();
        assertEquals("Wed Mar ", timeObserver.update().substring(0, 8));
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
