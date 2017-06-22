import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creating a settings class to the main menu so we are able to adjust the sound in the game.
 * or simply go back to the MainMenu class.
 *
 * @author Hector, Edin
 */

public class Settings extends JPanel implements ActionListener {

    private MainMenu menu;
    private JLabel textLbl = new JLabel("SETTINGS", SwingConstants.CENTER);
    private JButton soundOnBtn = new JButton("MUSIC ON");
    private JButton soundOffBtn = new JButton("MUSIC OFF");
    private JButton backBtn = new JButton("BACK TO MENU");
    private Font font, font2;

    public Settings(MainMenu menu) {

        this.menu = menu;
        this.setBackground(Color.BLACK);
        settingPanel();
        font = new Font("Bauhaus 93", Font.CENTER_BASELINE, 18);
        font2 = new Font("Bauhaus 93", Font.CENTER_BASELINE, 30);

        //Dimension
        textLbl.setPreferredSize(new Dimension(700, 200));
        soundOnBtn.setPreferredSize(new Dimension(150, 100));
        soundOffBtn.setPreferredSize(new Dimension(150, 100));
        backBtn.setPreferredSize(new Dimension(500, 100));

        //Add
        add(textLbl);
        add(soundOffBtn);
        add(soundOnBtn);
        add(backBtn);

        // Color on the text
        textLbl.setForeground(Color.WHITE);
        soundOnBtn.setForeground(Color.WHITE);
        soundOffBtn.setForeground(Color.WHITE);
        backBtn.setForeground(Color.WHITE);

        //Fonts
        textLbl.setFont(font2);
        soundOnBtn.setFont(font);
        soundOffBtn.setFont(font);
        backBtn.setFont(font);


        //Visibility
        soundOnBtn.setOpaque(false);
        soundOnBtn.setContentAreaFilled(false);

        soundOffBtn.setOpaque(false);
        soundOffBtn.setContentAreaFilled(false);

        backBtn.setOpaque(false);
        backBtn.setContentAreaFilled(false);

        // Add the JButtons
        add(soundOnBtn);
        add(soundOffBtn);
        add(backBtn);

        // ActionListeners to the JButtons
        soundOnBtn.addActionListener(this);
        soundOffBtn.addActionListener(this);
        backBtn.addActionListener(this);
    }

    /**
     * Focuses settings panel
     */
    public void settingPanel() {
        setFocusable(true);
        requestFocusInWindow();
    }

    /**
     * Disables sound on button
     */
    public void disableSoundOnButton() {
        soundOnBtn.setEnabled(false);
    }

    /**
     * Enables sound on button
     */
    public void enableSoundOnButton() {
        soundOnBtn.setEnabled(true);
    }

    /**
     * Disables sound off button
     */
    public void disableSoundOffButton() {
        soundOffBtn.setEnabled(false);
    }

    /**
     * Enables sound off button
     */
    public void enableSoundOffButton() {
        soundOffBtn.setEnabled(true);
    }

    /**
     * ActionListener for buttons
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == soundOnBtn) {
            menu.gameMusic();
            disableSoundOnButton();
            enableSoundOffButton();
        }

        if (e.getSource() == soundOffBtn) {
            menu.stopGameMusic();
            enableSoundOnButton();
            disableSoundOffButton();
        }

        if (e.getSource() == backBtn) {
            menu.showMenu();
        }
    }
}