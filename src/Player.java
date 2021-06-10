public class Player extends Unit{
    private Ability ability;
    public Player(Position position,char tile, String name, int healthCapacity, int attack, int defence,Ability ability) {
        super(position, tile, name, healthCapacity, attack, defence);
        this.ability = ability;
    }

    @Override
    public void Interaction(Player player) {

    }

    @Override
    public void Interaction(Enemy enemy) {

    }

    @Override
    public void visit(Tile tile) {

    }

    @Override
    public void interaction(Tile tile) {

    }
}
