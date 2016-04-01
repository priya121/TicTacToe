package ttt.observers;

import java.io.Serializable;

public class Move implements Serializable {
    private static long serialVersionUID = 9168168204746857485L;
    public final long index;
    public final long time;

    public Move(long index, long time) {
        this.index = index;
        this.time = time;
    }
}
