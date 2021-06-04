import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameLevel {
    private Board board;
    private Player player;
    private ArrayList<Enemy> Enemies = new ArrayList<Enemy>(); //****can be erased****
    private int Tick;
    public GameLevel (Board b)
    {
        this.Tick = 0;
        this.board = b;
        this.player = b.getThePlayer();
        this.Enemies = b.getEnemies();
    }
    public void init()
    {
        while (!Enemies.isEmpty()) {
            board.PrintGameBoard();
//            Position current_Pos = player.getPosition();
            InputProvider inputProvider = new InputProvider() {
                @Override
                public Position getAction(Position current_Pos, char input) { //***shouldn't the first input be player.GetPosition()?*** (we added the line there)
                    return null;
                }
            };
            player.turn(MessageCallback messageCallback, PlayerDeathCallback deathCallback, inputProvider);
            //***Why should the player get callBacks in each turn instead of constant fields in construction?****
            //***plus, maybe, we're not sure, there's a problem with how you send the desired new location
            //***shouldn't it be a copy constructor of the position? because it'll change inside GetAction.
            for (Enemy e : Enemies) {
                e.turn();
            }
            Tick++;
        }
    }
    //****you should put the ticks somewhere so units can know the amount(maybe in "turn" signature)***
    //****more functions of death of enemies*****

}
