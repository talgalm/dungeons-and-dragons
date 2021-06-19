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
            CooldownToMax();
            health.AddToResourceCurrent(GetDefensePoints()*10);
            Enemy unLuckyEnemy = Enemies.stream().filter(t -> t.GetPosition().Range(this.position)<3).findAny().get();
            int damage = health.GetResourceMax() / 10;
            unLuckyEnemy.TakeDamage(damage);
            //(Enemies.stream().filter(t -> t.GetPosition().Range(this.position)<3).findAny().get()).TakeDamage(health.GetResourceMax() / 10);
        }
        return null;
    }

    private void CooldownToMax(){ cooldown.SetResourceCurrent(cooldown.GetResourceMax()); }

    @Override
    public void TickUp() {
        cooldown.TakeFromResourceCurrent(1);
    }
}
