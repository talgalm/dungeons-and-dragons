package Frontend;
import BusinessLayer.*;
import Callbacks.MessageCallBack;

import java.util.ArrayList;
import java.util.Scanner;

public class GameLevel {
    private Board board;
    private Player player;
    private ArrayList<Enemy> Enemies;
    private ArrayList<Enemy> bribedEnemies;
    protected MessageCallBack messageCallBack;
    public GameLevel (Board b)
    {
        this.board = b;
        this.player = b.getThePlayer();
        this.Enemies = b.getEnemies();
        this.bribedEnemies = new ArrayList<>();
        messageCallBack = System.out::println;
    }
    public void init()
    {
        char[] inputs = new char[] {'q', 'w', 'e', 'a', 's', 'd'};
        while (!Enemies.isEmpty()) { // or if dead
            board.OrderTiles();
            board.PrintGameBoard();
            messageCallBack.Send(player.getDescription());
            char c = getInput(inputs);
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
                    enemy.accept(player);
                }
                else{
                    Tile t = board.GetTile(enemyMove);
                    t.VisitedBy(enemy);

                }
            }
            //Extra Code for the bribed enemies
            for(Enemy bribedEnemy : bribedEnemies){
                Position enemyMove = bribedEnemy.MoveAsBribed(Enemies, player);
                Tile t = board.GetTile(enemyMove);
                t.VisitedBy(bribedEnemy);
            }
            //end of extra code
            board.TickAll();
        }

    }

    private char getInput(char[] inputs) {
        Scanner scanner = new Scanner(System.in);
        Character input = null;
        while (input == null) {
            try {
                String received = scanner.nextLine();
                if(received.length() == 1)
                    input = received.charAt(0);
                else
                    input = 'x';
                boolean legit = false;
                for (char c : inputs)
                    if (input == c)
                        legit = true;
                if (!legit) {
                    System.out.println("wrong input, please choose a valid key");
                    input = null;
                }
            } catch (StringIndexOutOfBoundsException e) { input = null;}
        }
        return input;
    }
}
