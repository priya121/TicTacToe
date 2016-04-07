package ttt;

import ttt.board.Board;
import ttt.board.BoardCreator;
import ttt.inputOutput.IO;
import ttt.player.Player;
import ttt.player.PlayerCreator;

public class TwoVsTwoGameCreator {
    private PlayerCreator creator;
    private SizeChoice boardSize;
    private IO io;

    public TwoVsTwoGameCreator(IO io, PlayerCreator playerCreator, SizeChoice boardSize) {
        this.io = io;
        this.creator = playerCreator;
        this.boardSize = boardSize;
    }

    public Game createGame() {
        displayMessage();
        creator.takeGameType(io);
        Player playerOne = creator.createX();
        Player playerTwo = creator.createO();
        Board board = getBoard();
        boardSize.setBoardSize(board.contentsOfBoard().size());
        return new Game(board, io, playerOne, playerTwo);
    }

    public void gameStart() {
        createGame().gameLoop();
    }

    public void displayMessage() {
        io.showOutput("Hi, choose a game type (Enter 1 - 4): \n" +
                "1) Human vs Human \n" +
                "2) Human vs Computer \n" +
                "3) Computer vs Human \n" +
                "4) Computer vs Computer \n" +
                "Entering any other character will return a default Human v Human game:");
    }

    public Board getBoard() {
        BoardCreator boardChooser = new BoardCreator(io);
        return boardChooser.create();
    }
}
