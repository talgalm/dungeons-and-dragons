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

    public abstract void Interaction(Tile tile);
    public abstract void Interaction(Empty empty);
    public abstract void Interaction(Wall wall);
    public abstract void Interaction(Enemy enemy);


    public void setCharacter(char character) {
        this.character = character;
    }


    public abstract void accept(Tile tile);
}
