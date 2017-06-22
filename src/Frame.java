import javax.swing.*;
import java.awt.*;

/**
 * Game frame
 *
 * @author Ragnar, Edin
 */
public class Frame extends JFrame {

    /**
     * Constructor
     */
    private Frame() {
        setUndecorated(true);
        setAlwaysOnTop(false);
        setVisible(true);

        add(new MainMenu());
        setFrame();
    }


    private void setFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Frame();
    }
}