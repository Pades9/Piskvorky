/**
 * Created by patrikdendis on 29.11.14.
 */
public class Player {

    private String aName;
    private String aSymbol;
    private int aSymbolNum;
    private int aNumTurns;

    // Create and initialize player
    public Player(String paName,String paSymbol) {
        aName = paName;
        aSymbol = paSymbol;
        aNumTurns = 0;
        if (paSymbol.equals("X")) {
            aSymbolNum = 1;
        } else {
            aSymbolNum = 2;
        }
    }

    // Create and initialize player with different parameters
    public Player(String paName, int paSymbolNum) {
        aName = paName;
        aSymbolNum = paSymbolNum;
        aNumTurns = 0;
        if (paSymbolNum == 1) {
            aSymbol = "X";
        } else {
            aSymbol = "O";
        }
    }

    public String getPlayerSymbol() {
        return this.aSymbol;
    }

    public String getPlayerName() {
        return this.aName;
    }

    public void increaseNumTurns() {
        this.aNumTurns++;
    }

    public int getaNumTurns() { return this.aNumTurns; }

    public int getaSymbolNum() { return this.aSymbolNum; }

    @Override
    public String toString() {
        return "Player{" +
                "aName='" + aName + '\'' +
                ", aSymbol='" + aSymbol + '\'' +
                ", aSymbolNum=" + aSymbolNum +
                ", aNumTurns=" + aNumTurns +
                '}';
    }
}
