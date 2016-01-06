package ttt;

import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        Symbol empty = Symbol.EMPTY;
        List<Symbol> emptyBoard = Arrays.asList(empty, empty, empty,
                                               empty, empty, empty,
                                               empty, empty, empty);

        Board board = new Board(emptyBoard);
        IO io = new ConsoleIO(System.in, System.out);
        RealComputerPlayer realComputerPlayer = new RealComputerPlayer(board);
        Game newGame = new Game(board, io, realComputerPlayer);
        newGame.gameLoop();
    }
}
