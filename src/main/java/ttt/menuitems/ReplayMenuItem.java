package ttt.menuitems;

import ttt.Game;
import ttt.board.Board;
import ttt.board.BoardCreator;
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
        io.showOutput("2) Replay Game");
    }

    @Override
    public void perform() {
        replayMessage();
        Player one = creator.createReplayX();
        Player two = creator.createReplayO();
        Board board = new BoardCreator(io).create();
        game = new Game(board, io, one, two);
        game.gameLoop();
    }

    public String replayMessage() {
        return io.showOutput("Replaying game...");
    }
}
