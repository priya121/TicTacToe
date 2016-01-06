package ttt.inputOutput;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class ConsoleIOTest {
    OutputStream recordedOutputStream = new ByteArrayOutputStream();
    PrintStream actualOutput = new PrintStream(recordedOutputStream);

    @Test
    public void takesUserInput() {
    InputStream input = new ByteArrayInputStream("0\n".getBytes());
    ConsoleIO console = new ConsoleIO(input, null);
    Assert.assertEquals("0", console.takeInput());
    }

    @Test
    public void displaysMessage() {
        InputStream input = new ByteArrayInputStream("0\n".getBytes());
        ConsoleIO console = new ConsoleIO(input, actualOutput);
        console.showOutput("Make your move:");
        Assert.assertEquals(recordedOutputStream.toString(), "Make your move:\n");
    }
}

