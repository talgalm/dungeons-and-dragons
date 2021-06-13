import java.util.ArrayList;

public class Player extends Unit {
    private Ability ability;
    private MessageCallBack messageCallBack;
    private DeathCallBack deathCallBack;
    private MoveCallBack moveCallBack;
    private int exprirence;
    private int playerLevel;
    private final int POINTS = 50;


    public Player(Position position,char tile, String name, int healthCapacity, int attack, int defence,Ability ability ) {
        super(position, tile, name, healthCapacity, attack, defence);
        this.ability = ability;
        exprirence = 0;
        playerLevel = 1;
        }

    protected void onDeath(){
        messageCallBack.send("YOU LOST");
        deathCallBack.call();
    }
    public void onKill(Enemy e){
        addExprience(e.GetExpreience());
        e.onDeath();
    }
    public void addExprience(int additionExprience){
        this.exprirence = additionExprience + exprirence;
        while(toLevelUp() > levelUpRequirment()) {
            levelUp();
            exprirence = exprirence - toLevelUp();
        }
    }
    public void levelUp(){
        this.exprirence = exprirence - (POINTS * playerLevel);
        playerLevel = playerLevel + 1;
        getHealth().setResourceAmount(getHealth().getResourcePool());
        getHealth().addHealthPool(playerLevel);
        addDefensePoints(playerLevel);
        int attack = ability.levelUp(playerLevel);
        SetAttackPoints(attack + getAttackPoints());
        addAttackPoints(playerLevel);
    }
    public int toLevelUp(){
        return (playerLevel * POINTS) - exprirence;
    }
    public int levelUpRequirment(){
        return playerLevel * playerLevel;
    }

    public void Interaction(Enemy enemy) {
        this.Combat(enemy);
        if(!enemy.isAlive()) {
            onKill(enemy);
            moveCallBack.move(this.GetPosition(), enemy.GetPosition());
        }
    }


    public void Interaction(Empty empty) {
        moveCallBack.move(this.GetPosition(), empty.GetPosition());
    }

    @Override
    public void Interaction(Player player) {

    }


    @Override
    public void Interaction(Tile tile) {
        int x=-1;
    }

    @Override
    public void accept(Tile tile) {
        this.Interaction(tile);
    }


    public void accept(Empty e) {
        this.Interaction(e);
    }
    public void accept(Enemy e){
        this.Interaction(e);
    }
    public void accept(Wall w) {
    }
    public void Cast_Special_Ability(ArrayList<Enemy> Enemies,int current_tick)
    {
        ability.cast(Enemies , GetPosition(),getHealth().getResourceAmount(),getDefensePoints(),current_tick);
    }

}

