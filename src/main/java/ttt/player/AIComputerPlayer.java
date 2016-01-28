package ttt.player;

import ttt.Symbol;
import ttt.board.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static ttt.Symbol.*;

public class AIComputerPlayer implements ComputerPlayer {
    Board board;
    Symbol symbol;
    Symbol symbolToWin;

    public AIComputerPlayer(Board board) {
        this.board = board;
        this.symbolToWin = this.symbol;
    }

    public int[] minimax(Board board, Symbol symbol) {
        int index = -1;
        int score = 0;

        if (symbol == O) {
            score = -100;
        } else {
            score = 100;
        }

        if (!board.gameNotOver()) {
            int[] result = new int[]{-1, evaluate(board)};
            return result;
        }

        for (int emptyCell : board.validMoves()) {

            Board copyOfBoard = new Board(3);
            copyOfBoard.clone(board);
            Symbol currentPlayer = computeCurrentPlayer(copyOfBoard);

            copyOfBoard.markPlayer(emptyCell, currentPlayer);

            int[] newScore = minimax(copyOfBoard, computeCurrentPlayer(copyOfBoard));
            int temporaryScore = newScore[1];

            if (symbol.equals(O) && temporaryScore >= score) {
                index = emptyCell;
                score = temporaryScore;
            } else if (symbol.equals(X) && temporaryScore <= score) {
                index = emptyCell;
                score = temporaryScore;
            }
        }
        int[] bestMove = new int[]{index, score};
        return bestMove;
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
        if (emptyCells % 2 == 0) return O;
        return X;
    }

    public int move() {
        int[] bestMove = minimax(board, getSymbol());
        return bestMove[0];
    }
}


