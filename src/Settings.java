import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

/**
 * Created by patrikdendis on 27.4.15.
 */
public class Settings extends GuiHelper {

    private JTextField player1Field;
    private JTextField player2Field;
    private JComboBox sizeField;
    private JComboBox numTurnsField;
    private JComboBox savesField;

    // Constructor
    public Settings(String paName) {
        super(paName);
    }

    // Create settings window
    public void createSettingView(Piskvorky piskvorky) {

        final JPanel controlPanel = new JPanel();
        controlPanel.setBorder(new EmptyBorder(30, 10, 10, 10));
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setPreferredSize(new Dimension(500, 230));

        JLabel player1Label = new JLabel("Zadajte meno 1. hráča", JLabel.LEFT);
        controlPanel.add(player1Label);

        player1Field = new JTextField("", 20);
        controlPanel.add(player1Field);

        JLabel player2Label = new JLabel("Zadajte meno 2. hráča", JLabel.LEFT);
        controlPanel.add(player2Label);

        player2Field = new JTextField("", 20);
        controlPanel.add(player2Field);

        JLabel sizeLabel = new JLabel("Veľkost hracieho pola", JLabel.LEFT);
        controlPanel.add(sizeLabel);

        sizeField = new JComboBox();
        int i = 5;
        while (i <= 40) {
            sizeField.addItem(i + "x" + i);
            if(i==20) {
                sizeField.setSelectedItem(i + "x" + i);
            }
            i += 5;
        }
        controlPanel.add(sizeField);

        JLabel numTurnsLabel = new JLabel("Počet vítazných ťahov", JLabel.LEFT);
        controlPanel.add(numTurnsLabel);

        numTurnsField = new JComboBox();
        for (int j = 3; j < 6; j++) {
            numTurnsField.addItem(j);
            if(j==5) {
                numTurnsField.setSelectedItem( j );
            }
        }
        controlPanel.add(numTurnsField);

        JLabel savesLabel = new JLabel("                 Uložené hry", JLabel.CENTER);
        controlPanel.add(savesLabel);

        savesField = this.getSavesComboBox();
        controlPanel.add(savesField);

        JLabel emptyLabel = new JLabel("     ", JLabel.LEFT);
        controlPanel.add(emptyLabel);

        JButton submitButton = new JButton("Hrať");
        submitButton.addActionListener(piskvorky);
        controlPanel.add(submitButton);

        super.addJPanel(controlPanel);
        super.showGUI();

    }

    // Generate combobox with saving game files
    public JComboBox getSavesComboBox() {

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("-");
        File folder = new File("/Users/patrikdendis/Documents/java/Piskvorky/src/saves/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                comboBox.addItem(file.getName());
            }
        }
        return comboBox;

    }

    // Getter for Player 1 name
    public String getPlayer1Field() {
        return player1Field.getText();
    }

    // Getter for Player 2 name
    public String getPlayer2Field() {
        return player2Field.getText();
    }

    // Getter for size
    public String getSizeField() {
        return (String)sizeField.getSelectedItem();
    }

    // Getter for number of turns
    public Object getNumTurnsField() {
        return numTurnsField.getSelectedItem();
    }

    // Getter for saves
    public String getSavesField() {
        return (String)savesField.getSelectedItem();
    }
}
