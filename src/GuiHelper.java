/**
 * Created by patrikdendis on 29.11.14.
 */

import java.awt.*;
import javax.swing.*;

public class GuiHelper extends JFrame{

    private Container aPanel;
    private JFrame aFrame;

    public GuiHelper(String paName) {
        this.createGUI(paName);
    }

    public void createGUI(String paName) {
        //Create and set up the window.
        aFrame = new JFrame(paName);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        aPanel = aFrame.getContentPane();
        //Display the window.
        aFrame.setResizable(false);
    }

    public void showGUI() {
        aFrame.pack();
        aFrame.setVisible(true);
    }

    public void addJPanel(JPanel paControlPanel){
        aPanel.add(paControlPanel, BorderLayout.NORTH);
    }

    public void stopGUI() {
        aFrame.getContentPane().removeAll();
    }

}
