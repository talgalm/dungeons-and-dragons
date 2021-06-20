package BusinessLayer;

import java.util.ArrayList;

public class Warrior extends Player{
    private Resource cooldown;

    public Warrior(char tile, String name, int healthCapacity, int attack, int defence,int cooldown) {
        super(tile, name, healthCapacity, attack, defence);
        this.cooldown = new Resource(cooldown, 0);
    }

    public void LevelUp(){
        cooldown.SetResourceCurrent(0);
        health.AddToResourceMax(playerLevel*5);
        attackPoints += playerLevel*2;
        defensePoints += playerLevel;
        super.LevelUp();
    }

    @Override
    public Enemy CastSpecialAbility(ArrayList<Enemy> Enemies) { //Avenger's Shield
        if(cooldown.GetResourceCurrent() == 0){
            try {
                CooldownToMax();
                health.AddToResourceCurrent(GetDefensePoints() * 10);
                Enemy unLuckyEnemy = Enemies.stream().filter(t -> t.GetPosition().Range(position) < 3).findAny().get();
                int damage = health.GetResourceMax() / 10;
                castAssist(this, unLuckyEnemy, damage, "Avenger's Shield");
                if (!unLuckyEnemy.IsAlive())
                    onAbilityKill(unLuckyEnemy);
            }
            catch(Exception e){}
        }
        return null;
    }


    @Override
    public String getAbility() {
        return "  Avenger’s Shield: cooldown "+cooldown.GetResourceCurrent()+"/"+cooldown.GetResourceMax();
    }

    @Override
    public String getDescription() {
        return GetName() + "  Health: " +getHealth().GetResourceCurrent() + "/" +getHealth().GetResourceMax()
                + "  Attack: " +GetAttackPoints()
                + "  Defense: " +GetDefensePoints()
                + "  Level: " +playerLevel
                + "  Experience: " + getExperience() + getAbility();
    }

    private void CooldownToMax(){ cooldown.SetResourceCurrent(cooldown.GetResourceMax()); }

    @Override
    public void TickUp() {
        cooldown.TakeFromResourceCurrent(1);
    }
}
