/**
 * Created by patrikdendis on 29.11.14.
 */

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Piskvorky implements ActionListener {

    private int aSize;
    private int aNumCorrectTurns;
    private int[][] aGameArray;
    private Player aPlayer1;
    private Player aPlayer2;
    private Player aActivePlayer;
    private GuiHelper aGui;

    // Initialize Piskvorky and attributes
    public Piskvorky() {

        aSize = 20;
        aNumCorrectTurns = 5;
        aGui = new GuiHelper("Piškvorky");
        aGameArray = new int [aSize][aSize];
        aPlayer1 = new Player("Patrik","X");
        aPlayer2 = new Player("Jozef", "O");
        aActivePlayer = aPlayer1;

        this.createPlayground();

    }

    // Create GUI, add jPanel and show GUI
    private void createPlayground() {

        final JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(0,aSize));
        controlPanel.setPreferredSize(new Dimension(500,500));
        Border buttonFieldBorder = new LineBorder(new Color(212, 212, 212), 1);

        for (int i = 0;i<aSize;i++) {

            for (int j = 0;j<aSize;j++) {
                JButton buttonField = new JButton("");
                buttonField.addActionListener(this);
                buttonField.putClientProperty("id", new ArrayRowCol(i,j));
                buttonField.setBorder(buttonFieldBorder);
                controlPanel.add(buttonField);
            }

        }

        this.aGui.addJPanel(controlPanel);
        this.aGui.showGUI();

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
                JOptionPane.showMessageDialog(null, aActivePlayer.getPlayerName() + " vyhral!");
                aGui.stopGUI();
            }

            if (aActivePlayer == aPlayer1) {
                aActivePlayer = aPlayer2;
            } else {
                aActivePlayer = aPlayer1;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Tento ťah nieje povolený.");
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
                return  true;
            }

            rightCrossRow++;
            rightCrossCol--;
        }


        return false;
    }

    public static void main(String[] args) {
        Piskvorky piskvorky = new Piskvorky();
    }
}