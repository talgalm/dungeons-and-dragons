public class Wall extends Tile{
    public Wall(Position pos,char c )
    {
        super(pos,c);

    }

    @Override
    public void visit(Tile tile) {

    }
    @Override
    public void interaction(Tile tile)
    {
        //do nothing
    }



}
