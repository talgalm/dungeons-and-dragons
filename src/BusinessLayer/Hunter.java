package BusinessLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hunter extends Player{
    private Resource arrows;
    private int range;
    private int ticksCount;

    public Hunter(char tile, String name, int healthCapacity, int attack, int defence, int abilityRange) {
        super(tile, name, healthCapacity, attack, defence);
        this.arrows = new Resource(Integer.MAX_VALUE, 10);
        this.range = abilityRange;
        this.ticksCount = 0;
    }

    public void LevelUp(){
        arrows.AddToResourceCurrent(playerLevel*10);
        attackPoints += playerLevel*2;
        defensePoints += playerLevel;
        super.LevelUp();
        System.out.println(String.format("Ygritte received %d arrows to the Quiver\n", playerLevel*10));
    }

    public Enemy CastSpecialAbility(ArrayList<Enemy> Enemies){ //Shoot
        if(arrows.GetResourceCurrent() > 0) {
            List<Enemy> rangeEnemies = Enemies.stream().filter(t -> t.GetPosition().Range(GetPosition()) < range).collect(Collectors.toList());
            if (rangeEnemies != null) {
                Enemy unLuckyEnemy = findClosest(rangeEnemies);
                if (unLuckyEnemy != null) {
                    arrows.TakeFromResourceCurrent(1);
                    castAssist(this, unLuckyEnemy, GetAttackPoints(), "Shoot");
                }
            }
        }
        return null;
    }

    @Override
    public String getAbility() {
        return "  Ability: Shoot   arrows' Quiver: " + arrows.GetResourceCurrent()+"/"+arrows.GetResourceMax() +"   ability range: " +range ;
    }
    @Override
    public String getDescription() {
        return GetName() + "  Health: " +getHealth().GetResourceCurrent() + "/" +getHealth().GetResourceMax()
                + "  Attack: " +GetAttackPoints()
                + "  Defense: " +GetDefensePoints()
                + "  Level: " +playerLevel
                + "  Experience: " + getExperience() + getAbility();
    }

    private Enemy findClosest(List<Enemy> enemies) {
            Double minRange = null;
            Enemy closestEnemy = null;
            for (Enemy enemy : enemies) {
                if (minRange == null) {
                    minRange = enemy.GetPosition().Range(GetPosition());
                    closestEnemy = enemy;
                } else {
                    Double tmpRange = GetPosition().Range(enemy.GetPosition());
                    if (tmpRange < minRange) {
                        minRange = tmpRange;
                        closestEnemy = enemy;
                    }
                }
            }
            return closestEnemy;
        }

    @Override
    public void TickUp() {
        if(ticksCount == 10){
            arrows.AddToResourceCurrent(playerLevel);
            ticksCount = 0;
        }
        else
            ticksCount ++;
    }
}