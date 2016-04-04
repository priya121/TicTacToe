package ttt.menuitems;

import ttt.inputOutput.IO;

public class ExitMenuItem implements MenuItem {
    private final IO io;

    public ExitMenuItem(IO io) {
        this.io = io;
    }

    @Override
    public void show() {
        io.showOutput("3) Exit Game");
    }

    @Override
    public void perform() {
        io.showOutput("Bye!");
    }
}
