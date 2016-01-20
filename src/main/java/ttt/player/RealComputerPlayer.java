package ttt.player;

import ttt.Symbol;
import ttt.board.Board;
import static ttt.Symbol.O;

public class RealComputerPlayer implements ComputerPlayer {
    Board board;
    int move;
    Symbol symbol;

    public RealComputerPlayer(Board board) {
    this.board = board;
    this.symbol = Symbol.O;
    }

    public int move() {
    RandomNumber random = new RandomNumber();
    move = random.generate(8);
        while (!board.isPositionEmpty(move)) {
            move = random.generate(8);
        }
    return move;
    }

    public Symbol getSymbol() {
        return O;
    }
}
