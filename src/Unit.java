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

    public void moveLeft(){
        moveCallBack.move(new Position(position.getX()-1, position.getY()));
    }

    public void moveRight(){
        moveCallBack.move(new Position(position.getX()+1, position.getY()));
    }

    public void moveUp(){
        moveCallBack.move(new Position(position.getX(), position.getY()+1));
    }

    public void moveDown(){
        moveCallBack.move(new Position(position.getX(), position.getY()-1));
    }

    public void Interaction(Empty empty){
        Position emptyPosition = empty.GetPosition();
        empty.SetPosition(GetPosition());
        SetPosition(emptyPosition);
    }

    public void Interaction(Wall wall){}
    public abstract void Interaction(Player player);
    public abstract void Interaction(Enemy enemy);

    public void checkInteract(Tile tile) {
        tile.visit(this);
    }
    public Resource getHealth()
    {
        return health;
    }
    public void turn()
    {

    }
}
