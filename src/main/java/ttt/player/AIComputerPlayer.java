package ttt.player;

import ttt.Symbol;
import ttt.board.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static ttt.Symbol.*;

public class AIComputerPlayer implements ComputerPlayer {
    Player opponent;
    Board board;
    Symbol winningSymbol;

    public AIComputerPlayer(Board board, Player opponent) {
        this.board = board;
        this.opponent = opponent;
        this.winningSymbol = calculateOwnSymbol();
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
            return new int[]{-1, evaluate(board)};
        }

        for (int emptyCell : board.validMoves()) {

            Board copyOfBoard = (Board) board.clone();

            Symbol currentPlayer = computeCurrentPlayer(copyOfBoard);
            copyOfBoard.markPlayer(emptyCell, currentPlayer);

            int[] newScore = minimax(copyOfBoard, computeCurrentPlayer(copyOfBoard));
            int temporaryScore = newScore[1];

            if (symbol.equals(winningSymbol) && temporaryScore >= score) {
                index = emptyCell;
                score = temporaryScore;
            } else if (symbol.equals(opponent.calculateOwnSymbol()) && temporaryScore <= score) {
                index = emptyCell;
                score = temporaryScore;
            }
        }
         return new int[]{index, score};
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

    public Symbol calculateOwnSymbol() {
        if (opponent.calculateOwnSymbol().equals(O)) {
            return X;
        } else {
            return O;
        }
    }

    public Symbol setPlayerSymbol() {
        return winningSymbol;
    }

    private Symbol computeCurrentPlayer(Board board) {
        List<Integer> emptyCells = new ArrayList<>();
        IntStream.range(0, 9)
                .filter(cell -> board.get(cell).equals(E))
                .forEach(i -> emptyCells.add(i));
        return switchPlayers(emptyCells.size());
    }

    private Symbol switchPlayers(int emptyCells) {
        if (emptyCells % 2 == 0) return winningSymbol;
        return opponent.calculateOwnSymbol();
    }

    public int move() {
        int[] bestMove = minimax(board, winningSymbol);
        return bestMove[0];
    }
}
