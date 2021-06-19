package BusinessLayer;

public class Empty extends Tile {

    public Empty(char c) {
        super(c);
    }

    @Override
    public void TickUp() { }

    @Override
    public void VisitedBy(Unit unit) {
        unit.accept(this);
    }
}
