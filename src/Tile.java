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
    public void SetCharacter(char character) { this.character = character; }

    public Position GetPosition() {
        return position;
    }
    public void SetPosition(Position position) {
        this.position = position;
    }

    public char ToChar(){
        return GetChar();
    }

    public abstract void TickUp();
    public abstract void VisitedBy(Unit unit);
}
