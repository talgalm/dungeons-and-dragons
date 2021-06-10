public class Player extends Unit{
    private Ability ability;
    private InputProvider inputProvider;
    private MessageCallBack messageCallBack;

    public Player(Position position,char tile, String name, int healthCapacity, int attack, int defence,Ability ability) {
        super(position, tile, name, healthCapacity, attack, defence);
        this.ability = ability;

        }
    @Override
    public void Interaction(Enemy enemy) {
        this.combat(enemy);
        if(!enemy.isAlive())
            onKill(e);
    }
    @Override
    public void Interaction(Empty empty) {
    moveCallBack.move(this.GetPosition() , empty.GetPosition());
    }
    @Override
    public void interaction(Tile tile) {
    }
    public void accept(Empty e) {
        this.interaction(e);
    }
    public void accept(Enemy e){
        this.interaction(e);
    }
    public void accept(Wall w) {
    }

    public void accept(Tile t) {
    }
}
