package BusinessLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rogue extends Player{
    private Resource energy;
    private int abilityCost;

    public Rogue(char tile, String name, int healthCapacity, int attack, int defence,int abilityCost) {
        super(tile, name, healthCapacity, attack, defence);
        this.abilityCost = abilityCost;
        energy = new Resource(100, 100);
    }

    public void LevelUp(){
        energy.SetResourceCurrent(100);
        attackPoints += playerLevel*3;
        super.LevelUp();
    }

    @Override
    public Enemy CastSpecialAbility(ArrayList<Enemy> Enemies) { //Fan of Knives
        if(energy.GetResourceCurrent() >= abilityCost){
            energy.TakeFromResourceCurrent(abilityCost);
            List<Enemy> poorEnemiesList = Enemies.stream().filter(t -> t.GetPosition().Range(GetPosition()) < 2).collect(Collectors.toList());
            for( Enemy poorEnemy : poorEnemiesList){
                castAssist(this, poorEnemy, GetAttackPoints(), "Fan of Knives");
            }
        }
        return null;
    }

    @Override
    public String getAbility() {
        return "  Ability: Fan of Knives   energy: " + energy.GetResourceCurrent()+"/"+energy.GetResourceMax() +"   ability cost: " +abilityCost ;
    }
    @Override
    public String getDescription() {
        return GetName() + "  Health: " +getHealth().GetResourceCurrent() + "/" +getHealth().GetResourceMax()
                + "  Attack: " +GetAttackPoints()
                + "  Defense: " +GetDefensePoints()
                + "  Level: " +playerLevel
                + "  Experience: " + getExperience() + getAbility();
    }

    @Override
    public void TickUp() {
        energy.AddToResourceCurrent(10);
    }
}