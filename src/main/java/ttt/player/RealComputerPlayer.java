package ttt.player;

import ttt.board.Board;
import ttt.Symbol;
import ttt.inputOutput.IO;

public class RealComputerPlayer implements ComputerPlayer {
    IO io;
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
        while (!board.isValid(number)) {
            number = random.generate(8);
        }
    return number;
    }
}
