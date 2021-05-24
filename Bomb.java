public class Bomb {

    private int row, col, length, startTime;

    public Bomb(int r, int c, int l, int start)
    {
        row=r;
        col=c;
        length=l;
        startTime=start;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getLength() { return length; }

    public int getStart() { return startTime; }
    public void setStart(int time) { startTime=time; }

    public int getStartExplosion() { return startTime + 20; }

    public boolean inRange(Bomb bomb)
    {
        int r = bomb.getRow();
        int c = bomb.getCol();
        int l = bomb.getLength();
        if((r+l>=row && c==col && r-l<=row )|| (r==row && c+l>=col && c-l<=col))
            return true;
        return false;
    }
    public boolean inRange(Player p){
        int x = (p.getX() + 21) / 48;
        int y = (p.getY() + 21) / 48;
        if((row+length >= x && y == col && row-length <= x) || (row+length >= y && x == col && row-length <= y)){
            return true;
        }
        return false;
    }
}
