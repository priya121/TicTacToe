package ttt;

import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.IO;
import ttt.inputOutput.ReplayIO;

import java.io.File;

public class App {
    public static void main(String[] args) {
        IO io = new ConsoleIO(System.in, System.out);
        File file = new File("/Users/priyapatil/TTT/gameLog.txt");
        ReplayIO replayIO = new ReplayIO(file, System.out);
        GameCreator newGame = new GameCreator(io, replayIO);
        newGame.gameStart();
    }
}
