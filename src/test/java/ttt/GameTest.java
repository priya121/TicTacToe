package ttt;

import org.junit.Assert;
import org.junit.Test;
import ttt.board.Board;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.FakeIO;
import ttt.player.ComputerPlayer;
import ttt.player.FakeComputerPlayer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static ttt.Symbol.CROSS;
import static ttt.Symbol.NOUGHT;
import static ttt.Symbol.EMPTY;

public class GameTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    List<Symbol> emptyBoard = emptyBoard(3, 3);
    Board currentBoard = new Board(emptyBoard);

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private FakeComputerPlayer getFakeComputerMoves(List<Integer> input) {
        return new FakeComputerPlayer(input);
    }

    public List<Symbol> emptyBoard(int height, int width) {
        List<Symbol> board = new ArrayList<Symbol>();
        for (int i = 0; i < height*width; i++) {
            board.add(EMPTY);
        }
        return board;
    }

    public List<Symbol> placeSymbols(List<Integer> crossPositions, List<Integer> noughtPositions) {
        List<Symbol> board = emptyBoard(3, 3);
        for (int i = 0; i < crossPositions.size(); i++) {
            for (int j = 0; j < noughtPositions.size(); j++) {
                board.set(noughtPositions.get(j), NOUGHT);
            }
            board.set(crossPositions.get(i), CROSS);
        }
        return board;
    }

    @Test
    public void boardChangedWhenPlayerMakesMove() {
        List <Symbol> changedBoard = placeSymbols(Arrays.asList(0, 2, 1), Arrays.asList(3, 7));
        FakeIO fakeInput = getFakeIO(Arrays.asList("0", "2", "1"));
        ComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(3, 7));
        Game game = new Game(currentBoard, fakeInput, computerMoves);
        game.gameLoop();
        Assert.assertEquals(changedBoard, currentBoard.getCurrentBoard());
    }

    @Test
    public void switchesPlayersFromCrossToNought() {
        List <Symbol> changedBoard = placeSymbols(Arrays.asList(0, 1, 4), Arrays.asList(6, 7, 8));
        FakeIO fakeInput = getFakeIO(Arrays.asList("0", "1", "4"));
        FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(6, 7, 8));
        Game game = new Game(currentBoard, fakeInput, computerMoves);
        game.gameLoop();
        Assert.assertEquals(changedBoard, currentBoard.getCurrentBoard());
    }

    @Test
    public void tellsUserWhenGameOver() {
        InputStream inputStream = new ByteArrayInputStream("2\n5\n8\n".getBytes());
        FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(1, 4, 7));
        ConsoleIO io = new ConsoleIO(inputStream, out);
        Game newGame = new Game(currentBoard, io, computerMoves);
        newGame.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("game over"));
    }

    @Test(expected = Exception.class)
    public void userMustEnterDigits() {
        InputStream inputStream = new ByteArrayInputStream("a\n1\n1\n".getBytes());
        FakeComputerPlayer computerMoves = getFakeComputerMoves(Arrays.asList(1));
        ConsoleIO io = new ConsoleIO(inputStream, out);
        Game newGame = new Game(currentBoard, io, computerMoves);
        newGame.gameLoop();
        Assert.assertTrue(recordedOutput.toString().contains("please enter a number from 0-8"));
    }
}

