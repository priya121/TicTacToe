package ttt.inputOutput;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ReplayIO implements IO {

    private File file;
    private LinkedList<String> moveArray;
    private LinkedList<String> timeStamps;
    private PrintStream output;

    public ReplayIO(File file, PrintStream output) {
        this.file = file;
        this.output = output;
        this.moveArray = new LinkedList<>();
        this.timeStamps = new LinkedList<>();
    }

    @Override
    public String takeInput() {
        getMovesFromFile();
        return moveArray.pop();
    }

    @Override
    public String showOutput(String message) {
        output.println(message);
        return null;
    }

    public List getMovesFromFile() {
        try (FileReader reader = new FileReader(file);
             BufferedReader buffer = new BufferedReader(reader)) {
            String line;
            while ((line = buffer.readLine()) != null) {
                moveArray.add(line);
                buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moveArray;
    }

    public List getMoveTimes() {
        try (FileReader reader = new FileReader(file);
             BufferedReader buffer = new BufferedReader(reader)) {
            buffer.readLine();
            String time;
            while ((time = buffer.readLine()) != null) {
                timeStamps.add(time);
                buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return timeStamps;

    }
}
