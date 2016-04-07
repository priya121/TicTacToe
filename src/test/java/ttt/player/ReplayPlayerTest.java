package ttt.player;

import org.junit.Test;
import ttt.Game;
import ttt.SizeChoice;
import ttt.Symbol;
import ttt.TwoVsTwoGameCreator;
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
        ReplayPlayer playerX = getReplayPlayer(Symbol.X);
        assertEquals(0, playerX.firstMove.index);
    }

    @Test
    public void generatesRightMoveForPlayerO() {
        MoveObserver observer = moveObserverGame(game);
        observer.generateMoves();
        ReplayPlayer playerO = getReplayPlayer(Symbol.O);
        assertEquals(8, playerO.move(game.board));
    }

    private ReplayPlayer getReplayPlayer(Symbol symbol) {
        GameLog gameLog = new GameLog();
        return new ReplayPlayer(symbol, gameLog);
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
        SizeChoice size = new SizeChoice();
        return new TwoVsTwoGameCreator(humanMoves, creator, size).createGame();
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }
}
