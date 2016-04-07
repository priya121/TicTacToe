package ttt.menuitems;

import ttt.Game;
import ttt.SizeChoice;
import ttt.board.Board;
import ttt.board.BoardCreator;
import ttt.inputOutput.IO;
import ttt.player.Player;
import ttt.player.PlayerCreator;

public class ReplayMenuItem implements MenuItem {
    private final IO io;
    private final PlayerCreator creator;
    private final SizeChoice size;

    public ReplayMenuItem(IO io, PlayerCreator creator, SizeChoice size) {
        this.io = io;
        this.creator = creator;
        this.size = size;
    }

    @Override
    public void show() {
        io.showOutput("Replay Game");
    }

    @Override
    public void perform() {
        replayMessage();
        Player one = creator.createReplayX();
        Player two = creator.createReplayO();
        Board board = new Board(size.getBoardSize());
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

    public Board getBoard() {
        BoardCreator boardChooser = new BoardCreator(io);
        return boardChooser.create();
    }
}
