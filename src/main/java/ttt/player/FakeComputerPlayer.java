package ttt.player;

import ttt.Symbol;

import java.util.LinkedList;
import java.util.List;

import static ttt.Symbol.NOUGHT;

public class FakeComputerPlayer implements ComputerPlayer {
    private LinkedList<Integer> input;

    public FakeComputerPlayer(List<Integer> input) {
        this.input = new LinkedList<>(input);
    }

    public int move() {
      return input.pop();
    }

    public Symbol getSymbol() {
        return NOUGHT;
    }
}

