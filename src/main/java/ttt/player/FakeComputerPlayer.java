package ttt.player;

import java.util.LinkedList;
import java.util.List;

public class FakeComputerPlayer implements ComputerPlayer {
    private LinkedList<Integer> input;

    public FakeComputerPlayer(List<Integer> input) {
        this.input = new LinkedList<>(input);
    }

    public int move() {
      return input.pop();
    }
}

