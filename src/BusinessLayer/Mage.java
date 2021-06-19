package BusinessLayer;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Mage extends Player{
    private Resource mana;
    private int manaCost;
    private int spellPower;
    private int hitCount;
    private int abilityRange;

    public Mage(char tile, String name, int healthCapacity, int attack, int defence,int manaMax, int manaCost, int spellPower, int hitCount, int abilityRange) {
        super(tile, name, healthCapacity, attack, defence);
        this.mana = new Resource(manaMax, manaMax/4);
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitCount = hitCount;
        this.abilityRange = abilityRange;
    }

    public void LevelUp(){
        mana.AddToResourceMax(playerLevel*25);
        mana.AddToResourceCurrent(mana.GetResourceMax() / 4);
        spellPower = spellPower + playerLevel*10;
        super.LevelUp();
    }

    @Override
    public Enemy CastSpecialAbility(ArrayList<Enemy> Enemies) { //Blizzard
        if(mana.GetResourceCurrent() >= manaCost){
            TakeManaCost();
            int hits = 0;
            Stream<Enemy> enemiesInRange = Enemies.stream().filter(t -> (t.GetPosition().Range(GetPosition()) < abilityRange));
            while( (hits < hitCount) &  enemiesInRange.count() != 0 ) {
                Enemy poorEnemy = enemiesInRange.findAny().get();
                if(poorEnemy != null){ //he can be null, if he dies from a previous hit, and I don't want to check "enemiesInRange" in each loop
                    poorEnemy.health.TakeFromResourceCurrent(spellPower - poorEnemy.GetRandomDefensePoints());
                }
                hits ++;
            }
        }
        return null;
    }

    private void TakeManaCost(){
        mana.TakeFromResourceCurrent(manaCost);
    }

    @Override
    public void TickUp() {
        mana.AddToResourceCurrent(playerLevel);
    }
}