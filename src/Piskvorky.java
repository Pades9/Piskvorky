import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by patrikdendis on 29.11.14.
 */


public class Piskvorky implements ActionListener {

    private Settings setting;
    private Playground playground;

    // Initialize Piskvorky and attributes
    public Piskvorky() {

        playground = null;

        setting = new Settings("Nastavenia");
        setting.createSettingView(this);

    }

    public static void main(String[] args) {
        Piskvorky piskvorky = new Piskvorky();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(setting.getSavesField().equals("-")) {

            if(!setting.getPlayer1Field().isEmpty() && !setting.getPlayer2Field().isEmpty()) {

                Player aPlayer1 = new Player(setting.getPlayer1Field(),"X");
                Player aPlayer2 = new Player(setting.getPlayer2Field(),"O");

                String[] sizeArray = setting.getSizeField().split("x");
                int aSize = Integer.parseInt(sizeArray[0]);

                int aNumCorrectTurns = (Integer)setting.getNumTurnsField();

                Window w = SwingUtilities.getWindowAncestor(setting.getaPanel());
                w.setVisible(false);

                playground = new Playground("Piskvorky",aSize,aPlayer1,aPlayer2,aNumCorrectTurns);
                playground.createPlayground();

            } else {
                JOptionPane.showMessageDialog(null, "Nezadali ste všetky potrebné údaje.");
            }
        } else {

            FileHelper fileHelper = new FileHelper(setting.getSavesField());
            playground = fileHelper.loadGame();
            if(playground != null) {
                Window w = SwingUtilities.getWindowAncestor(setting.getaPanel());
                w.setVisible(false);
                playground.createPlayground();
            }

        }

    }
}