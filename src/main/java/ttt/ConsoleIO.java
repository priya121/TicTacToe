package ttt;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.InputStream;

public class ConsoleIO implements IO {
    BufferedReader input;
    PrintStream output;

    public ConsoleIO(InputStream input, PrintStream output) {
        this.input = new BufferedReader(new InputStreamReader(input));
        this.output = output;
    }

    @Override
    public String takeInput() {
        try {
            return input.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String showOutput(String message) {
        output.println(message);
        return null;
    }
}

