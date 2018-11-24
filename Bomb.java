public class Bomb {
    private int row, col, length, startTime;

    public Bomb(int row, int col, int length, int startTime){
        this.row = row;
        this.col = col;
        this.length = length;
        this.startTime = startTime;
    }

    public int getCol() {
        return col;
    }

    public int getLength() {
        return length;
    }

    public int getRow() {
        return row;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getStartExplosion() { return startTime + 40;}

    //Trong tầm phát nổ của Bomb
    public boolean inRange(Bomb bomb){
        int row = bomb.getRow();
        int col = bomb.getCol();
        int length = bomb.getLength();
        if(row + length >= this.row && col == this.col || row - length <= this.row && col == this.col
        || col - length <= this.col && row == this.row || col + length >= this.col && row == this.row){
            return true;
        }
        return false;
    }
}
