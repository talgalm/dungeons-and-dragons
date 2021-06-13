public class Wall extends Tile{
    public Wall(Position pos,char c )
    {
        super(pos,c);

    }

    @Override
    public void Interaction(Tile tile) {

    }

    @Override
    public void Interaction(Empty empty) {

    }

    @Override
    public void Interaction(Wall wall) {

    }

    @Override
    public void Interaction(Enemy enemy) {

    }


    @Override
    public void accept(Tile tile) {

    }


}
