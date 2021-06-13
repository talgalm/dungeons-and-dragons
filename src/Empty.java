public class Empty extends Tile {

    public Empty (Position pos,char c)
    {
        super(pos,c);
    }

    @Override
    public void Interaction(Tile tile) {
        Position newpO = tile.GetPosition();
        tile.position = position;
        position = newpO;

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
        this.Interaction(tile);
    }





}
