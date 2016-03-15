package ttt.observers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameLog implements AutoCloseable{

    public void transfer(String input, File destinationFilePath) throws IOException {
        try (FileWriter file = new FileWriter(destinationFilePath, true)) {
            file.write(input);
        }
    }

    @Override
    public void close() throws Exception {
    }
}
