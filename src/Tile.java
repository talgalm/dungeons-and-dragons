public abstract class Tile{
    protected char character;
    protected Position position;
    protected Tile ( Position pos, char c )
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

    public abstract void interaction(Tile tile);

}
