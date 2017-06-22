import javax.swing.*;

/**
 * Creates the alien template
 *
 * @author Arbie A.
 */
public class Alien {

    //Position
    private int xPos;
    private int yPos;

    //URLs
    private String alien = System.getProperty("user.dir").concat("/assets/icons/alien.png");
    private String alien2 = System.getProperty("user.dir").concat("/assets/icons/alien2.jpg");
    private String ufo = System.getProperty("user.dir").concat("/assets/icons/ufo.jpg");
    private String ufo2 = System.getProperty("user.dir").concat("/assets/icons/ufo2.jpg");

    //Boolean
    private boolean alienState;

    //image
    private ImageIcon[][] alienIcon =
            {{new ImageIcon(alien), new ImageIcon(alien2)},
                    {new ImageIcon(alien2), new ImageIcon(alien)},
                    {new ImageIcon(ufo), new ImageIcon(ufo2)},
                    {new ImageIcon(alien), new ImageIcon(alien2)},
                    {new ImageIcon(alien2), new ImageIcon(alien)}};


    /**
     * Constructor
     *
     * @param xPos - xPos of alien
     * @param yPos - yPos of alien
     */
    public Alien(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.alienState = true;
    }

    /**
     * returns xPos for alien
     *
     * @return - xPos value
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * returns yPos for alien
     *
     * @return - yPos value
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * Set alien position
     *
     * @param xPos - xPos value
     * @param yPos - yPos value
     */
    public void setPos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * returns true if alien is alive
     *
     * @return - state of alien
     */
    public boolean getState() {
        return alienState;
    }

    /**
     * sets alien state to false when alien is hit
     */
    public void destroyAlien() {
        alienState = false;
    }

    /**
     * returns a image of this Alien
     *
     * @return- image to return
     */
    public ImageIcon getImage(int col, int row) {
        return alienIcon[col][row];
    }

    /**
     * fires a missile
     */
    public synchronized void shot() {
        new AlienMissile(xPos + 12, yPos + 12);
    }
}