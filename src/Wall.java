public class Wall extends Tile{
    public Wall(Position pos,char c)
    {
        super(pos,c);
    }

    @Override
    public void TickUp() { }

    @Override
    public void VisitedBy(Unit unit) {
        unit.accept(this);
    }
}
