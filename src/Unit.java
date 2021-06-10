public abstract class Unit extends Tile{

    protected MessageCallBack messageCallBack;
    private String name;
    private int attackPoints;
    private int defensePoints;
    private Resource health;

    public Unit(Position position,char tile, String name, int healthCapacity, int attack, int defence){
        super(position,tile);
        this.name = name;
        this.health = new Resource(healthCapacity, healthCapacity);
        this.attackPoints = attack;
        this.defensePoints = defence;
    }

    public int getAttackPoints(){
        return attackPoints;
    }
    public int getDefensePoints(){
        return defensePoints;
    }
    public void addAttackPoints( int playerlLevel){
        this.attackPoints = attackPoints + (4 * playerlLevel); }
    public void addDefensePoints(int playerLevel){ this.defensePoints = defensePoints + playerLevel;}
    public void SetAttackPoints(int newAttackPoints){ this.attackPoints = newAttackPoints; }
    public Resource getHealth() { return health;}

    public void Interaction(Empty empty) {
        Position emptyPosition = empty.GetPosition();
        empty.SetPosition(GetPosition());
        SetPosition(emptyPosition);
    }

    public void Interaction(Wall wall){}
    public abstract void Interaction(Player player);
    public abstract void Interaction(Enemy enemy);
    public boolean isAlive(){
        return getHealth().getResourceAmount() > 0;
    }

}
