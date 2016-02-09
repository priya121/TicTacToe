package ttt;

public enum GameType {
    HvH("1"),
    HvC("2"),
    CvH("3"),
    CvC("4");

    private String gameType;

    GameType(String gameType) {
        this.gameType = gameType;
    }
}

