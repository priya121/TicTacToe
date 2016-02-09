package ttt.inputOutput;

import ttt.GameType;
import static ttt.GameType.*;

public class GameChooser {
    private IO io;

    public GameChooser(IO io) {
        this.io = io;
    }

    public GameType gameType(String choice) {
        switch(choice) {
            case("1"):
                return HvH;
            case("2"):
                return HvC;
            case("3"):
                return CvH;
            case("4"):
                return CvC;
        }
        return HvH;
    }
}
