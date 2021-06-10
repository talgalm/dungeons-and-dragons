public class Trap extends Enemy{
    private int visibility_time;
    private int invisibility_time;
    private int tick_count;
    private boolean visible;
    public Trap(Position position, char tile, String name, int healthCapacity, int attack, int defence, int experience_value,int visibility_time, int invisibility_time) {
        super(position, tile, name, healthCapacity, attack, defence, experience_value);
        tick_count=0;
        this.invisibility_time=invisibility_time;
        this.visibility_time = visibility_time;
    }
    public Position Move(Position pos)
    {
        visible = tick_count < visibility_time;
        if (tick_count == (visibility_time + invisibility_time))
        {
            tick_count=0;
        }
        else tick_count++;
        if (GetPosition().Range(pos)<2)
            return new Position(-1,-1);
        return  GetPosition();
    }
}
