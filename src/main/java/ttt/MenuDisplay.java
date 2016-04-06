package ttt;

import ttt.inputOutput.DigitValidator;
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
    private int gameType;

    public MenuDisplay(IO io) {
        this.io = io;
        MenuItem twoPlayer = new TwoPlayerMenuItem(io);
        MenuItem replay = new ReplayMenuItem(io);
        MenuItem exit = new ExitMenuItem(io);
        menuItems = Arrays.asList(twoPlayer, replay, exit);
    }

    public void start() {
        showGreeting();
        showMenuItems();
        MenuItem menuItemChosen = chooseGameType();
        while (gameType != 3) {
            menuItemChosen.show();
            menuItemChosen.perform();
            showMenuItems();
            menuItemChosen = chooseGameType();
        }
    }

    public String showGreeting() {
        return io.showOutput("Welcome!");
    }

    public void showMenuItems() {
        io.showOutput("Please choose from the following options: \n");
        for (MenuItem menuItem : menuItems) {
            menuItem.show();
        }
    }

    public MenuItem chooseGameType() {
        getGameType();
        return menuItems.get(gameType - 1);
    }

    private void getGameType() {
        DigitValidator inputValidator = new DigitValidator();
        String userInput = io.takeInput();
        while (!inputValidator.check(userInput)) {
            io.showOutput("Please enter a valid number");
            userInput = io.takeInput();
        }
        gameType = Integer.parseInt(userInput);
    }
}
