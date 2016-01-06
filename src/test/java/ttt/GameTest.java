package ttt;

import org.junit.Assert;
import org.junit.Test;
import ttt.board.Board;
import ttt.inputOutput.ConsoleIO;
import ttt.player.FakeComputerPlayer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class GameTest {
    Symbol empty = Symbol.EMPTY;
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    List<Symbol> emptyBoard = Arrays.asList(empty, empty, empty,
                                            empty, empty, empty,
                                            empty, empty, empty);
    Board newBoard = new Board(emptyBoard);

    private FakeComputerPlayer getFakeComputerMoves(List<Integer> input) {
        return new FakeComputerPlayer(input);
    }

    @Test
        public void tellsUserWhenGameOver() {
            InputStream inputStream = new ByteArrayInputStream("2\n5\n8\n".getBytes());
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(1, 3));
            ConsoleIO io = new ConsoleIO(inputStream, out);
            Game newGame = new Game(newBoard, io, fakeComputerMoves);
            newGame.gameLoop();
            Assert.assertTrue(recordedOutput.toString().contains("game over"));
        }

    @Test(expected = Exception.class)
        public void userMustEnterDigits() {
            InputStream inputStream = new ByteArrayInputStream("a\n1\n1\n".getBytes());
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(1));
            ConsoleIO io = new ConsoleIO(inputStream, out);
            Game newGame = new Game(newBoard, io, fakeComputerMoves);
            newGame.gameLoop();
            Assert.assertTrue(recordedOutput.toString().contains("please enter a number from 0-8"));
    }

    @Test
        public void userMustEnterEmptyPositionIndex() {
            InputStream inputStream = new ByteArrayInputStream("0\n0\n1\n2\n".getBytes());
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(8, 4, 7));
            ConsoleIO io = new ConsoleIO(inputStream, out);
            Game newGame = new Game(newBoard, io, fakeComputerMoves);
            newGame.gameLoop();
            Assert.assertTrue(recordedOutput.toString().contains("That position is already taken, try again."));
    }

    @Test
        public void markBoardSwitchesPlayersAfterEveryMove() {
            InputStream inputStream = new ByteArrayInputStream("0\n0\n1\n2\n".getBytes());
            FakeComputerPlayer fakeComputerMoves = getFakeComputerMoves(Arrays.asList(8, 4, 7));
            ConsoleIO io = new ConsoleIO(inputStream, out);
            Game newGame = new Game(newBoard, io, fakeComputerMoves);
            newGame.gameLoop();
            Assert.assertTrue(recordedOutput.toString().contains("That position is already taken, try again."));
        }
}

