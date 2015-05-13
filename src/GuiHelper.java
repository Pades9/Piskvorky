/**
 * Created by patrikdendis on 29.11.14.
 */

import javax.swing.*;
import java.awt.*;

public class GuiHelper extends JFrame {

    private Container aPanel;
    private JFrame aFrame;

    // Create GUI and
    public GuiHelper(String paName) {
        this.createGUI(paName);
    }

    public void createGUI(String paName) {
        //Create and set up the window.
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        aFrame = new JFrame(paName);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        aPanel = aFrame.getContentPane();
        //Display the window.
        aFrame.setResizable(false);
    }

    // show GUI window
    public void showGUI() {
        aFrame.pack();
        aFrame.setVisible(true);
    }

    public Container getaPanel() {
        return aPanel;
    }

    // add jPanel to window
    public void addJPanel(JPanel paControlPanel) {
        aPanel.add(paControlPanel, BorderLayout.NORTH);
    }

    public JFrame getaFrame() {
        return aFrame;
    }
}
