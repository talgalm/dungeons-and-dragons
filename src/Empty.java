public class Empty extends Tile {

    public Empty(Position pos, char c) {
        super(pos, c);
    }

    @Override
    public void TickUp() { }

    @Override
    public void VisitedBy(Unit unit) {
        unit.accept(this);
    }
}
