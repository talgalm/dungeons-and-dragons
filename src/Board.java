import java.util.ArrayList;
import java.util.stream.Collectors;

public class Board {
    private ArrayList<Tile> tiles = new ArrayList<>();
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
        return tiles.stream().filter(tile -> p.equals(tile.GetPosition())).findFirst().orElse(null);
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
                tiles.add(new Empty(tilePos,'.'));
            }
            else if(tile == '#') {
                tiles.add(new Empty(tilePos,'#'));
            }
            else if (tile == '@') {
                ThePlayer.SetPosition(tilePos);
                tiles.add(ThePlayer);
            }
            else{
                Enemy enemy = tileFactory.CreateEnemy(tilePos, tile);
                if(enemy == null){
                    tiles.add(new Empty(tilePos,'.'));
                }
                else{
                    tiles.add(enemy);
                    Enemies.add(enemy);
                }
            }
            indexCurrent_X++;
        }
    }

    public void PrintGameBoard()
    {
        String result = tiles.stream().sorted().map(t -> t.ToChar() + t.GetPosition().getX()==XTop ? "/n" : "").collect(Collectors.joining(""));
        System.out.println(result);
    }

    public void TickAll(){
        tiles.stream().forEach(t -> t.TickUp());
    }

}
