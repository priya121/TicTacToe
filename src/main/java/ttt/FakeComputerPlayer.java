package ttt;

import java.util.List;
import java.util.LinkedList;

public class FakeComputerPlayer implements ComputerPlayer {
    private LinkedList<Integer> input;

    public FakeComputerPlayer(List<Integer> input) {
        this.input = new LinkedList<>(input);
    }

    public int move() {
      return input.pop();
    }
}

