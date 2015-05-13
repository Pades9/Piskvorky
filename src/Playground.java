import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by patrikdendis on 27.4.15.
 */
public class Playground extends GuiHelper implements ActionListener {

    private int aSize;
    private int aNumCorrectTurns;
    private int[][] aGameArray;
    private Player aPlayer1;
    private Player aPlayer2;
    private Player aActivePlayer;
    private JLabel activePlayerLabel;
    private Date startDate;

    // Constructor
    public Playground(String paName, int paSize, Player paPlayer1, Player paPlayer2, int paNumCorrectTurns) {
        super(paName);
        aSize = paSize;
        aGameArray = new int [paSize][paSize];
        aPlayer1 = paPlayer1;
        aPlayer2 = paPlayer2;
        aActivePlayer = aPlayer1;
        aNumCorrectTurns = paNumCorrectTurns;
        startDate = new Date();
    }

    // Constructor
    public Playground(String paName, int paSize, Player paPlayer1, Player paPlayer2, Player paActivePlayer, int paNumCorrectTurns, int[][] paGameArray) {
        super(paName);
        aSize = paSize;
        aGameArray = paGameArray;
        aPlayer1 = paPlayer1;
        aPlayer2 = paPlayer2;
        aActivePlayer = paActivePlayer;
        aNumCorrectTurns = paNumCorrectTurns;
        startDate = new Date();
    }

    // Create GUI, add jPanel and show GUI
    public void createPlayground() {

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout());
        container.setPreferredSize(new Dimension(500,540));

