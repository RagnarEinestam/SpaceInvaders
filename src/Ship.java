import javax.swing.*;
import java.awt.*;


/**
 * Players ship
 *
 * @author Ragnar E, Arbie A
 * @version 4.0
 */
public class Ship {

    private ImageIcon shipImg;


    //Stats
    private int lives;
    private int score;

    //Position
    private int xPos;
    private int yPos;

    /**
     * Constructor
     *
     * @param xPos - value
     * @param yPos - value
     */
    public Ship(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        lives = 2;
        score = 0;
        shipImg = new ImageIcon(System.getProperty("user.dir").concat("/assets/icons/ship.jpg"));
    }

    /**
     * Set xPos of ship
     *
     * @param xPos - value
     */
    public void setX(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Return ships xPos
     *
     * @return xPos value
     */
    public int getXPos() {
        return this.xPos;
    }

    /**
     * Sets yPos of ship
     *
     * @param yPos - value
     */
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Returns yPos of ship
     *
     * @return yPos - value
     */
    public int getYPos() {
        return this.yPos;
    }

    /**
     * returns icon
     *
     * @return - icon to return
     */
    public ImageIcon getShipImg() {
        return shipImg;
    }

    /**
     * returns number of lives left
     *
     * @return - number of lives to return
     */
    public int getLives() {
        return lives;
    }

    /**
     * Removes on the ships 1UPs
     */
    public void isHit() {
        lives = lives - 1;
    }

    /**
     * return the players score
     *
     * @return score to return
     */
    public int getScore() {
        return score;
    }

    /**
     * increase player score with 100
     */
    public void AlienHit() {
        score = (int) (score + 100);
    }

    /**
     * increase player score with 10
     */
    public void bunkerHit() {
        score = score + 50;
    }

    /**
     * Draws the ship on screen
     *
     * @param g - Graphics
     */
    public void drawShip(Graphics g) {
        g.drawImage(shipImg.getImage(), xPos, yPos, null);
    }
}