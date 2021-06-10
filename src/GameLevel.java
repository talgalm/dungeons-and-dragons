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
            player.turn(); //MessageCallback messageCallback, PlayerDeathCallback deathCallback, inputProvider

            for (Enemy e : Enemies) {
                e.turn();
            }
            Tick++;
        }
    }


}
