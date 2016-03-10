package ttt.observers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MoveLogger {

    public void transfer(String input, File destinationFilePath) throws IOException {
        try (FileWriter file = new FileWriter(destinationFilePath, true)) {
            file.write(input);
        }
    }
}
