import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Sound assets for the game and menu
 *
 * @author Ragnar Einestam
 */
public class Sounds {
    private Clip cGameMusic, cPlayerExplosion, cPlayerShoot, cBunkerExplosion,
            cAlienExplosion, cAlienMovement, cAlienVictory, cAlienShoot;
    private AudioInputStream gameM, playerE, playerS, bunkerE, alienE, alienM, alienV, alienS;

    /**
     * Constructor, initiates the sound URLs.
     */
    public Sounds() {
        //Sound paths
    }

    /**
     * Game background music
     */
    public void gameMusic() {
        try {
            String path = System.getProperty("user.dir").concat("/assets/music/menuBackground.wav");
            File file = new File(path);
            gameM = AudioSystem.getAudioInputStream(file);
            cGameMusic = AudioSystem.getClip();
            cGameMusic.open(gameM);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException uafe) {
            uafe.printStackTrace();
        }

        cGameMusic.start();
        cGameMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stop game background music
     */
    public void stopGameMusic() {
        cGameMusic.stop();
    }

    /**
     * Player explosion effect
     */
    public void playerExplosion() {
        try {
            String path = System.getProperty("user.dir").concat("/assets/sounds/playerExplosion.wav");
            File file = new File(path);
            playerE = AudioSystem.getAudioInputStream(file);
            cPlayerExplosion = AudioSystem.getClip();
            cPlayerExplosion.open(playerE);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException uafe) {
            uafe.printStackTrace();
        }

        cPlayerExplosion.start();
    }

    /**
     * Alien sound effect
     */
    public void playerShoot() {
        try {
            String path = System.getProperty("user.dir").concat("/assets/sounds/playerShoot.wav");
            File file = new File(path);
            playerS = AudioSystem.getAudioInputStream(file);
            cPlayerShoot = AudioSystem.getClip();
            cPlayerShoot.open(playerS);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException uafe) {
            uafe.printStackTrace();
        }

        cPlayerShoot.start();
    }

    /**
     * Bunker explosion effect
     */
    public void bunkerExplosion() {
        try {
            String path = System.getProperty("user.dir").concat("/assets/sounds/bunkerExplosion.wav");
            File file = new File(path);
            bunkerE = AudioSystem.getAudioInputStream(file);
            cBunkerExplosion = AudioSystem.getClip();
            cBunkerExplosion.open(bunkerE);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException uafe) {
            uafe.printStackTrace();
        }

        cBunkerExplosion.start();
    }

    /**
     * Alien explosion effect
     */
    public void alienExplosion() {
        try {
            String path = System.getProperty("user.dir").concat("/assets/sounds/alienExplosion.wav");
            File file = new File(path);
            alienE = AudioSystem.getAudioInputStream(file);
            cAlienExplosion = AudioSystem.getClip();
            cAlienExplosion.open(alienE);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException uafe) {
            uafe.printStackTrace();
        }

        cAlienExplosion.start();
    }

    /**
     * Alien movement effect
     */
    public void alienMovement() {
        try {
            String path = System.getProperty("user.dir").concat("/assets/sounds/alienMovement.wav");
            File file = new File(path);
            alienM = AudioSystem.getAudioInputStream(file);
            cAlienMovement = AudioSystem.getClip();
            cAlienMovement.open(alienM);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException uafe) {
            uafe.printStackTrace();
        }

        cAlienMovement.start();
    }

    /**
     * Alien victory effect
     */
    public void alienVictory() {
        try {
            String path = System.getProperty("user.dir").concat("/assets/sounds/alienVictory.wav");
            File file = new File(path);
            alienV = AudioSystem.getAudioInputStream(file);
            cAlienVictory = AudioSystem.getClip();
            cAlienVictory.open(alienV);
            cAlienVictory.loop(1);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException uafe) {
            uafe.printStackTrace();
        }
        cAlienVictory.start();
    }

    /**
     * Alien shoot effect
     */
    public void alienShoot() {
        try {
            String path = System.getProperty("user.dir").concat("/assets/sounds/alienShoot.wav");
            File file = new File(path);
            alienS = AudioSystem.getAudioInputStream(file);
            cAlienShoot = AudioSystem.getClip();
            cAlienShoot.open(alienS);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException uafe) {
            uafe.printStackTrace();
        }
        cAlienShoot.start();
    }
}
