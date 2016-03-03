package ttt.player;

import ttt.Symbol;
import ttt.board.Board;

import static ttt.Symbol.O;
import static ttt.Symbol.X;

public class AIComputerPlayer implements Player {
    private Symbol opponentSymbol;
    private Symbol winningSymbol;

    public AIComputerPlayer(Symbol symbol) {
        this.winningSymbol = symbol;
        this.opponentSymbol = winningSymbol == X ? O : X;
    }

    public BestMove negamax(int depth, int alpha, int beta, Board board, Symbol currentSymbol) {
        BestMove bestMove = new BestMove(-1, 0);
        bestMove.score = -100;

        if (!board.gameNotOver() || depth == 0) {
            return new BestMove(-1, -scoreBoard(board));
        }

        for (int emptyCell : board.validMoves()) {

            Board copyOfBoard = board.markPlayer(emptyCell, currentSymbol);
            BestMove newScore = negamax(depth - 1, -beta, -alpha, copyOfBoard, switchPlayer(currentSymbol));

            int temporaryScore = -newScore.score;
            setScore(bestMove, emptyCell, temporaryScore);

            alpha = Math.max(alpha, bestMove.score);
            if (alpha >= beta) {
                break;
            }
        }
        return new BestMove(bestMove.index, bestMove.score);
    }

    private void setScore(BestMove bestMove, int emptyCell, int temporaryScore) {
        if (temporaryScore > bestMove.score) {
            bestMove.index = emptyCell;
            bestMove.score = temporaryScore;
        }
    }

    private Symbol switchPlayer(Symbol currentSymbol) {
        return currentSymbol == winningSymbol ? opponentSymbol : winningSymbol;
    }

    public int scoreBoard(Board copyOfBoard) {
        if (copyOfBoard.hasWon(X) || copyOfBoard.hasWon(O)) {
            return 1;
        }
        return 0;
    }

    public Symbol playerSymbol() {
        return winningSymbol;
    }

    public int move(Board board) {
        BestMove bestMove = negamax(7, -100, +100, board, winningSymbol);
        return bestMove.index;
    }
}
