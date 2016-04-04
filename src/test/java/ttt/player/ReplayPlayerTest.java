package ttt.player;

import org.junit.Test;
import ttt.Game;
import ttt.GameCreator;
import ttt.Symbol;
import ttt.inputOutput.FakeIO;
import ttt.observers.GameLog;
import ttt.observers.MoveObserver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReplayPlayerTest {
    Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "0", "8", "1", "7", "3", "5", "2", "N")));

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
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "1", "0", "8", "1", "7", "3", "5", "2", "N")));
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
        return new GameCreator(humanMoves).createGame();
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }
}
