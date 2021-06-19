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
                return m;
            }
        }
        return null;
    }

    @Override
    public String getAbility() {
        return "Bribe";
    }

    @Override
    public void earnCash(int healthPoints) {
        cash.AddToResourceCurrent((healthPoints * cashPercentage)/100);
    }

    @Override
    public void TickUp() { }
}
