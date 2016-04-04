package ttt;

import ttt.inputOutput.IO;

import java.util.Arrays;
import java.util.List;

public class MenuDisplay {
    private IO io;
    public List<MenuItem> menuItems;

    public MenuDisplay(IO io){
        this.io = io;
        this.menuItems = Arrays.asList(new TwoPlayerMenuItem(io), new ReplayMenuItem(), new ExitMenuItem());
    }

    public String showGreeting() {
        return io.showOutput("Welcome! Please choose an option from the following list: ");
    }
}
