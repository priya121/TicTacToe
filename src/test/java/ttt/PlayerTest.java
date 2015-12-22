package ttt;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.InputStream;

public class PlayerTest {
    Symbol nought = (Symbol.NOUGHT);
    Symbol cross = (Symbol.CROSS);
    Symbol empty = (Symbol.EMPTY);
    List<Symbol> emptyBoard = Arrays.asList(empty, empty, empty,
                                            empty, empty, empty,
                                            empty, empty, empty);
    Board currentBoard = new Board(emptyBoard);

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    @Test
        public void playerMarksMove() {
            FakeIO fakeInput = getFakeIO(Arrays.asList("0"));
            Player player = new Player(fakeInput, currentBoard);
            Assert.assertEquals("0", player.takesUserInput());
        }

    @Test
        public void boardChangedWhenPlayerMakesMove() {
        List<Symbol> changedBoard = Arrays.asList(cross, nought, empty,
                                                  empty, empty, empty,
                                                  empty, empty, empty);
            FakeIO fakeInput = getFakeIO(Arrays.asList("0", "1"));
            Player player = new Player(fakeInput, currentBoard);
            player.markBoard();
            player.markBoard();
            Assert.assertEquals(changedBoard, currentBoard.getCurrentBoard());
        }

    @Test
        public void switchesPlayersFromCrossToNought() {
        List<Symbol> changedBoard = Arrays.asList(cross, nought, empty,
                                                  empty, empty, empty,
                                                  empty, empty, empty);
            FakeIO fakeInput = getFakeIO(Arrays.asList("0", "1"));
            Player player = new Player(fakeInput, currentBoard);
            player.markBoard();
            player.markBoard();
            Assert.assertEquals(changedBoard, currentBoard.getCurrentBoard());
        }

    @Test
        public void switchesPlayerFromNoughtToCross() {
        List<Symbol> changedBoard = Arrays.asList(cross, cross, empty,
                                                  empty, nought, empty,
                                                  empty, nought, cross);
            FakeIO fakeInput = getFakeIO(Arrays.asList("0", "7", "8", "4", "1"));
            Player player = new Player(fakeInput, currentBoard);
            player.markBoard();
            player.markBoard();
            player.markBoard();
            player.markBoard();
            player.markBoard();
            Assert.assertEquals(changedBoard, currentBoard.getCurrentBoard());
        }

    @Test
        public void getsThePlayerWhoJustPlayed() {
            FakeIO fakeInput = getFakeIO(Arrays.asList("0", "7", "8", "4", "1"));
            Player player = new Player(fakeInput, currentBoard);
            player.markBoard();
            player.markBoard();
            Assert.assertEquals("O", player.getSymbol());
        }

    @Test(expected = Exception.class)
        public void playerOnlyMakesValidMove() {
        ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(recordedOutput);
        List<Symbol> newBoard = Arrays.asList(empty, cross, empty,
                                              empty, nought, empty,
                                              empty, nought, cross);
        InputStream inputStream = new ByteArrayInputStream("a\n1\n".getBytes());
        ConsoleIO io = new ConsoleIO(inputStream, out);
        Board currentBoard  = new Board(newBoard);
        Player player = new Player(io, currentBoard);
        player.markBoard();
        Assert.assertTrue(recordedOutput.toString().contains("Please enter a number from 0-8:"));
        }
}

class FakeIO implements IO {
    private LinkedList<String> input;

    public FakeIO(List<String> input) {
        this.input = new LinkedList<String>(input);
    }

    @Override
        public String takeInput() {
            return input.pop();
        }

    @Override
        public String showOutput(String message) {
            return message;
        }
}
