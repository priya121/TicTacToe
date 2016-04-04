package ttt.menuitems;

import ttt.Game;
import ttt.GameCreator;
import ttt.board.Board;
import ttt.inputOutput.IO;
import ttt.player.Player;
import ttt.player.PlayerCreator;

public class ReplayMenuItem implements MenuItem {
    private final IO io;
    private final PlayerCreator creator;
    private Game game;

    public ReplayMenuItem(IO io) {
        this.io = io;
        this.creator = new PlayerCreator(io);
    }

    @Override
    public void show() {
        replayMessage();
        Player one = creator.createReplayX();
        Player two = creator.createReplayO();
        Board board = new GameCreator(io).getBoard();
        game = new Game(board, io, one, two);
    }

    @Override
    public void perform() {
        game.gameLoop();
    }

    public String replayMessage() {
        return io.showOutput("Replaying game...");
    }
}
