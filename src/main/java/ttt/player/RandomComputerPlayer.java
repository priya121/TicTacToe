package ttt.player;

import ttt.Symbol;
import ttt.board.Board;

import static ttt.Symbol.O;
import static ttt.Symbol.X;

public class RandomComputerPlayer implements ComputerPlayer {
    Player opponent;
    Board board;
    int move;
    Symbol symbol;

    public RandomComputerPlayer(Board board, Player opponent) {
        this.board = board;
        this.opponent = opponent;
        this.symbol = opponent.calculateOwnSymbol();
    }

    public RandomComputerPlayer(Board board) {
        this.board = board;
        this.symbol = X;
    }

    public int move() {
        RandomNumber random = new RandomNumber();
        move = random.generate(board.contentsOfBoard().size());
        while (!board.isPositionEmpty(move)) {
            move = random.generate(board.contentsOfBoard().size());
        }
        return move;
    }

    public Symbol calculateOwnSymbol() {
        if (opponent.calculateOwnSymbol().equals(X)) {
            symbol = O;
        } else {
            symbol = X;
        }
        return symbol;
    }

    public Symbol setPlayerSymbol() {
        return symbol;
    }
}
