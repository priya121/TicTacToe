package ttt.inputOutput;

import java.util.List;
import java.util.LinkedList;

public class FakeIO implements IO {
    private LinkedList<String> input;

    public FakeIO(List<String> input) {
        this.input = new LinkedList<>(input);
    }

    @Override
         public String takeInput() {
            return input.pop();
         }

    @Override
        public String showOutput(String message) {
            return message;
        }
}
