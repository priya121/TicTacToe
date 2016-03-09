package ttt;

import java.util.List;

public abstract class Observer {
    protected Game game;
    public abstract String update();
    public abstract List<String> listObservations(String observation);
}
