package ttt.player;

import ttt.Symbol;
import ttt.board.Board;

public class RealComputerPlayer implements ComputerPlayer {
    Board board;
    int number;
    Symbol symbol;

    public RealComputerPlayer(Board board) {
    this.board = board;
    this.symbol = Symbol.NOUGHT;
    }

    public int move() {
    RandomNumber random = new RandomNumber();
    number = random.generate(8);
        while (!board.isPositionEmpty(number)) {
            number = random.generate(8);
        }
    return number;
    }
}
