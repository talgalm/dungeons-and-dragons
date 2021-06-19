package Frontend;
import BusinessLayer.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameLevel {
    private Board board;
    private Player player;
    private ArrayList<Enemy> Enemies;
    private ArrayList<Enemy> bribedEnemies;
    public GameLevel (Board b)
    {
        this.board = b;
        this.player = b.getThePlayer();
        this.Enemies = b.getEnemies();
        this.bribedEnemies = new ArrayList<>();
    }
    public void init()
    {
        while (!Enemies.isEmpty()) { // or if dead
            board.PrintGameBoard();
            Scanner scanner = new Scanner(System.in);
            char c = scanner.nextLine().charAt(0);
            Position playerWishedPosition = null;
            while(playerWishedPosition == null){
                playerWishedPosition = player.MoveTo(c);
            }
            if (playerWishedPosition.getX() == -1) {
                Enemy bribedEnemy = player.CastSpecialAbility(Enemies);
                if(bribedEnemy!=null) {
                    Enemies.remove(bribedEnemy);
                    bribedEnemies.add(bribedEnemy);
                }
            }
            else {
                Tile t = board.GetTile(playerWishedPosition);
                t.VisitedBy(player);
            }
            for (Enemy enemy : Enemies) {
                Position enemyMove = enemy.Move(player.GetPosition(), bribedEnemies); //try to find player, if no, look for bribed
                if (enemyMove.getX() == -1) { //if a trap and is close to player
                    enemy.Combat(player);
                }
                else{
                    Tile t = board.GetTile(enemyMove);
                    t.VisitedBy(enemy);
                }
            }
            //Extra Code for the bribed enemies
            for(Enemy bribedEnemy : bribedEnemies){
                Position enemyMove = bribedEnemy.MoveAsBribed(Enemies);
                Tile t = board.GetTile(enemyMove);
                t.VisitedBy(bribedEnemy);
            }
            //end of extra code
            board.TickAll();
        }
    }
}
