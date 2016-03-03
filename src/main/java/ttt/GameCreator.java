package ttt;

import ttt.board.Board;
import ttt.board.BoardCreator;
import ttt.inputOutput.IO;
import ttt.player.Player;
import ttt.player.PlayerCreator;

public class GameCreator {
    private IO io;

    public GameCreator(IO io) {
        this.io = io;
    }

    public Game createGame() {
        Board board = getBoard();
        displayMessage();
        String userInput = io.takeInput();
        PlayerCreator playerCreate = new PlayerCreator(io, userInput);
        Player playerOne = playerCreate.createX();
        Player playerTwo = playerCreate.createO();
        return new Game(board, io, playerOne, playerTwo);
    }

    public void gameStart() {
        createGame().gameLoop();
        replayGameOption();
        io.showOutput("game over");
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
        BoardCreator boardChooser = new BoardCreator(io);
        return boardChooser.create();
    }

    public void replayGameOption() {
        io.showOutput("Would you like to play again? Y/N");
        String replayChosen = io.takeInput();
        createMultipleGames(replayChosen);
    }

    public void createMultipleGames(String replayChosen) {
        if (replayChosen.equals("Y")) {
            createGame().gameLoop();
        }
    }
}
