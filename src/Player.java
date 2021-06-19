import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public abstract class Player extends Unit {
    private int experience;
    protected int playerLevel;
//    private Ability ability;
    private final int POINTS = 50;
    private int toLevelUpExperience;
    ///
    private Resource abilityResource = null;
    ///


    public Player(char tile, String name, int healthCapacity, int attack, int defence) {
        super(null, tile, name, healthCapacity, attack, defence);
        experience = 0;
        playerLevel = 1;
//        this.ability = ability;
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
    }


    public void AddExperience(int additionalExperience){
        this.experience += additionalExperience;
        while(experience > toLevelUpExperience ) {
            LevelUp();
        }
    }

    protected void onDeath(){
        messageCallBack.Send("YOU LOST");
        deathCallBack.Call();
    }
    public void onKill(Enemy e){
        AddExperience(e.GetExperience());
        e.onDeath();
    }


    public void accept(Player player) { }

    public void accept(Enemy enemy) {
        this.Combat(enemy);
        if (!enemy.IsAlive()) {
            onKill(enemy);
        }
    }



//    public void CastSpecialAbility(ArrayList<Enemy> Enemies,int current_tick)
//    {
////        if(abilityResource.GetResourceCurrent() < )
//        ability.cast(Enemies , GetPosition(),GetHealth().GetResourceCurrent(),GetDefensePoints(),current_tick);
//    }

    public abstract Enemy CastSpecialAbility(ArrayList<Enemy> Enemies) ;

    @Override
    public void VisitedBy(Unit unit) { unit.accept(this); }

}


