public class Enemy extends Unit {

    private int experience_value;

    public Enemy(Position position, char tile, String name, int healthCapacity, int attack, int defence,int experience_value) {
        super(position, tile, name, healthCapacity, attack, defence);
        this.experience_value = experience_value;
    }

    public int GetExpreience(){ return experience_value; }
    public void onKill(Player p){
        messageCallBack.send("GAME OVER");
        p.setChar('X');
    }
    public void onDeath(){

    }
    public void Interaction(Player p) {
        this.combat(p);
        if(!p.isAlive())
            onKill(p);
    }
    @Override
    public void Interaction(Empty empty) {
        moveCallBack.move(this.GetPosition() , empty.GetPosition()); }
    @Override
    public void interaction(Tile tile) { }
    public void accept(Empty e) { this.interaction(e); }
    public void accept(Player p){ this.interaction(p);}
    public void accept(Wall w) {}
    public void accept(Tile t) {
    }
}

