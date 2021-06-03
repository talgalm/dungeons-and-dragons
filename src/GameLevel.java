import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameLevel {
    private Board board;
    private Player player;
    private ArrayList<Enemy> Enemies = new ArrayList<Enemy>();
    private int Tick;
    public GameLevel (Board b , Player player)
    {
        this.Tick = 0;
        this.board = b;
        this.player = player;
        this.Enemies = b.getEnemies();
    }
    public void init()
    {
        while (!Enemies.isEmpty()) {
            board.PrintGameBoard();
            InputProvider inputProvider = new InputProvider() {
                @Override
                public Position getAction(Position current_Pos, char input) {
                    return null;
                }
            };
            player.turn(MessageCallback messageCallback, PlayerDeathCallback deathCallback, inputProvider);
            for (Enemy e : Enemies) {
                e.turn();
            }
            Tick++;
        }
    }

}
