import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

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

    public void buildTileList(String stringList, String CharMyPlayer)
    {
        int currentIndexWidth = 0, currentIndexHeight = 0;
        for (char tile : stringList.toCharArray())
        {
            String TileString =String.valueOf(tile);
            if (currentIndexWidth >= width) {
                currentIndexWidth = 0;
                currentIndexHeight++;
            }
            //*****we need to calculate position, as a field inside each unit.
            //*****The player as well needs to have position when constructed
            //***Therefore, we recommend to keep the chosen player as an int until this point of the code
            //*****instead of passing the object Player as input and when constructed now
            //*****it can be with the position. (using tilefactory)
            if (tile == '@') {
                ThePlayer = (Player) (new TileFactory()).Create(CharMyPlayer, new Position(currentIndexWidth,currentIndexHeight));
                tiles.add(ThePlayer);
            }
            else tiles.add((new TileFactory()).Create(TileString,new Position(currentIndexWidth,currentIndexHeight)));
            currentIndexWidth++;
        }
    }
    public void PrintGameBoard()
    {
        char [][] arr = new char[width][height]; //**because of 2 comments below, changed to char arr**
        // **We can also do this like that but then we will need to override toString to return char
        int counter = 0;
        for(int i = 0; i < width;i++)
        {
            for (int j = 0;j < height;j++) //***did you the mean opposite?***
            {
                Tile t = tiles.get(counter);
                arr[t.GetPosition().getX()][t.GetPosition().getY()] = t.toChar(); //**you wrote t.GetChar, we changed because problem with trap visibility**
                //***we will change the return value of trap in toChar considering the ticking.
                counter++;
            }
        }
        System.out.println(Arrays.deepToString(arr));
    }

}
