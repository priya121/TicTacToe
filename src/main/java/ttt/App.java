package ttt;

import ttt.board.Board;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.IO;
import ttt.player.AIComputerPlayer;
import ttt.player.HumanPlayer;
import ttt.player.RealComputerPlayer;

public class App {
    public static void main(String[] args) {
        Board board = new Board();
        IO io = new ConsoleIO(System.in, System.out);
        HumanPlayer human = new HumanPlayer(io, board);
        RealComputerPlayer realComputerPlayer = new RealComputerPlayer(board);
        AIComputerPlayer AIPlayer = new AIComputerPlayer(board);
        Game newGame = new Game(board, io, realComputerPlayer, human);
        newGame.gameLoop();
    }
}
