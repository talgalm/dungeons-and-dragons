package BusinessLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Briber extends Player{
    private Resource cash;
    private int cashPercentage;

    public Briber(char tile, String name, int healthCapacity, int attack, int defence,int cashPercentage) {
        super(tile, name, healthCapacity, attack, defence);
        this.cashPercentage = cashPercentage;
        this.cash = new Resource(Integer.MAX_VALUE, 0);
    }

    public void LevelUp(){
        health.AddToResourceMax(playerLevel*3);
        attackPoints += playerLevel*2;
        defensePoints += playerLevel;
        super.LevelUp();
    }

    @Override
    public Enemy CastSpecialAbility(ArrayList<Enemy> Enemies) { //Bribe
        List<Enemy> closeEnemies = Enemies.stream().filter(t -> t.GetPosition().Range(GetPosition()) < 2).collect(Collectors.toList());
        if (closeEnemies.size() == 1) {
            Enemy m = closeEnemies.get(0);
            char c = m.GetChar();
            if (c == 'q' | c == 's' | c == 'k') {
                m.AcceptBribe();
                messageCallBack.Send(String.format("\n%s bribed the %s for an amount of %d cash. He will now fight other enemies.", GetName(), m.GetName(), m.health.GetResourceMax()));
                return m;
            }
        }
        messageCallBack.Send(String.format("\n%s tried to bribe but no enemy to bribe is around.", GetName()));
        return null;
    }

    @Override
    public String getAbility() {
        return "  Ability: Bribe   cash: " + cash.GetResourceCurrent() + "  bribe cost: Enemy's health amount" ;
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
    public void earnCash(int healthPoints) {
        int amount = (healthPoints * cashPercentage)/100;
        messageCallBack.Send(String.format("\n%s received %d cash.", GetName(), amount));
        cash.AddToResourceCurrent(amount);
    }

    @Override
    public void TickUp() { }
}
