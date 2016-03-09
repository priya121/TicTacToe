package ttt;

public class MoveObserver extends Observer {
    private final Game game;

    public MoveObserver(Game game) {
        this.game = game;
        this.game.attach(this);
    }

    @Override
    public int updateMove() {
        return game.getMove();
    }
}
