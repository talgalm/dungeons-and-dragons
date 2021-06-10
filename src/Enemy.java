public class Enemy extends Unit {
    private int experience_value;
    public Enemy(Position position, char tile, String name, int healthCapacity, int attack, int defence,int experience_value) {
        super(position, tile, name, healthCapacity, attack, defence);
        this.experience_value = experience_value;
    }

    @Override
    public void visit(Tile tile) {

    }

    @Override
    public void interaction(Tile tile) {

    }

    public Position Move(Position pos)
    {
       return  null;
    }
}

