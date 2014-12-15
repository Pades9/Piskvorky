/**
 * Created by patrikdendis on 29.11.14.
 */


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.BorderLayout;

public class GuiHelper extends JFrame {

    private Container aPanel;
    private JFrame aFrame;

    // Create GUI and
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

    // show GUI window
    public void showGUI() {
        aFrame.pack();
        aFrame.setVisible(true);
    }

    // add jPanel to window
    public void addJPanel(JPanel paControlPanel) {
        aPanel.add(paControlPanel, BorderLayout.NORTH);
    }

    // Stop window
    public void stopGUI() {
        aFrame.getContentPane().removeAll();
    }

}
