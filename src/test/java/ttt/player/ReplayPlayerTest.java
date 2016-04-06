package ttt.player;

import org.junit.Test;
import ttt.Game;
import ttt.TwoVsTwoGameCreator;
import ttt.Symbol;
import ttt.inputOutput.FakeIO;
import ttt.observers.GameLog;
import ttt.observers.MoveObserver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReplayPlayerTest {
    Game game = getFourByFourGame(humanMoves(Arrays.asList("1", "4", "0", "8", "1", "7", "3", "5", "2")));

    @Test
    public void generatesRightMoveForPlayerX() throws IOException {
        MoveObserver observer = moveObserverGame(game);
        observer.generateMoves();
        GameLog gameLog = new GameLog();
        ReplayPlayer playerX = new ReplayPlayer(Symbol.X, gameLog);
        assertEquals(0, playerX.firstMove.index);
    }

    @Test
    public void generatesRightMoveForPlayerO() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("1", "4", "0", "8", "1", "7", "3", "5", "2")));
        MoveObserver observer = moveObserverGame(game);
        observer.generateMoves();
        GameLog gameLog = new GameLog();
        ReplayPlayer playerO = new ReplayPlayer(Symbol.O, gameLog);
        assertEquals(8, playerO.move(game.board));
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
        PlayerCreator creator = new PlayerCreator(humanMoves);
        return new TwoVsTwoGameCreator(humanMoves, creator).createGame();
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }
}
