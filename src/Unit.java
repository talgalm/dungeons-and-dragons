import Callbacks.DeathCallBack;
import Callbacks.MessageCallBack;

import java.util.Random;

public abstract class Unit extends Tile{

    protected DeathCallBack deathCallBack;
    protected MessageCallBack messageCallBack;
    private String name;
    protected int attackPoints;
    protected int defensePoints;
    protected Resource health;
    private InputProvider inputProvider = new InputProvider();
    private Random random = new Random();

    public Unit(Position position,char tile, String name, int healthCapacity, int attack, int defence){
        super(position,tile);
        this.name = name;
        this.health = new Resource(healthCapacity, healthCapacity);
        this.attackPoints = attack;
        this.defensePoints = defence;
    }
    public String GetName(){return name;}
    public int GetAttackPoints() {
        return attackPoints;
    }
    public int GetDefensePoints() { return defensePoints; }
    public int GetRandomAttackPoints() { return random.nextInt(attackPoints);}
    public int GetRandomDefensePoints() { return random.nextInt(defensePoints); }


    public Position MoveTo(char whereTo){
        return inputProvider.getNewPosition(GetPosition(), whereTo);
    }
    public boolean IsAlive() { return health.GetResourceCurrent() > 0; }
    public void TakeDamage(int damage) { health.SetResourceCurrent(health.GetResourceCurrent()-damage); }


    public void Combat(Unit unit)
    {
        messageCallBack.Send("description of the beginning of combat");
        int damage = Math.max((GetRandomAttackPoints()- unit.GetRandomDefensePoints()),0);
        unit.TakeDamage(damage);
        messageCallBack.Send("description of the end of combat");
    }

    public void accept(Empty empty) {
        Position emptyPosition = empty.GetPosition();
        empty.SetPosition(GetPosition());
        SetPosition(emptyPosition);
    }
    public void accept(Wall wall){ }
    public abstract void accept(Player player);
    public abstract void accept(Enemy enemy);
}
