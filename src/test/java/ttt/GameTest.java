package ttt;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.InputStream;

public class GameTest {
    Symbol cross = Symbol.CROSS;
    Symbol nought = Symbol.NOUGHT;
    Symbol empty = Symbol.EMPTY;
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    List<Symbol> board = Arrays.asList(empty, empty, empty,
                                       empty, empty, empty,
                                       empty, empty, empty);
    List<Symbol> emptyBoard = Arrays.asList(empty, empty, empty,
                                            empty, empty, empty,
                                            empty, empty, empty);
    Board newBoard = new Board(emptyBoard);
    DisplayBoard newDisplay = new DisplayBoard(newBoard.getCurrentBoard());

    @Test
        public void tellsUserWhenGameOver() {
            InputStream inputStream = new ByteArrayInputStream("2\n1\n5\n3\n8\n".getBytes());
            ConsoleIO io = new ConsoleIO(inputStream, out);
            Game newGame = new Game(newBoard, io);
            newGame.gameLoop();
            Assert.assertTrue(recordedOutput.toString().contains("game over"));
        }

    @Test(expected = Exception.class)
        public void userMustEnterDigits() {
            InputStream inputStream = new ByteArrayInputStream("a\n1\n1\n".getBytes());
            ConsoleIO io = new ConsoleIO(inputStream, out);
            Game newGame = new Game(newBoard, io);
            newGame.gameLoop();
            Assert.assertTrue(recordedOutput.toString().contains("please enter a number from 0-8"));
    }

    @Test
        public void userMustEnterEmptyPositionIndex() {
            InputStream inputStream = new ByteArrayInputStream("0\n1\n1\n3\n2\n6\n2\n".getBytes());
            ConsoleIO io = new ConsoleIO(inputStream, out);
            Game newGame = new Game(newBoard, io);
            newGame.gameLoop();
            Assert.assertTrue(recordedOutput.toString().contains("That position is already taken, try again."));
    }
}

