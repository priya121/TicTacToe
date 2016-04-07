package ttt;

public class SizeChoice {
    public int size;

    public void setBoardSize(int sizeChosen) {
        size = (int) Math.sqrt(sizeChosen);
    }

    public int getBoardSize() {
        return size;
    }
}
