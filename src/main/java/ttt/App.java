package ttt;

import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.IO;

public class App {
    public static void main(String[] args) {
        IO io = new ConsoleIO(System.in, System.out);
        Game newGame = new Game(io);
        newGame.gameLoop();
    }
}
