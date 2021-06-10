import java.util.ArrayList;

public class Player extends Unit{
    private Ability ability;
    private InputProvider inputProvider;
    private MassageCallBack messageCallBack;

    public Player(Position position,char tile, String name, int healthCapacity, int attack, int defence,Ability ability) {
        super(position, tile, name, healthCapacity, attack, defence);
        this.ability = ability;

        }

    @Override
    public void visit(Tile tile) {

    }

    @Override
    public void interaction(Tile tile) {

    }
    public void Cast_Special_Ability(ArrayList<Enemy> Enemies,int current_tick)
    {
        ability.cast(Enemies , GetPosition(),getResourcePool(),getDefensePoints(),current_tick);
    }
}
