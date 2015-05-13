import java.io.*;
import java.util.Scanner;

/**
 * Created by patrikdendis on 27.4.15.
 */
public class FileHelper {

    String fileName;

    // Constructor
    public FileHelper(String fileName) {
        this.fileName = fileName;
    }

    // Method for saving game to txt file
    public boolean saveGame(Player player1, Player player2, Player activePlayer, int[][] aGameArray, int size, int numCorrectTurns) {

        try {
            File file = new File("/Users/patrikdendis/Documents/java/Piskvorky/src/saves/"+fileName);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            PrintStream subor = new PrintStream(bos);
            subor.println(player1.getPlayerName()+", "+player1.getaSymbolNum());
            subor.println(player2.getPlayerName()+", "+player2.getaSymbolNum());
            subor.println(activePlayer.getPlayerName());
            subor.println(size);
            subor.println(numCorrectTurns);

            for (int i = 0; i < aGameArray.length; i++) {
                for (int j = 0; j < aGameArray.length; j++) {
                    subor.print(String.format("%3s",aGameArray[i][j]));
                }
                subor.print("\n");
            }

            subor.close();

            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Method for loading game from txt file
    public Playground loadGame() {

        try {
            File file = new File("/Users/patrikdendis/Documents/java/Piskvorky/src/saves/"+fileName);
            FileInputStream fis = new FileInputStream(file);


            BufferedInputStream bis = new BufferedInputStream(fis);
            Scanner subor = new Scanner(bis);

            String player1String = subor.nextLine();
            String[] player1Array = player1String.split(", ");
            Player player1 = new Player(player1Array[0],Integer.parseInt(player1Array[1]));

            String player2String = subor.nextLine();
            String[] player2Array = player2String.split(", ");
            Player player2 = new Player(player2Array[0],Integer.parseInt(player2Array[1]));

            Player activePLayer;
            String activePlayerString = subor.nextLine();
            if(activePlayerString.equals(player1.getPlayerName())) {
                activePLayer = player1;
            } else {
                activePLayer = player2;
            }

            int size = subor.nextInt();
            int numCorrectTurns = subor.nextInt();
            int[][] gameArray = new int [size][size];

            for (int i = 0;i<size;i++) {
                for (int j = 0;j<size;j++) {
                    gameArray[i][j] = subor.nextInt();
                }
            }

            subor.close();
            file.delete();
            return new Playground("Piskvorky",size,player1,player2,activePLayer,numCorrectTurns,gameArray);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
