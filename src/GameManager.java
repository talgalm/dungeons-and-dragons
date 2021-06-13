import java.awt.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Scanner;

public class GameManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MenuMessage();
        String chosenPlayer = scanner.nextLine();
        while (Integer.parseInt(chosenPlayer)<1 || Integer.parseInt(chosenPlayer)>6 ) {
            System.out.println("You entered wrong number , please choose number between 1 -6");
            chosenPlayer = scanner.nextLine();
        }

        //Player myPlayer = (Player)(new TileFactory()).Create(chosenPlayer);


        boolean winner = false;
        int level_number = 1;
        while (!winner)
        {
            Object [] level = ReadLevel(level_number);
            if (level.equals("noMoreLevels"))
                winner = true;
            else
            {
                level_number++;
                Board b = BuildLevel(level,chosenPlayer);
                GameLevel gameLevel = new GameLevel(b);
                gameLevel.init();
            }
        }
        System.out.println("YOU WIN !!!!!!!!!!!!!!!!!!!!");


    }
    public static Board BuildLevel(Object[] level, String CharMyPlayer)
    {
        Board board;
        board = new Board((int) level[1], (int) level[2]);
        board.buildTileList((String) level[0],CharMyPlayer);
        return board;
    }
    public static Object[] ReadLevel(int levelNumber)
    {
        int height = 0;
        int width = 0;
        String wantedLevel = "src/levels_dir/level"+levelNumber+".txt";
        String level = "";
        try {
            File myObj = new File(wantedLevel);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                width = data.length();
                height++;
                level += data; //***********doesn't it make the same value as wantedLevel***************
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No more levels");
            e.printStackTrace(); //*****isn't it a problem to throw an error message?******
            level = "noMoreLevels";
        }
        Object[] returnedValue = new Object[3];
        returnedValue[0] = level;
        returnedValue[1] = height;
        returnedValue[2] = width;
        return returnedValue;
    }
    public int findWidth(String l)
    {
        return 0;
    }
    public static void MenuMessage()
    {
        System.out.println( " -------------------------------------------------------------------------------------- " + "\n" +
            "|                                 Please choose a player:                              |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|                                          Warriors:                                   |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|      Name     |  Health  |  Attack  |  Defense  |  Cooldown |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|1.  Jon Snow   |    300   |    30    |     4     |     3     |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|2.  The Hound  |    400   |    20    |     6     |     5     |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|                                           Mages:                                     |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|      Name     |Health|Attack|Defense|Mana Pool|Mana Cost|Spell Power|Hit Count |Range|" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|3. Melisandre  |  100 |   5  |   1   |   300   |    30   |     15    |     5    |  6  |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|4.Thoros of Myr|  400 |  20  |   6   |   150   |    20   |     20    |     3    |  4  |" +"\n" +
            " ---------------------------------------------------------------------------------------" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|                                           Rogues:                                    |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|      Name     |  Health  |  Attack  |  Defense  |    cost   |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|5. Arya Stark  |    150   |    40    |     2     |     20    |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|6.    Bronn    |    250   |    35    |     3     |     50    |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "" +"\n" +
            "" +"\n" +
            "" +"\n" +
            "                  For Choose a player please enter character's number:                  " +"\n" +
            "                 ------------------------------------------------------                 ");
    }


}
