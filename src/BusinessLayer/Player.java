package BusinessLayer;

import java.util.ArrayList;

public abstract class Player extends Unit {
    private int experience;
    protected int playerLevel;
    private final int POINTS = 50;
    private int toLevelUpExperience;


    public Player(char tile, String name, int healthCapacity, int attack, int defence) {
        super(tile, name, healthCapacity, attack, defence);
        experience = 0;
        playerLevel = 1;
        toLevelUpExperience = playerLevel*POINTS;
    }



    public void LevelUpHealthPoints(int playerLevel) {
        health.AddToResourceMax(10*playerLevel);
        health.SetResourceCurrent(health.GetResourceMax());
    }
    public void LevelUpAttackPoints(int playerLevel){attackPoints = attackPoints + (4 * playerLevel); }
    public void LevelUpDefensePoints(int playerLevel){ this.defensePoints = defensePoints + playerLevel;}

    public void LevelUp(){
        this.experience = experience - toLevelUpExperience;
        playerLevel = playerLevel + 1;
        LevelUpHealthPoints(playerLevel);
        LevelUpAttackPoints(playerLevel);
        LevelUpDefensePoints(playerLevel);
        toLevelUpExperience = playerLevel*POINTS;
        messageCallBack = System.out::println;
        messageCallBack.Send(GetName() +" reached level "  +getPlayerLevel() +": +" +10*playerLevel+ " Health Points, +"+(4 * playerLevel) + " Attack Points, +"+playerLevel +" Defence Points\n" );
    }


    public void AddExperience(int additionalExperience){
        this.experience += additionalExperience;
        while(experience > toLevelUpExperience ) {
            LevelUp();
        }
    }

    protected void onDeath(){
        deathCallBack.Call();
    }
    public void onKill(Enemy e){
        SwapPositions(e);
        onAbilityKill(e);
    }

    public void onAbilityKill(Enemy e){
        messageCallBack.Send(e.GetName() + " died. " + GetName() + " gained " + e.GetExperience() + " experience points" +"\n");
        AddExperience(e.GetExperience());
        e.onEnemyDeath();
        earnCash(e.health.GetResourceMax());
    }

    public void accept(Player player) { }

    public void accept(Enemy enemy) {
        this.Combat(enemy);
        if (!enemy.IsAlive()) {
            onKill(enemy);
        }
    }

    public void earnCash(int healthPoints) {}
    public abstract Enemy CastSpecialAbility(ArrayList<Enemy> Enemies) ;

    @Override
    public void VisitedBy(Unit unit) { unit.accept(this); }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public abstract String getAbility();
    public abstract String getDescription();

    public int getExperience() {
        return experience;
    }
}


