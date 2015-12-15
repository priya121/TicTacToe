package ttt;

import java.util.List;

public class Board {
    List<String> board;
    String playerCross = "X";
    String playerNought = "O";

    public Board(List<String> board) {
        this.board = board;
    }

    public List<String> markPlayer(int indexPosition, String player) {
        board.set(indexPosition, player);
        return board;
    }

    public List<String> getCurrentBoard() {
        return board;
    }

    public String get(int index) {
        return board.get(index);
    }

 }
