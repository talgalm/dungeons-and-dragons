import java.util.ArrayList;
import java.util.List;

public class Trap extends Enemy{
    private int visibilityTime;
    private int invisibilityTime;
    private int tickCount;
    private boolean visible;

    public Trap(char tile, String name, int healthCapacity, int attack, int defence, int experience_value,int visibility_time, int invisibility_time) {
        super(tile, name, healthCapacity, attack, defence, experience_value);
        this.tickCount=0;
        this.invisibilityTime = invisibility_time;
        this.visibilityTime = visibility_time;
        this.visible = true;
    }

    public Position Move(Position pos, List<Enemy> ignoreAbleList)
    {
        visible = tickCount < visibilityTime;
        if (tickCount == visibilityTime + invisibilityTime)
            tickCount=0;
        else
            tickCount++;
        if (GetPosition().Range(pos)<2)
            return new Position(-1,-1);
        return GetPosition();
    }

    @Override
    public void TickUp() { }

    @Override
    public Position MoveAsBribed(ArrayList<Enemy> enemies) { return null; }

    @Override
    public void AcceptBribe() { }
}
