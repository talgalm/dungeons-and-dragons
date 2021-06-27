package BusinessLayer;

public abstract class Tile implements Comparable<Tile>{
    protected char character;
    protected Position position;

    protected Tile (char c)
    {
        character = c;
    }

    public void init(Position pos){
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

    public int compareTo(Tile t){
        return GetPosition().compareTo(t.GetPosition());
    }
}
