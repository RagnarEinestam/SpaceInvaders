import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Pause panel
 *
 * @author Hector Dusic, Edin Ademi
 */
public class PauseScreen extends JPanel implements ActionListener {

    private SpaceInvaders space;
    private MainMenu menu;
    private JLabel text = new JLabel("PAUSE", SwingConstants.CENTER);
    private JButton back = new JButton("RESUME");
    private JButton btm = new JButton("MENU");
    private Font stil, stil2;

    public PauseScreen(MainMenu menu, SpaceInvaders space) {
        this.menu = menu;
        this.space = space;
        this.setBackground(Color.BLACK);
        PausePanel();
        stil = new Font("Bauhaus 93", Font.CENTER_BASELINE, 18);
        stil2 = new Font("Bauhaus 93", Font.CENTER_BASELINE, 30);

        //Size
        text.setPreferredSize(new Dimension(700, 200));
        back.setPreferredSize(new Dimension(400, 100));
        btm.setPreferredSize(new Dimension(400, 100));

        //Labels

        add(text);
        add(back);
        add(btm);

        //TextColor
        text.setForeground(Color.WHITE);
        back.setForeground(Color.WHITE);
        btm.setForeground(Color.WHITE);

        //Fonts
        text.setFont(stil2);
        back.setFont(stil);
        btm.setFont(stil);

        //Visibility

        back.setOpaque(false);
        back.setContentAreaFilled(false);

        btm.setOpaque(false);
        btm.setContentAreaFilled(false);

        //Add
        add(back);
        add(btm);

        //ActionListeners
        back.addActionListener(this);
        btm.addActionListener(this);
    }

    /**
     * Focuses pause panel
     */
    public void PausePanel() {
        setFocusable(true);
        requestFocusInWindow();
    }

    /**
     * ActionListener for buttons
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == back) {
            space.setRunningState(true);
            menu.showGame();
        }
        if (e.getSource() == btm) {
            space.setRunningState(false);
            menu.showMenu();
        }
    }
}