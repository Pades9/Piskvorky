/**
 * Created by patrikdendis on 29.11.14.
 */
public class Player {

    private String aName;
    private String aSymbol;
    private int aSymbolNum;
    private int aNumTurns;

    public Player(String paName,String paSymbol) {
        aName = paName;
        aSymbol = paSymbol;
        aNumTurns = 0;
        if(paSymbol.equals("X")) {
            aSymbolNum = 1;
        } else {
            aSymbolNum = 2;
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
}
