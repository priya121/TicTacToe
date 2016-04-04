package ttt.menuitems;

import ttt.Game;
import ttt.GameCreator;
import ttt.board.Board;
import ttt.inputOutput.IO;
import ttt.player.Player;
import ttt.player.PlayerCreator;

public class TwoPlayerMenuItem implements MenuItem {
    private final IO io;
    private final PlayerCreator creator;
    private Game game;

    public TwoPlayerMenuItem(IO io) {
        this.io = io;
        this.creator = new PlayerCreator(io);
    }

    @Override
    public void show() {
        askTypeOfTwoPlayerGame();
        creator.createPlayers(io);
        Player one = creator.createX();
        Player two = creator.createO();
        Board board = new GameCreator(io).getBoard();
        game = new Game(board, io, one, two);
    }

    @Override
    public void perform() {
        game.gameLoop();
    }

    public Player playerX() {
        return creator.createX();
    }

    public Player playerO() {
        return creator.createO();
    }

    public String askTypeOfTwoPlayerGame() {
        return io.showOutput("Hi, choose a two player game type (Enter 1 - 4): \n" +
                "1) Human vs Human \n" +
                "2) Human vs Computer \n" +
                "3) Computer vs Human \n" +
                "4) Computer vs Computer \n" +
                "Entering any other character will return a default Human v Human game:");
    }
}
