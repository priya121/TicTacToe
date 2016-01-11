package ttt;

import ttt.board.Board;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.IO;
import ttt.player.RealComputerPlayer;
import static ttt.Symbol.EMPTY;
import static ttt.Symbol.NOUGHT;

import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<Symbol> emptyBoard = Arrays.asList(EMPTY, EMPTY, EMPTY,
                                               EMPTY, EMPTY, EMPTY,
                                               EMPTY, EMPTY, EMPTY);

        Board board = new Board(emptyBoard);
        IO io = new ConsoleIO(System.in, System.out);
        RealComputerPlayer realComputerPlayer = new RealComputerPlayer(board);
        Game newGame = new Game(board, io, realComputerPlayer);
        newGame.gameLoop();
    }
}
