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
            player.accept( board.GetTile(inputProvider.getAction(player.GetPosition(), c)));
            for (Enemy e : Enemies) {
                e.turn();
            }
            Tick++;
        }
    }


}
