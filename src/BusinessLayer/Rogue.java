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
                poorEnemy.TakeDamage(GetAttackPoints() - poorEnemy.GetRandomDefensePoints());
            }
        }
        return null;
    }

    @Override
    public void TickUp() {
        energy.AddToResourceCurrent(10);
    }
}