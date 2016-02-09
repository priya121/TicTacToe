package ttt.player;

import ttt.Symbol;
import ttt.board.Board;

import java.util.LinkedList;
import java.util.List;

public class FakeComputerPlayer implements ComputerPlayer {
    private Symbol symbol;
    private LinkedList<Integer> input;

    public FakeComputerPlayer(List<Integer> input, Symbol symbol) {
        this.input = new LinkedList<>(input);
        this.symbol = symbol;
    }

    public int move(Board board) {
      return input.pop();
    }


    public Symbol playerSymbol() {
        return symbol;
    }
}

