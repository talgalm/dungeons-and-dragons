import java.util.ArrayList;

public class Fan_Of_Knives extends Ability {
    private int cost;
    private int current_energy;
    public Fan_Of_Knives(int cost)
    {
        this.cost = cost;
        current_energy = 100;
    }
    @Override
    public int cast(ArrayList<Enemy> Enemies, Position PlayerPos, int Damage, int currentDefense, int CurrentTick) {
        current_energy = current_energy-cost;
        for (Enemy e:
             Enemies) {
            if (e.GetPosition().Range(PlayerPos) < 2)
            {
                e.takeDamage(Damage); //(each enemy will try to defend itself).
            }
        }
        return 0;
    }

    @Override
    public int levelUp(int level) {
        current_energy = 100;
        return 3*level;
    }

    @Override
    public void gameTick(int level) {
        current_energy = Math.min(current_energy+10,100);
    }
}
