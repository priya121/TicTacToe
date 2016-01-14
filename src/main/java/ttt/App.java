package ttt;

import ttt.board.Board;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.IO;
import ttt.player.RealComputerPlayer;

import java.util.List;

public class App {

    public static void main(String[] args) {
        GameSetup initialSetup = new GameSetup();
        List<Symbol> empty = initialSetup.emptyBoard(3, 3);
        Board board = new Board(empty);
        IO io = new ConsoleIO(System.in, System.out);
        RealComputerPlayer realComputerPlayer = new RealComputerPlayer(board);
        Game newGame = new Game(board, io, realComputerPlayer);
        newGame.gameLoop();
    }
}
