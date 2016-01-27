package ttt.board;

import ttt.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static ttt.Symbol.E;
import static ttt.Symbol.X;
import static ttt.Symbol.O;

public class Board {
    private List<Symbol> board;
    private int size;

    public Board(int size) {
        List<Symbol> newBoard = new ArrayList<>();
        for (int i = 0; i < (size * size); i++) {
            newBoard.add(E);
        }
        this.board = newBoard;
        this.size = (int) Math.sqrt(newBoard.size());
    }

    public Board(List<Symbol> board) {
        this.board = board;
        this.size = (int) Math.sqrt(board.size());
    }

    public List<List<Symbol>> getRows() {
        List<List<Symbol>> rows = new ArrayList<>();
        for (int i = 0; i < size * size; i += size) {
            List<Symbol> line = new ArrayList<>();
            for (int j = i; j < i + size; j++) {
                line.add(contentsOfBoard().get(j));
            }
            rows.add(line);
        }
        return rows;
    }

    public List<List<Symbol>> getColumns() {
        List<List<Symbol>> columns = new ArrayList<>();
        for (int i = 0; i < (size); i++) {
            List<Symbol> line = new ArrayList<>();
            for (int j = i; j < (size * size); j += size) {
                line.add(contentsOfBoard().get(j));
            }
            columns.add(line);
        }
        return columns;
    }

    public List<List<Symbol>> getDiagonals() {
        List<List<Symbol>> diagonals = new ArrayList<>();
        List<Symbol> leftToRightDiagonal = new ArrayList<>();
        List<Symbol> rightToLeftDiagonal = new ArrayList<>();

        for (int i = 0; i < size * size; i += size + 1) {
            leftToRightDiagonal.add(contentsOfBoard().get(i));
        }
        for (int j = size - 1; j < size * size - 1; j += size - 1) {
            rightToLeftDiagonal.add(contentsOfBoard().get(j));
        }
        diagonals.add(leftToRightDiagonal);
        diagonals.add(rightToLeftDiagonal);
        return diagonals;
    }

    boolean checkWinningLine(List<List<Symbol>> lines, Symbol symbol) {
        boolean winningLine = false;
        for (List<Symbol> line : lines) {
            for (int i = 0; i < line.size(); i++) {
                winningLine = line.get(i) == symbol;
                if (!winningLine) break;
            }
            if (winningLine) return true;
        }
        return winningLine;
    }

    boolean checkWins(Symbol symbol) {
        return (checkWinningLine(getRows(), symbol) || checkWinningLine(getColumns(), symbol) || checkWinningLine(getDiagonals(), symbol));
    }

    public List<Symbol> markPlayer(int indexPosition, Symbol player) {
        board.set(indexPosition, player);
        return board;
    }

    public List<Symbol> contentsOfBoard() {
        return board;
    }

    public Symbol get(int index) {
        return board.get(index);
    }

    public boolean isPositionEmpty(int move) {
        return validMoves().contains(move);
    }

    public boolean notFull() {
        return board.contains(E);
    }

    public List<Integer> validMoves() {
        List<Integer> validMoves = new ArrayList<>();
        IntStream.range(0, this.board.size())
                .filter(index -> this.board.get(index).equals(E))
                .forEach(i -> validMoves.add(i));
        return validMoves;
    }

    public boolean gameNotOver() {
        return !isWin() && notFull();
    }

    public boolean isWin() {
        return checkWins(O) || checkWins(X);
    }

    public boolean winningSymbolCross() {
        return checkWins(X);
    }

    public boolean winningSymbolNought() {
        return checkWins(O);
    }

    public void clone(Board board) {
        for (int i = 0; i < board.contentsOfBoard().size(); i++) {
            this.markPlayer(i, board.get(i));
        }
    }
}
