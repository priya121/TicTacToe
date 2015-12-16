package ttt;

import java.util.List;
import java.util.ArrayList;

public class Board {
    List<String> board;
    String empty = "-";
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

    public boolean isValid(int move) {
        return validMoves().contains(move);
    }

    public boolean boardFull() {
        return board.contains(empty);
    }


    public List<Integer> validMoves() {
        List<Integer> validMoves = new ArrayList<Integer>();
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).equals(empty)) {
                validMoves.add(i);
            }
        }
        return validMoves;
    }


 }
