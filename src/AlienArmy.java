import java.awt.*;
import java.util.Random;

/**
 * Class that creates a array of Aliens
 *
 * @author Arbie A
 */

public class AlienArmy extends Thread {

    private SpaceInvaders space;
    private Alien[][] alienFormation;
    private int deltaX;
    private int deltaY;
    private int startPositionX;
    private int startPositionY;
    private Sounds soundAssets;
    private Thread t;
    private boolean runningState;
    private int alienRows;
    private int counter;
    private int delay;

    /**
     * Constructor
     */
    public AlienArmy(int row, SpaceInvaders space) {
        this.space = space;
        alienRows = row;
        alienFormation = new Alien[row][13];
        deltaX = 11;
        deltaY = 35;
        startPositionX = 140;
        startPositionY = 35;
        soundAssets = new Sounds();
        delay = 400;
        t = new Thread(new AlienBarrage());
        runningState = true;

        createArmy();
        start();
    }

    /**
     * Draw Aliens in formation
     *
     * @param g - graphic component
     */
    public void drawAlien(Graphics g) {
        int currentIcon = 0;
        g.setColor(Color.WHITE);
        for (int col = 0; col < alienFormation.length; col++) {
            for (int row = 0; row < alienFormation[col].length; row++) {
                Alien alien = alienFormation[col][row];
                currentIcon = alien.getXPos() % 2;
                if (alien.getState()) {
                    int x = alien.getXPos();
                    int y = alien.getYPos();
                    Image image = alien.getImage(col, currentIcon).getImage();
                    g.drawImage(image, x, y, null);
                }
            }
        }
    }


    /**
     * Creates an formation of Aliens
     */
    public void createArmy() {
        for (int col = 0; col < alienFormation.length; col++) {
            for (int row = 0; row < alienFormation[col].length; row++) {
                alienFormation[col][row] = new Alien(startPositionX, startPositionY);
                startPositionX += 40;
            }
            startPositionX = 140;
            startPositionY += 35;
        }
    }

    /**
     * Moves the array of Aliens
     */
    public void moveArmy() {
        if (changeDirection()) {
            deltaX = -deltaX; //changeDirection
            deltaY += 10;
        }
        move();
    }


    /**
     * Check if alien formation should change direction
     *
     * @return - return true if time to change direction
     */
    public boolean changeDirection() {
        boolean turnback = false;
        int xPos;
        for (int col = 0; col < alienFormation.length; col++) {
            for (int row = 0; row < alienFormation[col].length; row++) {
                if (alienFormation[col][row].getState()) {
                    xPos = alienFormation[col][row].getXPos(); //currentXPos
                    if (xPos < 30 || xPos > 740) {
                        turnback = true;
                        soundAssets.alienMovement();
                        return turnback;
                    }
                }
            }
        }
        return turnback;
    }

    /**
     * moves the formation
     */
    public void move() {
        int xPos;
        int yPos;
        soundAssets.alienMovement();
        for (int col = 0; col < alienFormation.length; col++) {
            for (int row = 0; row < alienFormation[col].length; row++) {
                xPos = alienFormation[col][row].getXPos(); //currentXPos
                yPos = alienFormation[col][row].getYPos() + deltaY; //currentYpos
                alienFormation[col][row].setPos(xPos += deltaX, +yPos);
            }
        }
        deltaY = 0;
    }

    /**
     * sets running state of AlienArmy
     *
     * @param b - running state
     */
    public void setRunningState(boolean b) {
        runningState = b;
    }

    /**
     * return the running state of AlienArmy
     *
     * @return boolean - running state
     */
    public boolean getRunningState() {
        return runningState;
    }

    /**
     * Returns the number of columns in this AlienFormation
     *
     * @return - number of columns
     */
    public int getCol() {
        return alienFormation.length;
    }


    /**
     * Returns the number of aliens in the specified row
     *
     * @param col - the row to check
     * @return - number of aliens to return
     */
    public int getRow(int col) {
        return alienFormation[col].length;
    }

    /**
     * returns the Alien in the specified position.
     *
     * @param row - row in the array
     * @param col - col in the array to return
     * @return - Alien to be returned
     */
    public Alien getAlien(int col, int row) {
        return alienFormation[col][row];
    }

    /**
     * check if alienFormation is empty
     *
     * @return - return true if empty
     */
    public boolean isEmpty() {
        if (counter == (alienRows * 13)) {
            setRunningState(false);
            return true;
        }
        return false;
    }


    /**
     * Checks if alien in this formation is hit by a missile with the specified position
     *
     * @param x - x-position for missile
     * @param y - -position for missile
     * @return - return true if hit
     */
    public boolean checkIfHit(int x, int y) {
        int aliensLeft = alienRows * 13;
        for (int col = 0; col < alienFormation.length; col++) {
            for (int row = 0; row < alienFormation[col].length; row++) {
                if (alienFormation[col][row].getState()) {
                    if ((alienFormation[col][row].getXPos() <= x) && (alienFormation[col][row].getXPos() + 35 > x)) {
                        if ((alienFormation[col][row].getYPos() <= y) && (alienFormation[col][row].getYPos() + 25 >= y)) {
                            alienFormation[col][row].destroyAlien();
                            soundAssets.alienExplosion();
                            counter++;
                            speed(aliensLeft - counter);
                            isEmpty();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Inner thread class for AlienArmy fire rate
     *
     * @author Ragnar E
     */
    public class AlienBarrage extends Thread {
        public void run() {
            while (true) {
                if(space.getRunningState()){
                    randomShot();
                }
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    /**
     * Thread for AlienArmy movement
     */
    public void run() {
        t.start();
        while (true) {
            if(space.getRunningState()){
                moveArmy();
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Sets the speed depending on number of Aliens left.
     *
     * @param aliensLeft - number of aliens left
     */
    public void speed(int aliensLeft) {
        if (aliensLeft == 15)
            delay = 200;
        if (aliensLeft == 10)
            delay = 150;
        if (aliensLeft == 5)
            delay = 100;
        if (aliensLeft == 2)
            delay = 75;
        if (aliensLeft == 1)
            delay = 40;
    }


    /**
     * Random alien in array gets to fire a missile
     */
    public synchronized void randomShot() {
        Random r = new Random();
        for (int row = 0; row < alienFormation.length; row++) {
            for (int col = 0; col < alienFormation[row].length; col++) {
                if (getAlien(row, col).getState()) {
                    if (r.nextInt(350) == 1) {
                        alienFormation[row][col].shot();
                    }
                }
            }
        }
    }
}