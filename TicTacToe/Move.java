

// Every move consists of a row col
public class Move {
    public int row;
    public int col;

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public void set(int row, int col) {
        this.row = row;
        this.col = col;
    }
}