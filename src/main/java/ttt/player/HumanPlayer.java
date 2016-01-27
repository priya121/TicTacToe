package ttt.player;

import ttt.Symbol;
import ttt.board.Board;
import ttt.inputOutput.IO;

import static ttt.Symbol.X;

public class HumanPlayer implements Player {
    private IO io;
    private Board board;
    private int indexChosen;
    private String userInput;
    private String sizeOfBoardChosen;
    private int sizeChosen;

    public HumanPlayer(IO io, Board board) {
        this.io = io;
        this.board = board;
    }

    public String takesUserInput() {
        userInput = io.takeInput();
        return userInput;
    }

    public String getSizeOfBoard() {
        sizeOfBoardChosen = io.takeInput();
        return sizeOfBoardChosen;
    }

    public int size() {
        sizeOfBoardChosen = getSizeOfBoard();
        sizeChosenValidDigit(sizeOfBoardChosen);
        return sizeChosen;
    }

    private int validMove() {
        while (!digitInput(userInput) || !board.isPositionEmpty(indexChosen)) {
            io.showOutput("That position is already taken or is not on the board, try again.");
            takesUserInput();
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
                io.showOutput("Please enter a valid number");
                userInput = takesUserInput();
            }
        }
        return digitInput;
    }

    private boolean sizeChosenValidDigit(String sizeOfBoardChosen) {
        boolean digitInput = false;
        while (!digitInput) {
            try {
                sizeChosen = Integer.parseInt(sizeOfBoardChosen);
                digitInput = true;
            } catch (Exception e) {
                io.showOutput("Please enter a valid number");
                sizeOfBoardChosen = getSizeOfBoard();
            }
        }
        return digitInput;
    }

    public int move() {
        takesUserInput();
        return validMove();
    }

    public Symbol getSymbol() {
        return X;
    }
}
