package ttt.player;

import ttt.board.Board;
import ttt.Symbol;
import ttt.inputOutput.IO;

import static ttt.Symbol.CROSS;

public class HumanPlayer implements Player {
    private IO io;
    private Board board;
    private int indexChosen;
    private String userInput;

    public HumanPlayer(IO io, Board board) {
        this.io = io;
        this.board = board;
    }

    public String takesUserInput() {
        userInput = io.takeInput();
        return userInput;
    }

    public int validMove() {
        while (!digitInput(userInput) || !board.isPositionEmpty(Integer.parseInt(userInput))) {
            io.showOutput("That position is already taken or is not on the board, try again.");
            takesUserInput();
            indexChosen = Integer.parseInt(userInput);
        }
        return indexChosen;
    }

    public boolean digitInput(String userInput) {
        boolean digitInput = false;
        while (!digitInput) {
            try {
                indexChosen = Integer.parseInt(userInput);
                digitInput = true;
            } catch (Exception e) {
                io.showOutput("Please enter a number from 0 - 8:");
                userInput = takesUserInput();
            }
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
