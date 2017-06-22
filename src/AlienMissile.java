import java.util.ArrayList;

/**
 * Ship Missile
 *
 * @author Ragnar E, Arbie A
 */
public class AlienMissile extends Missile {

    public static ArrayList<AlienMissile> missile = new ArrayList<>();

    /**
     * Constructor
     *
     * @param xPos - xPos value
     * @param yPos - yPos value
     */
    public AlienMissile(int xPos, int yPos) {
        super(xPos, yPos, 5);
        missile.add(this);
        start();
    }

    /**
     * runs the missile movement
     */
    public void run() {
        for (int i = getYpos(); i <= 900; i += getSpeed()) {
            try {
                Thread.sleep(25);
                setYPos(i);
            } catch (Exception e) {
            }
        }
    }
}