package ttt.player;

import ttt.Symbol;
import ttt.board.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static ttt.Symbol.E;
import static ttt.Symbol.O;
import static ttt.Symbol.X;

public class AIComputerPlayer implements ComputerPlayer {
    int bestMove;
    Board board;
    int highestScore;
    int lowestScore;
    Symbol symbol;
    Symbol symbolToWin;

    public AIComputerPlayer(Board board) {
        this.board = board;
        this.symbolToWin = this.symbol;
        this.highestScore = 0;
        this.bestMove = 0;
    }

    public int minimax(Board board, Symbol symbol) {
        int index = -1;
        int score = 0;

        for (int emptyCell : board.validMoves()) {
            int newIndex = emptyCell;
            int temporaryScore = 0;

            Board copyOfBoard = new Board();
            copyOfBoard.clone(board);
            copyOfBoard.markPlayer(emptyCell, computeCurrentPlayer(copyOfBoard));

            if (copyOfBoard.isFull() || copyOfBoard.isWin()) {
                temporaryScore = evaluate(copyOfBoard);
            } else {
                int newScore = minimax(copyOfBoard, computeCurrentPlayer(copyOfBoard));
                temporaryScore = newScore;
            }

                if (computeCurrentPlayer(copyOfBoard).equals(O) && temporaryScore > highestScore) {
                    highestScore = temporaryScore;
                    index = newIndex;
                } else if (computeCurrentPlayer(copyOfBoard).equals(X) && temporaryScore < lowestScore) {
                    highestScore = temporaryScore;
                    index = newIndex;
                }
            }
        return index;
    }

    public int evaluate(Board copyOfBoard) {
        int evaluation = 0;
        if (copyOfBoard.winningSymbolNought()) {
            evaluation = 1;
        }
        if (copyOfBoard.winningSymbolCross()) {
            evaluation = -1;
        }
        return evaluation;
    }


    public Symbol getSymbol() {
        return O;
    }

    private Symbol computeCurrentPlayer(Board board) {
        List<Integer> emptyCells = new ArrayList<>();
        IntStream.range(0, 9)
                .filter(cell -> board.get(cell).equals(E))
                .forEach(i -> emptyCells.add(i));
        return switchPlayers(emptyCells.size());
    }

    private Symbol switchPlayers(int emptyCells) {
        if ((emptyCells % 2) == 0) {
            return O;
        } else {
            return X;
        }
    }

    public int move() {
        return minimax(board, getSymbol());
    }
}


