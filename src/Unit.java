public abstract class Unit extends Tile{
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

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }
    
    public int getResourcePool() {
        return health.getResourcePool();
    }
    public int getResourceAmount() {
        return health.getResourceAmount();
    }

    public void takeDamage(int damage)
    {
        health.setResourceAmount(health.getResourceAmount()-damage);
    }

    public void Combat(Unit unit)
    {
        MassageCallBack.send("description of the beginning of combat");
        int damage = Math.max((getAttackPoints()- unit.getDefensePoints()),0);
        unit.takeDamage(damage);
        MassageCallBack.send("description of the end of combat");
    }

}
