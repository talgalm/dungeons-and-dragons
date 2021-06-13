public class Enemy extends Unit {

    private int experience_value;
    private MessageCallBack messageCallBack;
    private DeathCallBack deathCallBack;
    private MoveCallBack moveCallBack;

    public Enemy(Position position, char tile, String name, int healthCapacity, int attack, int defence,int experience_value) {
        super(position, tile, name, healthCapacity, attack, defence);
        this.experience_value = experience_value;
    }

    public int GetExpreience(){ return experience_value; }
    public void onKill(Player p){
        messageCallBack.send("GAME OVER");
        p.setCharacter('X');
    }
    public void onDeath(){

    }
    public void Interaction(Player p) {
        this.Combat(p);
        if(!p.isAlive())
            onKill(p);
    }

    @Override
    public void Interaction(Enemy enemy) {
        int x=0;
    }

    @Override
    public void Interaction(Empty empty) {
        moveCallBack.move(this.GetPosition() , empty.GetPosition()); }




    public void accept(Empty e) { this.Interaction(e); }



    public void accept(Player p){ this.Interaction(p);}
    public void accept(Wall w) {}

    @Override
    public void Interaction(Tile tile) {

    }

    public void accept(Tile t) {
    }



    public Position Move(Position pos)
    {
       return  null;
    }
}

