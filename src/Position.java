public class Position {
    private int x;
    private int y;


    public Position(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public double Range(Position pos)
    {
        return Math.sqrt(Math.pow((getX() - pos.getX()),2) + Math.pow((getY() - pos.getY()),2));
    }
    public void Move (int newX , int newY)
    {
        this.x = newX;
        this.y = newY;
    }
}
