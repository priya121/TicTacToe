package ttt.player;

import ttt.Symbol;

import java.util.LinkedList;
import java.util.List;

import static ttt.Symbol.O;
import static ttt.Symbol.X;

public class FakeComputerPlayer implements ComputerPlayer {
    private Symbol symbol;
    private Player opponent;
    private LinkedList<Integer> input;

    public FakeComputerPlayer(List<Integer> input, Player opponent) {
        this.input = new LinkedList<>(input);
        this.opponent = opponent;
        this.symbol = calculateOwnSymbol();
    }

    public int move() {
      return input.pop();
    }

    public Symbol calculateOwnSymbol() {
        if (opponent.calculateOwnSymbol().equals(O)) {
            return X;
        } else {
            return O;
        }
    }

    public Symbol setPlayerSymbol() {
        return symbol;
    }
}

