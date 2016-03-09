package ttt;

public class PlayerObserver extends Observer{

    private final Game game;

    public PlayerObserver(Game game) {
        this.game = game;
        this.game.attach(this);
    }

    @Override
    public String update() {
        return String.valueOf(game.getCurrentPlayer());
    }
}
