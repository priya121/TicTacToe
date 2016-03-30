package ttt.player;

import ttt.Symbol;
import ttt.board.Board;
import ttt.observers.GameLog;
import ttt.observers.Move;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.toIntExact;

public class ReplayPlayer implements Player {
    public Symbol symbol;
    public List<Move> playerMoves = new ArrayList<>();
    public List<Move> moveList;

    public ReplayPlayer(Symbol symbol, GameLog gameLog) {
        this.symbol = symbol;
        this.moveList = gameLog.moveList;
        this.playerMoves = playerMoves();
    }

    @Override
    public int move(Board board) {
        Iterator moveSequence = playerMoves.iterator();
        Move move = (Move) moveSequence.next();
        moveSequence.remove();
        try {
            Thread.sleep(move.duration(0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return toIntExact(move.move);
    }

    @Override
    public Symbol playerSymbol() {
        return symbol;
    }

    public List<Move> playerMoves() {
        List<Move> listMoves = new ArrayList<>();
        for (int i = 0; i < moveList.size(); i++) {
            addPlayerMoves(listMoves, i);
        }
        return listMoves;
    }

    private void addPlayerMoves(List<Move> listMoves, int i) {
        if (symbol == Symbol.X && (i % 2) == 0) {
            listMoves.add(moveList.get(i));
        } else if (symbol == Symbol.O && (i % 2) != 0) {
            listMoves.add(moveList.get(i));
        }
    }

    public Board getBoardSize() {
        return 

    }
}
