import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;

//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;


/**
 * SpaceInvader game panel
 *
 * @author Arbie A, Ragnar E,
 */

public class SpaceInvaders extends JPanel implements KeyListener {

    private Ship ship;
    private MainMenu menu;
    private Sounds soundAssets;
    private AlienArmy alienArmy;
    private ArrayList<ShipMissile> missile;
    private boolean runningState;
    private Thread thread;

    //Bunkers
    private Bunker bunkerWest;
    private Bunker bunkerCentral;
    private Bunker bunkerEast;

    //Highscore
    private String highScore = "";

    /**
     * Constructor
     */
    public SpaceInvaders(MainMenu menu) {

        this.menu = menu;
        ship = new Ship(400, 520);
        alienArmy = new AlienArmy(4, this);

        missile = new ArrayList<>();
        soundAssets = new Sounds();
        thread = new Thread(new RunGame());
        runningState = true;

        setBackground(Color.black);
        addKeyListener(this);
        thread.start();
        focusPanel();

        //Bunkers
        bunkerWest = new Bunker(100, 400);
        bunkerCentral = new Bunker(350, 400);
        bunkerEast = new Bunker(600, 400);

        if (highScore.equals("")) {
            highScore = this.getHighScore();
        }
    }

    /**
     * Creates the graphic components
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Lives and score
        g.setColor(Color.WHITE);
        g.setFont(new Font("ITALIC", Font.BOLD, 17));
        g.drawString("SCORE: " + ship.getScore(), 20, 25);
        g.setColor(Color.RED);
        g.drawString("LIVES: ", 650, 25);
        g.setColor(Color.YELLOW);
        g.drawString("HIGHSCORE:  " + highScore, 20, 40);
        showLives(g);

        //Bunker
        bunkerWest.repaintBunker(g);
        bunkerCentral.repaintBunker(g);
        bunkerEast.repaintBunker(g);

        //Aliens
        alienArmy.drawAlien(g);

        //Ship
        ship.drawShip(g);

        //ShipMissile
        g.setColor(Color.GREEN);
        for (Missile m : missile) {
            if (m.running()) {
                g.fillOval(m.getXPos(), m.getYpos(), m.getWidth(), m.getHeight());
                if (bunkerWest.blockState(m.getXPos(), m.getYpos())) {
                    ship.bunkerHit();
                    m.stopRunning();
                }
                if (bunkerCentral.blockState(m.getXPos(), m.getYpos())) {
                    ship.bunkerHit();
                    m.stopRunning();
                }
                if (bunkerEast.blockState(m.getXPos(), m.getYpos())) {
                    ship.bunkerHit();
                    m.stopRunning();
                }
                if (alienArmy.checkIfHit(m.getXPos(), m.getYpos())) {
                    ship.AlienHit();
                    m.stopRunning();
                }
            }
        }

        //AlienMissile
        g.setColor(Color.RED);
        for (AlienMissile am : AlienMissile.missile) {
            if (am.running()) {
                g.fillOval(am.getXPos(), am.getYpos(), am.getWidth(), am.getHeight());
                if (bunkerWest.blockState(am.getXPos(), am.getYpos())) {
                    am.stopRunning();
                }
                if (bunkerCentral.blockState(am.getXPos(), am.getYpos())) {
                    am.stopRunning();
                }
                if (bunkerEast.blockState(am.getXPos(), am.getYpos())) {
                    am.stopRunning();
                }
            }
        }

        //check if ship is hit
        for (AlienMissile am : AlienMissile.missile) {
            if (am.running()) {
                if (am.getXPos() >= ship.getXPos() && am.getXPos() <= ship.getXPos() + 30) {
                    if (am.getYpos() >= ship.getYPos() && am.getYpos() <= ship.getYPos() + 5) {
                        am.stopRunning();
                        ship.isHit();
                        if (ship.getLives() < 0) {
                            checkScore();
                            setRunningState(false);
                            soundAssets.alienVictory();
                            soundAssets.stopGameMusic();
                            alienArmy.setRunningState(false);
                        }
                        soundAssets.playerExplosion();
                    }
                }
            }
        }

        //check if Bunker is hit by AlienShip
        for (int col = 0; col < alienArmy.getCol(); col++) {
            for (int row = 0; row < alienArmy.getRow(col); row++) {
                if (alienArmy.getAlien(col, row).getState()) {
                    int alienX = alienArmy.getAlien(col, row).getXPos() + 35;
                    int alienY = alienArmy.getAlien(col, row).getYPos() + 25;
                    bunkerWest.blockState(alienX, alienY);
                    bunkerCentral.blockState(alienX, alienY);
                    bunkerEast.blockState(alienX, alienY);
                }
            }
        }
    }

    /**
     * Sets focus on panel
     */
    public void focusPanel() {
        setFocusable(true);
        requestFocusInWindow();
    }

