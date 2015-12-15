package ttt;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Assert;

public class ConsoleIOTest {
    OutputStream recordedOutputStream = new ByteArrayOutputStream();
    PrintStream actualOutput = new PrintStream(recordedOutputStream);

    @Test
    public void takesUserInput() {
    InputStream input = new ByteArrayInputStream("0\n".getBytes());
    ConsoleIO console = new ConsoleIO(input, null);
    Assert.assertEquals("0", console.takeInput());
    }
}

