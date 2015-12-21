package ttt;

import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        String empty = "-";
        List<String> emptyBoard = Arrays.asList(empty, empty, empty,
                                                empty, empty, empty,
                                                empty, empty, empty);
        Board board = new Board(emptyBoard);
        IO io = new ConsoleIO(System.in, System.out);
        Game newGame = new Game(board, io);
        newGame.gameLoop();
    }
}
