package ttt;

import ttt.board.Board;
import ttt.board.DisplayBoard;
import ttt.inputOutput.IO;
import ttt.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static ttt.Symbol.E;

public class Game {
    GameCreator createGame;
    public Board board;
    IO io;
    Player currentPlayer;
    Player playerOne;
    Player playerTwo;

    public Game(IO io) {
        this.io = io;
        this.createGame = new GameCreator(io);
    }

    public Game(Board board, IO io, Player playerOne, Player playerTwo) {
        this.board = board;
        this.io = io;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;
    }

    public Game setupGame() {
        return createGame.createGame();
    }

    public void gameLoop() {
        while (board.gameNotOver()) {
            io.showOutput("Enter a position from 0 - " + (board.contentsOfBoard().size() - 1));
            showCurrentBoard();
            computeCurrentPlayer(board);
            board = board.markPlayer(currentPlayer.move(board), currentPlayer.playerSymbol());
        }
        endOfGameDisplay();
    }

    public List<Symbol> getBoard() {
        return board.contentsOfBoard();
    }

    public void computeCurrentPlayer(Board board) {
        List<Integer> emptyCells = new ArrayList<>();
        IntStream.range(0, board.contentsOfBoard().size())
                .filter(cell -> board.get(cell).equals(E))
                .forEach(i -> emptyCells.add(i));
        switchPlayers(emptyCells.size());
    }

    private void switchPlayers(int emptyCells) {
        boardOddNumberOfCells(emptyCells);
        boardEvenNumberOfCells(emptyCells);
    }

    private void boardEvenNumberOfCells(int emptyCells) {
        if (board.contentsOfBoard().size() % 2 == 0) {
            if (emptyCells % 2 == 0) currentPlayer = playerOne;
            else currentPlayer = playerTwo;
        }
    }

    private void boardOddNumberOfCells(int emptyCells) {
        if (board.contentsOfBoard().size() % 2 != 0) {
            if (emptyCells % 2 == 0) currentPlayer = playerTwo;
            else currentPlayer = playerOne;
        }
    }

    public void showCurrentBoard() {
        DisplayBoard currentDisplay = new DisplayBoard(board.contentsOfBoard());
        io.showOutput(currentDisplay.showBoard());
    }

    private void endOfGameDisplay() {
        showCurrentBoard();
        if (board.isWin()) {
            io.showOutput("Player " + currentPlayer.playerSymbol() + " has won!");
        }
        io.showOutput("game over");
        replayGameOption();
    }

    private void replayGameOption() {
        io.showOutput("Would you like to play again? Y/N");
        String replayChosen = io.takeInput();
        replayOptionChosen(replayChosen);
    }

    private void replayOptionChosen(String replayChosen) {
        if (replayChosen.equals("Y")) {
            Game newGame  = this.setupGame();
            newGame.gameLoop();
        }
    }

    public Symbol getPlayerOne() {
        return currentPlayer.playerSymbol();
    }
}
