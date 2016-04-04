package ttt;

import ttt.inputOutput.IO;
import ttt.menuitems.ExitMenuItem;
import ttt.menuitems.MenuItem;
import ttt.menuitems.ReplayMenuItem;
import ttt.menuitems.TwoPlayerMenuItem;

import java.util.Arrays;
import java.util.List;

public class MenuDisplay {
    private IO io;
    public List<MenuItem> menuItems;

    public MenuDisplay(IO io) {
        this.io = io;
        menuItems = Arrays.asList(new TwoPlayerMenuItem(io), new ReplayMenuItem(io), new ExitMenuItem(io));
    }

    public String showGreeting() {
        return io.showOutput("Welcome! Please choose an option from the following list: ");
    }

    public void showChoices() {
        io.showOutput("Please choose from the following options: \n");
        for (MenuItem menuItem : menuItems) {
             menuItem.show();
        }
    }

    public MenuItem chooseGameType() {
        Integer gameType = Integer.parseInt(io.takeInput());
        return menuItems.get(gameType - 1);
    }

    public void start() {
        showGreeting();
        showChoices();
        MenuItem menuItemChosen = chooseGameType();
        menuItemChosen.show();
        menuItemChosen.perform();
    }
}