    /**
     * Start of thread
     */

    private class RunGame extends Thread {
        public void run() {
            boolean b = true;
            while (b) {
                try {
                    if (alienArmy.isEmpty()) {
                        b = false;
                        soundAssets.stopGameMusic();
                        setRunningState(false);
                        menu.showPause();
                        //checkScore();
                    }
                    Thread.sleep(10);
                } catch (Exception e) {
                }
                if (runningState) {
                    repaint();
                }

            }
        }
    }

    /**
     * Sets the games running state
     *
     * @param b - boolean running state
     */
    public void setRunningState(boolean b) {
        runningState = b;
    }

    /**
     * Return games running state
     *
     * @return boolean - running state
     */
    public boolean getRunningState() {
        return runningState;
    }

    /**
     * displays number of lives left
     *
     * @param g Graphics component
     */
    public void showLives(Graphics g) {
        int x = 710;
        int y = 0;
        for (int i = 0; i < ship.getLives(); i++) {
            g.drawImage(ship.getShipImg().getImage(), x, y, null);
            x = 750;
        }
    }

    /**
     * Creates a file that is used to store the data
     * A method that checks if the score from the ship class is greater than the current highscore,
     * and overwrites that one, it also splits the line in the document into 2 pieces, so that you
     * retrieve the integer to compare to the score
     */

    public void checkScore() {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (highScore.equals("")) {
                    return;
                }
                if (ship.getScore() > Integer.parseInt((highScore.split(":")[1]))) {
                    String name = JOptionPane.showInputDialog("What's your name? You've set a new RECORD!");
                    highScore = name + ":" + ship.getScore();
                }
                File scoreFile = new File("highscore.dat");
                if (!scoreFile.exists()) {
                    try {
                        scoreFile.createNewFile();
                    } catch (IOException e) {
                    }
                }

                FileWriter fileWriter = null;
                BufferedWriter buffWriter = null;
                try {
                    fileWriter = new FileWriter(scoreFile);
                    buffWriter = new BufferedWriter(fileWriter);
                    buffWriter.write(highScore);
                } catch (Exception ex) {
                } finally {
                    try {
                        if (buffWriter != null) {
                            buffWriter.close();
                        }
                    } catch (Exception ex) {

                    }
                }
            }
        });
    }


    /**
     * Reads from the highscore.dat file and retrieves the data that is used to
     *
     * @return
     */
    public String getHighScore() {

        FileReader readFile = null;
        BufferedReader reader = null;
        try {
            readFile = new FileReader("highscore.dat");
            reader = new BufferedReader(readFile);
            return reader.readLine();
        } catch (Exception ex) {
            return "SpaceInvaders:0";
        } finally {
            try {
                reader.close();
            } catch (Exception ex) {

            }
        }
    }


    /**
     * KeyListener for movement, shoot and pause
     */
    public void keyPressed(KeyEvent e) {
        int shipXPos = ship.getXPos();
        int shipYPos = ship.getYPos();

        //Move right
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (shipXPos < 770)
                ship.setX(shipXPos += 10);
        }

        //Move left
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (ship.getXPos() > 0)
                ship.setX(shipXPos -= 10);
        }

        //Shoot
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            int lastIndex = missile.size() - 1;
            if (missile.isEmpty()) {
                missile.add(new ShipMissile(shipXPos + 14, shipYPos));
                soundAssets.playerShoot();
            } else if (!missile.get(lastIndex).running() || missile.get(lastIndex).getYpos() < 200) {
                missile.add(new ShipMissile(shipXPos + 14, shipYPos));
                soundAssets.playerShoot();
            }
        }

        //Pause
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            menu.showPause();
            setRunningState(false);
        }
    }

    //Not in use
    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}