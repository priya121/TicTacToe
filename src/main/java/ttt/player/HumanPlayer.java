package ttt.player;

import ttt.Symbol;
import ttt.board.Board;
import ttt.inputOutput.IO;

import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.CROSS;

public class HumanPlayer implements Player {
    private IO io;
    private Board board;
    private int indexChosen;
    private String userInput;
    List<String> validIndices = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8");

    public HumanPlayer(IO io, Board board) {
        this.io = io;
        this.board = board;
    }

    public String takesUserInput() {
        userInput = io.takeInput();
        return userInput;
    }

    public int validMove() {
        while (!digitInput() || !board.isPositionEmpty(Integer.parseInt(userInput))) {
            io.showOutput("That position is already taken or is not on the board, try again.");
            takesUserInput();
        }
        indexChosen = Integer.parseInt(userInput);
        return indexChosen;
    }

    private boolean digitInput() {
        boolean digitInput = true;
        if (!validIndices.contains(userInput)) {
            io.showOutput("Please enter a number from 0 - 8:");
            digitInput = false;

        }
        return digitInput;
    }

    public int move() {
        takesUserInput();
        return validMove();
    }

    public Symbol getSymbol() {
        return CROSS;
    }
}
