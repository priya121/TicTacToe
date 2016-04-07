package ttt;

import ttt.inputOutput.DigitValidator;
import ttt.inputOutput.IO;
import ttt.menuitems.ExitMenuItem;
import ttt.menuitems.MenuItem;
import ttt.menuitems.ReplayMenuItem;
import ttt.menuitems.TwoPlayerMenuItem;
import ttt.player.PlayerCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuDisplay {
    private final SizeChoice size;
    private IO io;
    public List<MenuItem> menuItems = new ArrayList<>();
    private List<MenuItem> displayItems = new ArrayList<>();
    private int gameType;

    public MenuDisplay(IO io) {
        this.io = io;
        this.size = new SizeChoice();
        PlayerCreator creator = new PlayerCreator(io);
        MenuItem twoPlayer = new TwoPlayerMenuItem(io, creator, size);
        MenuItem replay = new ReplayMenuItem(io, creator, size);
        MenuItem exit = new ExitMenuItem(io);
        this.menuItems = Arrays.asList(twoPlayer, replay, exit);
        updateMenuItems();
    }

    public void start() {
        showGreeting();
        showMenuItems();
        MenuItem menuItemChosen = chooseGameType();
        while (gameType != 3) {
            if (menuItemChosen.shouldAppear()) {
                menuItemChosen.perform();
                updateMenuItems();
                showMenuItems();
                menuItemChosen = chooseGameType();
            }
        }
    }

    public void updateMenuItems() {
        displayItems = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.shouldAppear()) {
                displayItems.add(item);
            }
        }
    }

    public String showGreeting() {
        return io.showOutput("Welcome!");
    }

    public void showMenuItems() {
        io.showOutput("Please choose from the following options: \n");
        int number = 1;
        for (MenuItem menuItem : displayItems) {
            io.showOutput(String.valueOf(number) + ") ");
            menuItem.show();
            number++;
        }
    }

    public MenuItem chooseGameType() {
        getGameType();
        return displayItems.get(gameType - 1);
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
