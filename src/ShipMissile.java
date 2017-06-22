/**
 * Ship Missile
 *
 * @author Ragnar E, Arbie A
 */
public class ShipMissile extends Missile {

    /**
     * Constructor
     *
     * @param xPos - xPos value
     * @param yPos - yPos value
     */
    public ShipMissile(int xPos, int yPos) {
        super(xPos, yPos, 8);
        start();
    }

    public void run() {
        while (getYpos() > -10) {
            for (int i = getYpos(); i >= -20; i -= getSpeed()) {
                try {
                    Thread.sleep(10);
                    setYPos(i);
                } catch (Exception e) {
                }
            }
        }
        stopRunning();
    }
}