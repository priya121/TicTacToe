package ttt.observers;

import java.io.Serializable;

public class Move implements Serializable {

    public final long move;
    public final long time;

    public Move(long move, long time) {
        this.move = move;
        this.time = time;
    }

    public long duration(long start) {
        return 1000;
    }
}
