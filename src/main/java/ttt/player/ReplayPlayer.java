package ttt.player;

import ttt.Symbol;
import ttt.board.Board;
import ttt.observers.GameLog;
import ttt.observers.Move;

import java.util.Iterator;

import static java.lang.Math.toIntExact;

public class ReplayPlayer implements Player {
    private long firstMoveTime;
    public Symbol symbol;
    public Iterator<Move> moves;
    public Move firstMove;

    public ReplayPlayer(Symbol symbol, GameLog gameLog) {
        this.symbol = symbol;
        this.moves = gameLog.moveList.iterator();
        this.firstMove = gameLog.moveList.get(0);
        this.firstMoveTime = gameLog.moveList.get(0).time;
        if (symbol == Symbol.O) {
            this.firstMove = moves.next();
        }
    }

    @Override
    public int move(Board board) {
        Move move = moves.next();
        try {
            Thread.sleep(move.time - firstMoveTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        firstMoveTime = move.time;
        calculateNextMove();
        return (toIntExact(move.index));
    }

    private void calculateNextMove() {
        if (moves.hasNext()) {
            moves.next();
        }
    }

    @Override
    public Symbol playerSymbol() {
        return symbol;
    }
}
