package ttt.board;

import ttt.Symbol;

import java.util.List;

public class BoardDisplay {
    List<Symbol> board;
    String display = "";

    public BoardDisplay(List<Symbol> board) {
        this.board = board;
    }

    public String showBoard() {
        for (int i = 0; i < board.size(); i++) {
            display += " " + board.get(i).getSymbol() + " ";
            if ((i + 1) % Math.sqrt(board.size()) == 0) {
                display += "\n";
            }
        }
        return display;
    }
}
