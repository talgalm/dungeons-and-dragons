import java.util.ArrayList;

public class Blizzard extends Ability {
    private int current_mana;
    private int hits_count;
    private int ability_range;
    private int spell_power;
    private int mana_cost;
    private int mana_pool;

    public Blizzard(int mana_pool,int mana_cost,int spell_power,int hits_count,int ability_range)
    {
        this.mana_pool = mana_pool;
        this.current_mana = mana_pool/4;
        this.mana_cost = mana_cost;
        this.spell_power = spell_power;
        this.hits_count = hits_count;
        this.ability_range = ability_range;
    }
    @Override
    public int cast(ArrayList<Enemy> Enemies, Position PlayerPos, int Damage, int currentDefense, int CurrentTick) {
        if (current_mana >= mana_cost)
        {
            current_mana = current_mana-mana_cost;
            int hits = 0;
            while (hits < hits_count) {
                ArrayList<Enemy> EnemiesInRange = new ArrayList<>();
                for (Enemy e : Enemies) {
                    if (e.GetPosition().Range(PlayerPos) < ability_range)
                        EnemiesInRange.add(e);
                }
                while (EnemiesInRange.size()!= 0)
                {
                    int randomize_Enemy = (int)(Math.random()*EnemiesInRange.size());
                    EnemiesInRange.get(randomize_Enemy).getHealth().takeDamage(spell_power); //(each enemy may try to defend itself).
                }
                hits++;
            }
        }
        return 0;
    }

    @Override
    public int levelUp(int level) {
        mana_pool = mana_pool + 25*level;
        current_mana = Math.min(current_mana+mana_pool/4,mana_pool);
        spell_power = spell_power+10*level;
        return 0;
    }

    @Override
    public void gameTick(int level) {
        current_mana = Math.min(mana_pool, current_mana+level);
    }
}
