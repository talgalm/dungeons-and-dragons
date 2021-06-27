package Frontend;
import BusinessLayer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private List<Tile> tiles = new ArrayList<>();
    private ArrayList<Enemy> Enemies = new ArrayList<>();
    private int YTop;
    private int XTop;
    private Player ThePlayer;
    private TileFactory tileFactory = new TileFactory();
    public Board (int h , int w)
    {
        this.YTop = h;
        this.XTop = w;
    }


    public ArrayList<Enemy> getEnemies() {
        return Enemies;
    }

    public Player getThePlayer() {
        return ThePlayer;
    }

    public Tile GetTile(Position p){
        List<Tile> tilesInPos = tiles.stream().filter(tile -> p.equals(tile.GetPosition())).collect(Collectors.toList());
        if (tilesInPos.size() !=0)
            return tilesInPos.get(0);
        else
        {
            int np = XTop*p.getY() + p.getX();
            return tiles.get(np);
        }
    }

    public void buildTileList(String stringList, Player ThePlayer)
    {
        this.ThePlayer = ThePlayer;
        int indexCurrent_X = 0, indexCurrent_Y = 0;
        for (char tile : stringList.toCharArray())
        {
            if (indexCurrent_X == XTop)
            {
                indexCurrent_X = 0;
                indexCurrent_Y++;
            }
            String TileString = String.valueOf(tile);
            Position tilePos = new Position(indexCurrent_X, indexCurrent_Y);
            if(tile == '.') {
                Empty empty = new Empty('.');
                empty.init(tilePos);
                tiles.add(empty);
            }
            else if(tile == '#') {
                Wall wall = new Wall('#');
                wall.init(tilePos);
                tiles.add(wall);
            }
            else if (tile == '@') {
                ThePlayer.init(tilePos, () -> playerDead(), (s) -> printToScreen(s));
                tiles.add(ThePlayer);
            }
            else{
                Enemy enemy = tileFactory.CreateEnemy(tile);
                enemy.init(tilePos, () -> replaceEnemyWithEmpty(enemy), (s) -> printToScreen(s));
                if(enemy == null){
                    Empty empty = new Empty('.');
                    empty.init(tilePos);
                    tiles.add(empty);
                }
                else{
                    tiles.add(enemy);
                    Enemies.add(enemy);
                }
            }
            indexCurrent_X++;
        }
    }

    private void replaceEnemyWithEmpty(Enemy enemy){
        Enemies.remove(enemy);
        tiles.remove(enemy);
        Empty empty = new Empty('.');
        tiles.add(empty);
        empty.init(enemy.GetPosition());
    }

    private void playerDead(){
        ThePlayer.SetCharacter('X');
        PrintGameBoard();
        System.out.println("YOU LOST");
        System.exit(0);
    }

    private void printToScreen(String s){
        System.out.println(s);
    }

    public void PrintGameBoard()
    {
        String result = tiles.stream().sorted()
                .map(t -> t.GetPosition().getX()==(XTop-1) ? (t.ToChar() + "\n") : String.valueOf(t.ToChar()))
                .collect(Collectors.joining(""));
        System.out.println(result);
    }

    public void TickAll(){
        tiles.stream().forEach(t -> t.TickUp());
    }

    public void OrderTiles() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
    }
}
