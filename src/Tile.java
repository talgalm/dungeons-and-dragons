public class Tile{
    protected char character;
    protected Position position;
    protected Tile (char c , Position pos)
    {
        character = c;
        position = pos;
    }
    public char GetChar()
    {
        return character;
    }

    public Position GetPosition() {
        return position;
    }

    public void SetPosition(Position position) {
        this.position = position;
    }

    public char toChar(){
        return GetChar();
    }
}
