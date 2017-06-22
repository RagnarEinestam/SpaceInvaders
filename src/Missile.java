/**
 * Generic missile class
 *
 * @author Arbie A
 * @version 1.0
 */
public class Missile extends Thread {

    private int width;
    private int hight;
    private int xPos;
    private int yPos;
    private int speed;
    private boolean running;

    /**
     * Constructor
     *
     * @param xPos - starting xPos
     * @param yPos - starting yPos
     */
    public Missile(int xPos, int yPos, int inSpeed) {
        this.width = 4;
        this.hight = 15;
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = inSpeed;
        this.running = true;
    }

    /**
     * Width of missile
     *
     * @return - width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Height of missile
     *
     * @return - height
     */
    public int getHeight() {
        return hight;
    }

    /**
     * xPos of missile
     *
     * @return - xPos
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * yPos of missile
     *
     * @return - yPos
     */
    public int getYpos() {
        return yPos;
    }

    /**
     * set yPos of missile
     *
     * @param yPos
     */
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * returns speed of the missile
     *
     * @return - speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * returns running state of missile
     *
     * @return - true if missile is running, else false
     */
    public boolean running() {
        return running;
    }

    /**
     * Sets state of missile to false
     */
    public void stopRunning() {
        if (!running)
            return;
        running = false;
    }
}