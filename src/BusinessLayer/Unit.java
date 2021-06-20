package BusinessLayer;

import Callbacks.DeathCallBack;
import Callbacks.MessageCallBack;

public abstract class Unit extends Tile{

    protected DeathCallBack deathCallBack;
    protected MessageCallBack messageCallBack;
    protected String name;
    protected int attackPoints;
    protected int defensePoints;
    protected Resource health;
    private InputProvider inputProvider = new InputProvider();

    public Unit(char tile, String name, int healthCapacity, int attack, int defence){
        super(tile);
        this.name = name;
        this.health = new Resource(healthCapacity, healthCapacity);
        this.attackPoints = attack;
        this.defensePoints = defence;
    }

    public void init(Position pos, DeathCallBack deathCallBack, MessageCallBack messageCallBack){
        this.deathCallBack = deathCallBack;
        this.messageCallBack = messageCallBack;
        super.init(pos);
    }

    public String GetName(){return name;}
    public int GetAttackPoints() {
        return attackPoints;
    }
    public int GetDefensePoints() { return defensePoints; }
    public int GetRandomAttackPoints() { return RandomSingleton.getInstance().nextInt(attackPoints);}
    public int GetRandomDefensePoints() { return RandomSingleton.getInstance().nextInt(defensePoints); }


    public Position MoveTo(char whereTo){
        Position p = inputProvider.getNewPosition(GetPosition(), whereTo);
        return p;
    }
    public boolean IsAlive() { return health.GetResourceCurrent() > 0; }
    public void TakeDamage(int damage) { health.SetResourceCurrent(health.GetResourceCurrent()-damage); }


    public void Combat(Unit unit)
    {

        messageCallBack.Send("\n" + this.GetName() + " engaged in combat with " + unit.GetName());
        messageCallBack.Send(this.getDescription());
        messageCallBack.Send(unit.getDescription());
        int RA = this.GetRandomAttackPoints();
        int RD = unit.GetRandomDefensePoints();
        messageCallBack.Send(this.GetName() + " rolled " + RA + " attack points ");
        messageCallBack.Send(unit.GetName() + " rolled " + RD + " defense points ");
        int damage = Math.max((RA-RD ),0);
        messageCallBack.Send(this.GetName() + " took "  +damage+ " health points from " + unit.GetName() + "\n");
        unit.TakeDamage(damage);

    }

    protected void castAssist(Player player, Enemy enemy, int damage, String abilityName){
        messageCallBack.Send(String.format("\n%s used the %s on %s", GetName(), abilityName, enemy.GetName()));
        messageCallBack.Send(enemy.getDescription());
        int RD = enemy.GetRandomDefensePoints();
        messageCallBack.Send(String.format("%s's damage is %s attack points", abilityName, damage));
        messageCallBack.Send(String.format("%s rolled %d defense points", enemy.GetName(), RD));
        int inflictedDamage = Math.max((damage-RD ),0);
        messageCallBack.Send(String.format("%s took %d health points from %s\n", GetName(), inflictedDamage, enemy.GetName()));
        enemy.TakeDamage(inflictedDamage);
        if(!enemy.IsAlive())
            player.onAbilityKill(enemy);

    }

    public void accept(Empty empty) {
        SwapPositions(empty);
    }
    protected void SwapPositions(Tile t){
        Position tilePosition = t.GetPosition();
        t.SetPosition(GetPosition());
        SetPosition(tilePosition);
    }
    public void accept(Wall wall){ }
    public abstract void accept(Player player);
    public abstract void accept(Enemy enemy);

    public Resource getHealth() {
        return health;
    }
    public abstract String getDescription();
}
