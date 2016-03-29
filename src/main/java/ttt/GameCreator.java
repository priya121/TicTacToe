package ttt;

import ttt.board.Board;
import ttt.board.BoardCreator;
import ttt.inputOutput.IO;
import ttt.inputOutput.ReplayIO;
import ttt.player.Player;
import ttt.player.PlayerCreator;

import java.io.File;

public class GameCreator {
    private IO io;
    private ReplayIO replayIO;
    private Board savedBoard;
    private long start;

    public GameCreator(IO io, ReplayIO replayIO) {
        this.io = io;
        this.replayIO = replayIO;
    }

    public Game createGame() {
        start = System.currentTimeMillis();
        Board board = getBoard();
        setReplayBoard(board);
        displayMessage();
        String userInput = io.takeInput();
        PlayerCreator playerCreate = new PlayerCreator(io, userInput);
        Player playerOne = playerCreate.createX();
        Player playerTwo = playerCreate.createO();
        File file = new File("/Users/priyapatil/TTT/gameLog.txt");
        return new Game(board, io, playerOne, playerTwo, file);
    }

    public void setReplayBoard(Board replayBoard) {
        savedBoard = replayBoard;
    }

    public Board getSavedBoard() {
        return savedBoard;
    }

    public void createReplayGame(ReplayIO io) {
        io.addGameStartTime(start);
        io.showOutput("Replaying previous game...");
        File file = new File("/Users/priyapatil/TTT/gameLog.txt");
        PlayerCreator playerCreate = new PlayerCreator(io);
        Player playerOne = playerCreate.createX();
        Player playerTwo = playerCreate.createO();
        new Game(getSavedBoard(), io, playerOne, playerTwo, file).gameLoop();
    }

    public void gameStart() {
        createGame().gameLoop();
        replayGameOption();
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
        io.showOutput("Would you like to play again? (Y/N)\nEnter R to re-watch game just played: ");
        String replayChosen = io.takeInput();
        createMultipleGames(replayChosen);
    }

    public void createMultipleGames(String replayChosen) {
        if (replayChosen.equals("Y")) {
            gameStart();
        } else if (replayChosen.equals("R")) {
            createReplayGame(replayIO);
            replayGameOption();
        }
        io.showOutput("game over");
    }
}