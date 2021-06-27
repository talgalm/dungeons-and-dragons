package Frontend;
import BusinessLayer.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class GameManager {

    public static void main(String[] args) {
        MenuMessage();
        String chosenNumber = chooseNumber();
        TileFactory tileFactory = new TileFactory();
        Player ThePlayer = tileFactory.CreatePlayer(chosenNumber);
        boolean winner = false;
        int level_number = 1;
        while (!winner)
        {
            Object [] level = ReadLevel(level_number);
            if (level[0].equals("noMoreLevels"))
                winner = true;
            else
            {
                level_number++;
                Board b = BuildLevel(level,ThePlayer);
                GameLevel gameLevel = new GameLevel(b);
                gameLevel.init();
                System.out.println("\n All Enemies are dead. Level up! \n");
            }
        }
        if(winner) {
            winScreen(chosenNumber);
        }
    }



    public static Board BuildLevel(Object[] level, Player ThePlayer)
    {
        Board board;
        board = new Board((int) level[1], (int) level[2]);
        board.buildTileList((String) level[0],ThePlayer);
        return board;
    }

    private static String chooseNumber(){
        Scanner scanner = new Scanner(System.in);
        String chosenPlayer;
        String result = null;
        while(result == null){
            try
            {
                chosenPlayer = scanner.nextLine();
                int chosen = Integer.parseInt(chosenPlayer);
                if(chosen < 0 || chosen > 7)
                    System.out.println("You entered wrong input, please choose a number between 1 to 7");
                else
                    result = chosenPlayer;
            }
            catch (NumberFormatException nfe){ }
        }
        return result;
    }

    public static Object[] ReadLevel(int levelNumber)
    {
        int YTop = 0;
        int XTop = 0;
        String wantedLevel = "src/levels_dir/level"+levelNumber+".txt";
        System.out.println(String.format("Level %d ", levelNumber));
        String level = "";
        try {
            File myObj = new File(wantedLevel);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                while(data.charAt(data.length()-1) == ' ') //there might be a problem with spaces
                    data = data.substring(0, data.length()-1);
                XTop = data.length();
                YTop++;
                level += data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No more levels");
            //e.printStackTrace(); //*****isn't it a problem to throw an error message?******
            level = "noMoreLevels";
        }
        Object[] returnedValue = new Object[3];
        returnedValue[0] = level;
        returnedValue[1] = YTop;
        returnedValue[2] = XTop;
        return returnedValue;
    }

    public static void MenuMessage()
    {
        System.out.println( " -------------------------------------------------------------------------------------- " + "\n" +
            "|                                 Please choose a character:                           |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|                                  New Character!! 'Briber'                            |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|         Name        |  Health  |  Attack  |  Defense  |     cash per kill      |      " +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|0.  Tyrion Lannister |    200   |    15    |     4     |  35% of enemy's health |      " +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|         Tyrion might be weak, but every time he kills an enemy he gets cash.         |" +"\n" +
            " A percentage of enemy's health as cash. With this cash, he can BRIBE a Lannister guard|" +"\n" +
            "|       or knight or Queen's Guard (marked as 's' and 'k' and 'q' respectively).       |" +"\n" +
            "|Bribe will cost as much as the health bar of the bribed and will last the whole level |" +"\n" +
            "|Bribe action will succeed only if Briber has enough cash and only 1 bribeable soldier |" +"\n" +
            "|                      around his close 'circle' (square around)                       |" +"\n" +
            "|When an enemy get bribed:                                                             |" +"\n" +
            "|•His range get buffed by 3 points                                                     |" +"\n" +
            "|•He will search for another enemy to fight with, and the enemy will fight him back    |" +"\n" +
            "|•if he can't find an enemy in range he will follow after the player                   |" +"\n" +
            "|                     (No need to kill bribed enemy, your choice)                      |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
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
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|                                           Hunters:                                    |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|      Name     |  Health  |  Attack  |  Defense  |    Range   |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "|7.   Ygritte   |   200    |    30    |     2     |      6     |" +"\n" +
            " -------------------------------------------------------------------------------------- " +"\n" +
            "" +"\n" +
            "" +"\n" +
            "" +"\n" +
            "                  To Choose a character please enter the character's number:                  " +"\n" +
            "                 ------------------------------------------------------------           ");
    }

    public static void winScreen(String number){
        String title = "King";
        if(number.equals("3") | number.equals("5") | number.equals("7"))
            title = "Queen";
        System.out.println(String.format("You won the game!! You are now The %s of the North", title));
        System.out.println(String.format("%s of the North!", title));
        System.out.println(String.format("%s of the North!", title));
        System.out.println(String.format("%s of the North!", title));
    }


}
