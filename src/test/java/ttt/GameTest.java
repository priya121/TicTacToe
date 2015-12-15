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

public class GameTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    String empty = " ";
    List<String> board = Arrays.asList(empty, empty, empty,
                                       empty, empty, empty,
                                       empty, empty, empty);
    List<String> emptyBoard = Arrays.asList(empty, empty, empty,
                                            empty, empty, empty,
                                            empty, empty, empty);
    Board newBoard = new Board(emptyBoard);
    DisplayBoard newDisplay = new DisplayBoard(newBoard.getCurrentBoard());
    String cross = "X";
    String nought = "O";

    private FakeIOTwo getFakeIOTwo(List<String> input) {
        return new FakeIOTwo(input);
    }

    @Test
    public void checksWinFirstRowForCross() {
        FakeIOTwo io = getFakeIOTwo(Arrays.asList("0", "3", "1", "6", "2"));
        Game newGame = new Game(newBoard, io);
        Player player = new Player(io, newBoard);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, newGame.isWinningRow(cross));
    }

    @Test
    public void checksWinLastRowForCross() {
        FakeIOTwo io = getFakeIOTwo(Arrays.asList("1", "6", "5", "7", "2", "8"));
        Game newGame = new Game(newBoard, io);
        Player player = new Player(io, newBoard);
            for (int i = 0; i < 6; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, newGame.isWinningRow(nought));
    }

    @Test
    public void checksWinSecondRowForCross() {
        FakeIOTwo io = getFakeIOTwo(Arrays.asList("3", "0", "4", "6", "5"));
        Game newGame = new Game(newBoard, io);
        Player player = new Player(io, newBoard);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, newGame.isWinningRow(cross));
    }


    @Test
        public void checksNoughtWinsDiagonally() {
            FakeIOTwo io = getFakeIOTwo(Arrays.asList("1", "0", "3", "4", "7", "8"));
            Game newGame = new Game(newBoard, io);
            Player player = new Player(io, newBoard);
            for (int i = 0; i < 6; i++) {
                player.markBoard();
            }
            Assert.assertEquals(false, newGame.isWinningDiagonal(cross));
            Assert.assertEquals(true, newGame.isWinningDiagonal(nought));
        }

    @Test
        public void checksAnotherDiagonalWin() {
            FakeIOTwo io = getFakeIOTwo(Arrays.asList("1", "0", "3", "4", "7", "8"));
            Game newGame = new Game(newBoard, io);
            Player player = new Player(io, newBoard);
            for (int i = 0; i < 6; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, newGame.isWinningDiagonal(nought));
        }

    @Test
        public void checksDiagonalWinForCross() {
            FakeIOTwo io = getFakeIOTwo(Arrays.asList("0", "1", "4", "6", "8"));
            Game newGame = new Game(newBoard, io);
            Player player = new Player(io, newBoard);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, newGame.checkForWin());
        }

    @Test
        public void checksDiagonalWinForNought() {
            FakeIOTwo io = getFakeIOTwo(Arrays.asList("3", "2", "0", "6", "5", "4"));
            Game newGame = new Game(newBoard, io);
            Player player = new Player(io, newBoard);
            for (int i = 0; i < 6; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, newGame.checkForWin());
        }

    @Test
        public void checksVerticalWinMiddleColumnForCross() {
            FakeIOTwo io = getFakeIOTwo(Arrays.asList("1", "0", "4", "6", "7"));
            Game newGame = new Game(newBoard, io);
            Player player = new Player(io, newBoard);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, newGame.checkForWin());
        }

    @Test
        public void returnsFalseIfNoWin() {
            FakeIOTwo io = getFakeIOTwo(Arrays.asList("8", "0", "4", "6", "7"));
            Game newGame = new Game(newBoard, io);
            Player player = new Player(io, newBoard);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(false, newGame.checkForWin());
        }

    @Test
        public void checksVerticalWinFirstColumnForCross() {
            FakeIOTwo io = getFakeIOTwo(Arrays.asList("0", "1", "3", "8", "6"));
            Game newGame = new Game(newBoard, io);
            Player player = new Player(io, newBoard);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, newGame.isWinningColumn(cross));
            Assert.assertEquals(false, newGame.isWinningColumn(nought));
        }

    @Test
        public void checksVerticalWinLastColumn() {
            FakeIOTwo io = getFakeIOTwo(Arrays.asList("2", "1", "5", "3", "8"));
            Game newGame = new Game(newBoard, io);
            Player player = new Player(io, newBoard);
            for (int i = 0; i < 5; i++) {
                player.markBoard();
            }
            Assert.assertEquals(true, newGame.isWinningColumn(cross));
            Assert.assertEquals(false, newGame.isWinningColumn(nought));
        }

    @Test
        public void tellsUserWhenGameOver() {
            InputStream inputStream = new ByteArrayInputStream("2\n1\n5\n3\n8\n".getBytes());
            ConsoleIO io = new ConsoleIO(inputStream, out);
            Game newGame = new Game(newBoard, io);
            Player player = new Player(io, newBoard);
            newGame.gameLoop();
            Assert.assertTrue(recordedOutput.toString().contains("game over"));
        }
}

class FakeIOTwo implements IO {
    private LinkedList<String> input;

    public FakeIOTwo(List<String> input) {
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
