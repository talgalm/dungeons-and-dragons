import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameLevel {
    private Board board;
    private Player player;
    private ArrayList<Enemy> Enemies = new ArrayList<Enemy>(); //****can be erased****
    private int Tick;
    private InputProvider inputProvider;
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
            Scanner scanner = new Scanner(System.in);
            char c = scanner.nextLine().charAt(0);
            Position playerMove = inputProvider.getAction(player.GetPosition(), c);
            if (playerMove.getX() == -1)
            {
                player.Cast_Special_Ability(Enemies, Tick);
            }
            else {player.accept( board.GetTile(playerMove));}
            for (Enemy e : Enemies) {
                Position enemyMove = e.Move(player.GetPosition());
                if (enemyMove.getX() == -1)
                {
                    e.Combat(player);
                }
                e.accept(board.GetTile(enemyMove));
            }
            Tick++;
        }
    }

}
