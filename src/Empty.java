public class Empty extends Tile {
    public Empty (Position pos,char c)
    {
        super(pos,c);
    }

    @Override
    public void visit(Tile tile) {

    }
    @Override
    public void interaction(Tile tile)
    {
        position = tile.GetPosition();
        tile.position = position;
    }

    @Override


}
