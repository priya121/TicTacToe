package ttt.board;

import ttt.Symbol;
import ttt.inputOutput.DigitValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static ttt.Symbol.*;

public class Board {
    final WinningLines lines;
    private final int size;
    private final List<Symbol> listOfSymbols;

    public Board(int size) {
        List<Symbol> listOfSymbols = new ArrayList<>();
        for (int i = 0; i < (size * size); i++) {
            listOfSymbols.add(E);
        }
        this.listOfSymbols = listOfSymbols;
        this.size = (int) Math.sqrt(listOfSymbols.size());
        this.lines = new WinningLines(size);
    }

    public Board(List<Symbol> listOfSymbols) {
        this.listOfSymbols = listOfSymbols;
        this.size = (int) Math.sqrt(listOfSymbols.size());
        this.lines = new WinningLines(size);
    }

    public boolean hasWon(Symbol symbol) {
         return lines.allWinningLines().filter(line -> hasWin(line, symbol))
                .findAny()
                .isPresent();
    }

    public boolean hasWin(IntStream line, Symbol symbol) {
        return line.mapToObj(index -> get(index))
                .allMatch(index -> index.equals(symbol));
    }

    boolean checkWins(Symbol symbol) {
        return (hasWon(symbol));
    }

    public Board markPlayer(int indexPosition, Symbol symbol) {
        List<Symbol> newBoard = new ArrayList<>(listOfSymbols);
        newBoard.set(indexPosition, symbol);
        return new Board(newBoard);
    }

    public List<Symbol> contentsOfBoard() {
        return listOfSymbols;
    }

    public Symbol get(int index) {
        return listOfSymbols.get(index);
    }

    public boolean isPositionEmpty(String move) {
        DigitValidator digitValidator = new DigitValidator();
        return digitValidator.check(move) && validMoves().contains(Integer.parseInt(move));
    }

    public boolean notFull() {
        return listOfSymbols.contains(E);
    }

    public List<Integer> validMoves() {
        return IntStream.range(0, listOfSymbols.size()).boxed()
                .filter(index -> listOfSymbols.get(index).equals(E))
                .collect(toList());
    }

    public boolean gameNotOver() {
        return !isWin() && notFull();
    }

    public boolean isWin() {
        return checkWins(O) || checkWins(X);
    }

    public boolean winningSymbol(Symbol symbol) {
        return checkWins(symbol);
    }
}
