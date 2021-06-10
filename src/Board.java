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

        public Tile GetTile(Position p){
        return tiles.get((p.getX() * width) + p.getY());
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
        char [][] arr = new char[width][height];
        int counter = 0;
        for(int i = 0; i < width;i++)
        {
            for (int j = 0;j < height;j++)
            {
                Tile t = tiles.get(counter);
                arr[t.GetPosition().getX()][t.GetPosition().getY()] = t.toChar();
                counter++;
            }
        }
        System.out.println(Arrays.deepToString(arr));

        //String result = tiles.stream().sorted().map(t -> t.toSring() + t.getPosition().getX()==width ? "/n" : "").collect(Collectors.joining(""));
    }

}
