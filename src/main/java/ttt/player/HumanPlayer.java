package ttt.player;

import ttt.board.Board;
import ttt.Symbol;
import ttt.inputOutput.IO;

public class HumanPlayer implements Player {
    Symbol cross = Symbol.CROSS;
    IO io;
    Board board;
    int indexChosen = 0;

    public HumanPlayer(IO io, Board board) {
        this.io = io;
        this.board = board;
    }

    public String takesUserInput() {
        return io.takeInput();
    }

    public int isValidMove(int indexChosen) {
        while (!board.isValid(indexChosen)) {
            io.showOutput("That position is already taken or is not on the board, try again.");
            indexChosen = Integer.parseInt(takesUserInput());
        }
        return indexChosen;
    }

    public int checkDigitInput() {
        boolean digitInput = false;
        while (!digitInput) {
            try {
                indexChosen = Integer.parseInt(takesUserInput());
                digitInput = true;
            } catch (Exception e) {
                io.showOutput("Please enter a number from 0-8:");
            }
        }
        return indexChosen;
    }

    public int move() {
        int indexChosen = checkDigitInput();
        indexChosen = isValidMove(indexChosen);
        return indexChosen;
    }

    public Symbol getSymbol() {
        return cross;
    }
}
