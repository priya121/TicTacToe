package ttt;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    @Test
        public void placeCrossOnEmptyBoard() {
            List<String> board = new ArrayList<String>();
            List<String> emptyBoard = new ArrayList<String>();
            board.add("X");
            Game newGame  = new Game(emptyBoard);
            Assert.assertEquals(board, newGame.move("X"));
        }

    @Test
        public void placesSymbolOnTwoByTwoBoard() {
            List<String> board = new ArrayList<String>();
            List<String> emptyBoard = new ArrayList<String>();
            Game newGame  = new Game(emptyBoard);
            board.add("O");
            Assert.assertEquals(board, newGame.move("O"));
        }
}

