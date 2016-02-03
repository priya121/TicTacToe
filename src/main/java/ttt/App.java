package ttt;

import ttt.board.Board;
import ttt.board.BoardChooser;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.IO;
import ttt.player.AIComputerPlayer;
import ttt.player.HumanPlayer;
import ttt.player.RandomComputerPlayer;

public class App {
    public static void main(String[] args) {
        IO io = new ConsoleIO(System.in, System.out);
        Board board = new Board(3);
        BoardChooser boardSize = new BoardChooser(io);
        board = boardSize.create();
        HumanPlayer human = new HumanPlayer(io, board);
        //HumanPlayer humanOne = new HumanPlayer(io, board);
        RandomComputerPlayer randomComputerPlayer = new RandomComputerPlayer(board, human);
        AIComputerPlayer aiComputerPlayer = new AIComputerPlayer(board, human);
        Game newGame = new Game(board, io, human, aiComputerPlayer);
        newGame.gameLoop();
    }
}
