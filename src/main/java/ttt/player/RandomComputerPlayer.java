package ttt.player;

import ttt.Symbol;
import ttt.board.Board;

import static ttt.Symbol.O;

public class RandomComputerPlayer implements ComputerPlayer {
    Board board;
    Symbol symbol;

    public RandomComputerPlayer(Board board) {
        this.board = board;
        this.symbol = O;
    }

    public int move(Board board) {
        RandomNumber random = new RandomNumber();
        int move = random.generate(board.contentsOfBoard().size());
        while (!board.isPositionEmpty(move)) {
            move = random.generate(board.contentsOfBoard().size());
        }
        return move;
    }

    public Symbol playerSymbol() {
        return symbol;
    }
}
