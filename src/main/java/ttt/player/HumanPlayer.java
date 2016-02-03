package ttt.player;

import ttt.Symbol;
import ttt.board.Board;
import ttt.inputOutput.IO;

import static ttt.Symbol.X;

public class HumanPlayer implements Player {
    private IO io;
    private SymbolChooser symbolChosen;
    private Board board;
    private int indexChosen;
    private String userInput;
    private Symbol chosenSymbol;

    public HumanPlayer(IO io, Board board) {
        this.io = io;
        this.board = board;
        this.symbolChosen = new SymbolChooser(io);
        this.chosenSymbol = X;
    }

    public String takesUserInput() {
        userInput = io.takeInput();
        return userInput;
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

    public int move() {
        takesUserInput();
        return validMove();
    }

    public Symbol setPlayerSymbol() {
        chosenSymbol = symbolChosen.getSymbol();
        return chosenSymbol;
    }

    public Symbol calculateOwnSymbol() {
        return chosenSymbol;
    }
}
