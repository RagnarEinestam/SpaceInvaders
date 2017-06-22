import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SpaceInvaders main menu panel
 *
 * @author Edin Ademi, Hector Dusic
 */
public class MainMenu extends JPanel implements ActionListener {

    //Panels and Layout and classes
    private CardLayout cl;
    private SpaceInvaders spaceInvaders;
    private JPanel menu;
    private MainMenu pnl;
    private PauseScreen pause;
    private Settings settings;

    //Buttons
    private JLabel lbl = new JLabel("Created by SpaceInvaders Group");
    private JButton resume = new JButton("CONTINUE");
    private JButton newGame = new JButton("NEW GAME");
    private JButton settingsBtn = new JButton("SETTINGS");
    private JButton exit = new JButton("EXIT");
    private JButton credits = new JButton("CREDITS");
    private JButton mainMenu = new JButton("BACK TO MENU");
    private String menuHeaderGif = System.getProperty("user.dir").concat("/assets/pictures/mainMenu.gif");
    private JLabel background = new JLabel(new ImageIcon(menuHeaderGif));
    private Font stil1;
    private Font stil2;
    private Sounds soundAssets;

    /**
     * Constructor
     */
    public MainMenu() {

        pnl = this;
        cl = new CardLayout();
        menu = new JPanel();
        settings = new Settings(pnl);
        soundAssets = new Sounds();
        setLayout(new BorderLayout());
        settings.disableSoundOnButton();

        pnl.setLayout(cl);
        pnl.add(menu, "menu");

        pnl.add(settings, "settings");

        //Font
        stil1 = new Font("Bauhaus 93", Font.CENTER_BASELINE, 18);
        stil2 = new Font("Bauhaus 93", Font.CENTER_BASELINE, 15);

        //ObjectFont
        resume.setFont(stil1);
        newGame.setFont(stil1);
        exit.setFont(stil1);
        settingsBtn.setFont(stil1);
        lbl.setFont(stil2);

        //Size
        background.setPreferredSize(new Dimension(800, 300));
        newGame.setPreferredSize(new Dimension(150, 100));
        settingsBtn.setPreferredSize(new Dimension(150, 100));
        credits.setPreferredSize(new Dimension(150, 100));
        exit.setPreferredSize(new Dimension(150, 100));
        mainMenu.setPreferredSize(new Dimension(800, 200));
        resume.setPreferredSize(new Dimension(150, 100));
        newGame.setPreferredSize(new Dimension(150, 100));
        lbl.setPreferredSize(new Dimension(300, 250));

        //Color
        newGame.setForeground(Color.WHITE);
        credits.setForeground(Color.WHITE);
        settingsBtn.setForeground(Color.WHITE);
        mainMenu.setForeground(Color.WHITE);
        exit.setForeground(Color.WHITE);
        lbl.setForeground(Color.RED);


        //Visibility
        resume.setOpaque(false);
        resume.setContentAreaFilled(false);

        newGame.setOpaque(false);
        newGame.setContentAreaFilled(false);

        settingsBtn.setOpaque(false);
        settingsBtn.setContentAreaFilled(false);

        exit.setOpaque(false);
        exit.setContentAreaFilled(false);

        credits.setOpaque(false);
        credits.setContentAreaFilled(false);

        mainMenu.setOpaque(false);
        mainMenu.setContentAreaFilled(false);

        // Background color
        menu.setBackground(Color.BLACK);

        //ActionListeners
        resume.addActionListener(this);
        newGame.addActionListener(this);
        settingsBtn.addActionListener(this);
        exit.addActionListener(this);

        //Add
        menu.add(background);
        menu.add(resume);
        menu.add(newGame);
        menu.add(settingsBtn);
        menu.add(exit);
        menu.add(lbl);

        //Starting screen
        cl.show(pnl, "menu");

        resume.setEnabled(false);
    }

    /**
     * Focus game panel
     */
    public void showGame() {
        cl.show(pnl, "game");
        spaceInvaders.focusPanel();
    }

    /**
     * Focus pause panel
     */
    public void showPause() {
        cl.show(pnl, "pause");
        pause.PausePanel();
    }

    /**
     * Focus main menu panel
     */
    public void showMenu() {
        cl.show(pnl, "menu");
    }


    public void gameMusic() {
        soundAssets.gameMusic();
    }

    public void stopGameMusic() {
        soundAssets.stopGameMusic();
    }

    /**
     * ActionListeners for menu buttons
     */
    public void actionPerformed(ActionEvent e) {

        //Resume the game
        if (e.getSource() == resume) {
            //TODO: Implement resume function
        }

        //NewGame
        if (e.getSource() == newGame) {
            spaceInvaders = new SpaceInvaders(pnl);
            pnl.add(spaceInvaders, "game");
            pause = new PauseScreen(this, spaceInvaders);
            pnl.add(pause, "pause");
            gameMusic();
            cl.show(pnl, "game");
            spaceInvaders.focusPanel();
            spaceInvaders.setRunningState(true);
        }

        //Settings
        if (e.getSource() == settingsBtn) {
            cl.show(pnl, "settings");
            settings.settingPanel();
        }

        //Exit
        if (e.getSource() == exit) {
            System.exit(0);
        }
    }
}