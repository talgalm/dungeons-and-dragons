package BusinessLayer;

import java.util.Objects;

public class Position implements Comparable<Position>{
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

    @Override
    public int compareTo(Position o) {
        if (getY() != o.getY())
            return getY() - o.getY();
        return getX() - o.getX();
    }


    public boolean equals(Position p) {
        if (this == p) return true;
        return x == p.getX() && y == p.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
