import java.util.ArrayList;

public class Avengers_Shield extends Ability{
    private int ability_cooldown;
    private int remaining_cooldown;
    public Avengers_Shield(int ability_cooldown)
    {
        this.ability_cooldown = ability_cooldown;
        remaining_cooldown = 0;
    }

    @Override
    public int cast(ArrayList<Enemy> Enemies , Position PlayerPos , int Damage , int currentDefense , int CurrentTick) {
        if (remaining_cooldown >=0 )
        {
            ArrayList<Enemy> EnemiesInRange = new ArrayList<>();
            for (Enemy e: Enemies) {
                if (e.GetPosition().Range(PlayerPos) < 3)
                    EnemiesInRange.add(e);
            }
            int randomize_Enemy = (int)(Math.random()*EnemiesInRange.size());
            EnemiesInRange.get(randomize_Enemy).getHealth().takeDamage(Damage);
            remaining_cooldown = ability_cooldown;
            return currentDefense*10;
        }
        else
            return 0;
    }

    @Override
    public int levelUp(int level) {
        remaining_cooldown = 0;
        return 0;
    }

    @Override
    public void gameTick(int level) {
        if (remaining_cooldown > 0)
            remaining_cooldown--;
    }


}
