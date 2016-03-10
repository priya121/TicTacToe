package ttt.observers;

import ttt.Game;

import java.io.File;
import java.util.List;

public abstract class Observer {
    protected Game game;
    public abstract String update();
    public abstract List<String> listObservations(String observation);
    public abstract void writeToFile(String display, File file);
}
