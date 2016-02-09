package ttt;

import ttt.board.Board;
import ttt.board.BoardChooser;
import ttt.inputOutput.GameChooser;
import ttt.inputOutput.IO;
import ttt.player.Player;
import ttt.player.PlayerCreator;


public class GameCreator {
    private GameChooser gameChooser;
    private PlayerCreator playerCreate;
    IO io;

    public GameCreator(IO io) {
        this.io = io;
        this.playerCreate = new PlayerCreator(io);
        this.gameChooser = new GameChooser(io);
    }

    public Game createGame() {
        Board board = getBoard();
        displayMessage();
        String userInput = io.takeInput();
        GameType choice = gameChooser.gameType(userInput);

        Player playerOne = playerCreate.playerOne(choice);
        Player playerTwo = playerCreate.playerTwo(choice);
        Game game = new Game(board, io, playerOne, playerTwo);
        return game;
    }

    public void displayMessage() {
        io.showOutput("Hi, choose a game type (Enter 1 - 4): \n" +
                "1) Human vs Human \n" +
                "2) Human vs Computer \n" +
                "3) Computer vs Human \n" +
                "4) Computer vs Computer \n" +
                "Entering any other character will return a default Human v Human game:");
    }

    private Board getBoard() {
        BoardChooser boardChooser = new BoardChooser(io);
        return boardChooser.create();
    }
}
