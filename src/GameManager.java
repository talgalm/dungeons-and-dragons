import java.awt.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Scanner;

public class GameManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MenuMessage();
        String chosenPlayer = scanner.nextLine(); //*******Dina: we need to add a limit for values other than 1-6********
        Player myPlayer = (Player)(new TileFactory()).Create(chosenPlayer);


        boolean winner = false;
        int level_number = 1;
        while (!winner)
        {
            String level =  ReadLevel(level_number);
            if (level.equals("noMoreLevels"))
                winner = true;
            else
            {
                level_number++;
                Board b = BuildLevel(level,myPlayer);
                GameLevel gameLevel = new GameLevel(b,myPlayer);
                gameLevel.init();
            }
        }
        System.out.println("YOU WIN !!!!!!!!!!!!!!!!!!!!");


    }
    public static Board BuildLevel(String level, Player myPlayer)
    {
        int width = level.charAt(level.length()-1); //*****might be 2+ digits*****
        int height = level.charAt(level.length()-2); //***same**** you might want to use "split"
        level = level.substring(0,level.length()-2); //***same**
        Board board;
        board = new Board(height, width);
        board.buildTileList(level,myPlayer);
        return board;
    }
    public static String ReadLevel(int levelNumber)
    {
        int height = 0;
        int width = 0;
        String wantedLevel = "src/levels_dir/level"+levelNumber+".txt";
        String level = "";
        try {
            File myObj = new File(wantedLevel); //************how did you know it? h'omer or internet?****************
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                width = data.length();
                height++;
                level += data; //***********doesn't it make the same value as wantedLevel***************
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred."); //no more levels
            e.printStackTrace(); //*****isn't it a problem to throw an error message?******
            level = "noMoreLevels";
        }
        level = level+height+width;
        return level;
    }
    public static void MenuMessage()
    {
        System.out.println( " -------------------------------------------------------------------------------------- " +
            "|                                 Please choose a player:                              |" +
            " -------------------------------------------------------------------------------------- " +
            "|                                          Warriors:                                   |" +
            " -------------------------------------------------------------------------------------- " +
            "|      Name     |  Health  |  Attack  |  Defense  |  Cooldown |" +
            " -------------------------------------------------------------------------------------- " +
            "|1.  Jon Snow   |    300   |    30    |     4     |     3     |" +
            " -------------------------------------------------------------------------------------- " +
            "|2.  The Hound  |    400   |    20    |     6     |     5     |" +
            " -------------------------------------------------------------------------------------- " +
            " -------------------------------------------------------------------------------------- " +
            "|                                           Mages:                                     |" +
            " -------------------------------------------------------------------------------------- " +
            "|      Name     |Health|Attack|Defense|Mana Pool|Mana Cost|Spell Power|Hit Count |Range|" +
            " -------------------------------------------------------------------------------------- " +
            "|3. Melisandre  |  100 |   5  |   1   |   300   |    30   |     15    |     5    |  6  |" +
            " -------------------------------------------------------------------------------------- " +
            "|4.Thoros of Myr|  400 |  20  |   6   |   150   |    20   |     20    |     3    |  4  |" +
            " ---------------------------------------------------------------------------------------" +
            " -------------------------------------------------------------------------------------- " +
            "|                                           Rogues:                                    |" +
            " -------------------------------------------------------------------------------------- " +
            "|      Name     |  Health  |  Attack  |  Defense  |    cost   |" +
            " -------------------------------------------------------------------------------------- " +
            "|2. Arya Stark  |    150   |    40    |     2     |     20    |" +
            " -------------------------------------------------------------------------------------- " +
            "|6.    Bronn    |    250   |    35    |     3     |     50    |" +
            " -------------------------------------------------------------------------------------- " +
            "" +
            "" +
            "" +
            "                  For Choose a player please enter character's number:                  " +
            "                 ------------------------------------------------------                 ");
    }


}
