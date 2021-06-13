import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Board {
    private ArrayList<Tile> tiles = new ArrayList<Tile>();
    private ArrayList<Enemy> Enemies = new ArrayList<Enemy>();
    private int height;
    private int width;
    private Player ThePlayer;
    public Board (int h , int w)
    {
        this.height = h;
        this.width = w;
        Position savePlayerPos = new Position(0,0);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public ArrayList<Enemy> getEnemies() {
        return Enemies;
    }

    public Player getThePlayer() {
        return ThePlayer;
    }

    public Tile GetTile(Position p){
        for (Tile t:
             tiles) {
            if (t.GetPosition().getX() == p.getX() && t.GetPosition().getY() == p.getY())
                return t;
        }
        return null;
    }

    public void buildTileList(String stringList, String CharMyPlayer)
    {
        int currentIndexWidth = 0, currentIndexHeight = height-1;
        for (char tile : stringList.toCharArray())
        {
            if (currentIndexWidth == width)
            {
                currentIndexWidth = 0;
                currentIndexHeight--;
            }
            String TileString =String.valueOf(tile);

            if (tile == '@') {
                ThePlayer = (Player) (new TileFactory()).Create(CharMyPlayer, new Position(currentIndexWidth,currentIndexHeight));
                tiles.add(ThePlayer);
            }
            else {
                Tile notPlayer = (new TileFactory()).Create(TileString,new Position(currentIndexWidth,currentIndexHeight));
                tiles.add(notPlayer);
                if (tile != '.' && tile != '#')
                    Enemies.add((Enemy)notPlayer);
            }
            currentIndexWidth++;
        }
    }
    public void PrintGameBoard()
    {
        char [][] arr = new char[5][4];
        for (Tile t:
             tiles) {
            arr[t.GetPosition().getX()][t.GetPosition().getY()] = t.toChar();
        }

        for (int i=height-1;i>=0;i--)
        {
            for(int j=0; j<= width-1;j++)
            {
                System.out.print(arr[j][i]);
            }
            System.out.println();
        }
    }

}
