import java.util.ArrayList;

public abstract class Ability {
    public abstract int cast(ArrayList<Enemy> Enemies, Position PlayerPos, int Damage, int currentDefense, int CurrentTick);
    public abstract int levelUp(int level);
    public abstract void gameTick(int level);
}
