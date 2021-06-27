package BusinessLayer;

public class Wall extends Tile{
    public Wall(char c)
    {
        super(c);
    }

    @Override
    public void TickUp() { }

    @Override
    public void VisitedBy(Unit unit) {
        unit.accept(this);
    }
}
