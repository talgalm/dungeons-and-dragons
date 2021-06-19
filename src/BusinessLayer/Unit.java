package BusinessLayer;

import Callbacks.DeathCallBack;
import Callbacks.MessageCallBack;

import java.util.Random;

public abstract class Unit extends Tile{

    protected DeathCallBack deathCallBack;
    protected MessageCallBack messageCallBack;
    protected String name;
    protected int attackPoints;
    protected int defensePoints;
    protected Resource health;
    private InputProvider inputProvider = new InputProvider();
    private Random random = new Random();

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
    public int GetRandomAttackPoints() { return random.nextInt(attackPoints);}
    public int GetRandomDefensePoints() { return random.nextInt(defensePoints); }


    public Position MoveTo(char whereTo){
        return inputProvider.getNewPosition(GetPosition(), whereTo);
    }
    public boolean IsAlive() { return health.GetResourceCurrent() > 0; }
    public void TakeDamage(int damage) { health.SetResourceCurrent(health.GetResourceCurrent()-damage); }


    public void Combat(Unit unit)
    {
        messageCallBack.Send(this.GetName() + " engaged in combat with " + unit.GetName());
        messageCallBack.Send(String.format("%s rolled %d attack", name, 2));
        int damage = Math.max((GetRandomAttackPoints()- unit.GetRandomDefensePoints()),0);
        unit.TakeDamage(damage);
        messageCallBack.Send(this.GetName() + " dealt "  +damage+ " to " + unit.GetName());
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
}