            final JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new GridLayout(0,this.getaSize()));
            controlPanel.setPreferredSize(new Dimension(500,500));
            Border buttonFieldBorder = new LineBorder(new Color(212, 212, 212), 1);
            controlPanel.setBorder(new EmptyBorder(0,0,0,0));

            for (int i = 0;i<aSize;i++) {

                for (int j = 0;j<aSize;j++) {
                    JButton buttonField = new JButton("");
                    buttonField.addActionListener(this);
                    buttonField.putClientProperty("id", new ArrayRowCol(i, j));
                    buttonField.setBorder(buttonFieldBorder);

                    if(aGameArray[i][j] == aPlayer1.getaSymbolNum()) {
                        buttonField.setText(aPlayer1.getPlayerSymbol());
                    }

                    if(aGameArray[i][j] == aPlayer2.getaSymbolNum()) {
                        buttonField.setText(aPlayer2.getPlayerSymbol());
                    }

                    controlPanel.add(buttonField);
                }

            }

            JFrame frame = super.getaFrame();
            JMenuBar menuBar = new JMenuBar();
            JMenu saveMenu = new JMenu("Uložiť hru");
            saveMenu.addMenuListener(new MenuListener() {

                public void menuSelected(MenuEvent e) {
                    saveGameAction();
                }

                @Override
                public void menuDeselected(MenuEvent e) {
                }

                @Override
                public void menuCanceled(MenuEvent e) {
                }

            });
            menuBar.add(saveMenu);
            frame.setJMenuBar(menuBar);

            container.add(controlPanel);

            final JPanel controlPanel2 = new JPanel();
            controlPanel2.setLayout(new FlowLayout());

            activePlayerLabel = new JLabel(String.format("Na ťahu je %s - %s",aActivePlayer.getPlayerName(),aActivePlayer.getPlayerSymbol()), JLabel.LEFT);
            controlPanel2.add(activePlayerLabel);

            container.add(controlPanel2);

        super.addJPanel(container);

        super.showGUI();

    }

    // Action after click on button
    public void actionPerformed(ActionEvent e) {
        JButton selectedButton = (JButton)e.getSource();
        if (selectedButton.getText().equals("")) {

            aActivePlayer.increaseNumTurns();
            ArrayRowCol arrayRowCol = (ArrayRowCol)selectedButton.getClientProperty("id");
            this.addTurnToPlayer(aActivePlayer, arrayRowCol.getaRow(), arrayRowCol.getaCol());
            selectedButton.setText(aActivePlayer.getPlayerSymbol());

            if (this.checkResult(aActivePlayer,arrayRowCol.getaRow(), arrayRowCol.getaCol())) {

                int dialogResult = JOptionPane.showConfirmDialog (null, aActivePlayer.getPlayerName() + " vyhral!\nChcete začať novú hru?","Upozornenie",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION) {
                    Window w = SwingUtilities.getWindowAncestor(this.getaPanel());
                    w.setVisible(false);
                    Piskvorky piskvorky = new Piskvorky();
                } else {
                    System.exit(0);
                }

            }

            if (aActivePlayer == aPlayer1) {
                aActivePlayer = aPlayer2;
            } else {
                aActivePlayer = aPlayer1;
            }

            activePlayerLabel.setText(String.format("Na ťahu je %s - %s",aActivePlayer.getPlayerName(),aActivePlayer.getPlayerSymbol()));

        } else {
            showAlert("Tento ťah nieje povolený.");
        }

    }

    // add turn to player
    private void addTurnToPlayer(Player paPlayer,int paRow,int paCol) {
        aGameArray[paRow][paCol] = paPlayer.getaSymbolNum();
    }

    // check results
    private boolean checkResult(Player paPlayer,int paRow,int paCol) {

        // Check for symbols in ROW
        int count = 0;
        for (int j = 0;j<aSize;j++) {
            if (aGameArray[paRow][j] == paPlayer.getaSymbolNum()) {
                count++;
            } else {
                count = 0;
            }

            if (count == aNumCorrectTurns) {
                return true;
            }
        }

        // Check for symbols in column
        count = 0;
        for (int i = 0;i<aSize;i++) {
            if (aGameArray[i][paCol] == paPlayer.getaSymbolNum()) {
                count++;
            } else {
                count = 0;
            }

            if (count == aNumCorrectTurns) {
                return true;
            }
        }

        count = 0;
        int leftCrossRow = paRow-paCol;
        int leftCrossCol = 0;
        if (leftCrossRow < 0) {
            leftCrossRow = 0;
            leftCrossCol = paCol-paRow;
        }

        // Check for symbols in cross from left
        while (leftCrossRow < aSize && leftCrossCol < aSize) {
            if (aGameArray[leftCrossRow][leftCrossCol] == paPlayer.getaSymbolNum()) {
                count++;
            } else {
                count = 0;
            }

            if(count == aNumCorrectTurns) {
                return true;
            }

            leftCrossRow++;
            leftCrossCol++;
        }

        count = 0;
        int rightCrossRow = aSize-paRow-paCol-1;
        int rightCrossCol = aSize-1;
        if (rightCrossRow > 0) {
            rightCrossRow = 0;
            rightCrossCol = aSize-1-(aSize-paCol-paRow-1);
        } else {
            rightCrossRow = Math.abs(rightCrossRow);
        }

        // Check for symbols in cross from right
        while (rightCrossRow < aSize && rightCrossCol >= 0) {
            if (aGameArray[rightCrossRow][rightCrossCol] == paPlayer.getaSymbolNum()) {
                count++;
            } else {
                count = 0;
            }

            if (count == aNumCorrectTurns) {
                return true;
            }

            rightCrossRow++;
            rightCrossCol--;
        }

        return false;
    }

    // Getter for size
    public int getaSize() {
        return this.aSize;
    }

    // Method for saving game to txt file
    public void saveGameAction() {

        String dateFormat = "yyyy-MM-dd_HH-mm-ss";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        String fileName = sdf.format(startDate)+".txt";

        FileHelper fileHelper = new FileHelper(fileName);

        if(fileHelper.saveGame(aPlayer1,aPlayer2,aActivePlayer,aGameArray,aSize,aNumCorrectTurns)) {
            showAlert("Hra bola uspesne ulozena. Môžete začať novú hru.");
            Window w = SwingUtilities.getWindowAncestor(this.getaPanel());
            w.setVisible(false);
            Piskvorky piskvorky = new Piskvorky();
        } else {
            showAlert("Hru sa nepodarilo ulozit.");
        }
    }

    // Helper for show alert
    private void showAlert(final String message) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, message);
            }
        });
    }

    @Override
    public String toString() {
        return "Playground{" +
                "aSize=" + aSize +
                ", aNumCorrectTurns=" + aNumCorrectTurns +
                ", aGameArray=" + Arrays.toString(aGameArray) +
                ", aPlayer1=" + aPlayer1 +
                ", aPlayer2=" + aPlayer2 +
                ", aActivePlayer=" + aActivePlayer +
                ", startDate=" + startDate +
                '}';
    }
}
