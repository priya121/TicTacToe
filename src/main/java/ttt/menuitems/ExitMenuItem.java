package ttt.menuitems;

import ttt.inputOutput.IO;

public class ExitMenuItem implements MenuItem {
    private final IO io;

    public ExitMenuItem(IO io) {
        this.io = io;
    }

    @Override
    public void show() {
        exitMessage();
    }

    @Override
    public void perform() {
        io.showOutput("Bye!");
    }

    public String exitMessage() {
        return io.showOutput("Exiting game...\n" +
                             "Bye!");
    }
}
