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

    public ReplayMenuItem(IO io, PlayerCreator creator) {
        this.io = io;
        this.creator = creator;
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
        Game game = new Game(board, io, one, two);
        game.gameLoop();
    }

    @Override
    public boolean shouldAppear() {
        return creator.notFirstGame;
    }

    public String replayMessage() {
        return io.showOutput("Replaying game...");
    }
}
