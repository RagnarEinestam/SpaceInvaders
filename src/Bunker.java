import java.awt.*;

/**
 * Class that draws out a bunker for the player to
 * hide behind and stop incoming enemy missiles
 * Bunker positions depends on constructor inputs
 *
 * @author Ragnar E
 */
public class Bunker {

    //Starting position of bunker
    private int xStartPos;
    private int yStartPos;

    //Position of blocks
    private int xPos;
    private int yPos;

    //Block
    private Block b[][];

    //Sounds
    private Sounds bunkerExplosion;

    //Template
    private static int[][] template = {
            {0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 0, 1, 1, 1}};

    /**
     * Constructor, initiates variables and bunker array
     *
     * @param x - bunker xStartingPosition
     * @param y - bunker yStartingPosition
     */
    public Bunker(int x, int y) {
        xStartPos = x;
        yStartPos = y;

        xPos = x;
        yPos = y;

        b = new Block[8][10];

        bunkerExplosion = new Sounds();

        for (int i = 0; i < b.length; i++) {
            xPos = xStartPos;
            for (int j = 0; j < b[i].length; j++) {
                if (template[i][j] == 1) {
                    b[i][j] = new Block(xPos, yPos);
                    b[i][j].setBlockState(true);
                    xPos += 10;
                } else {
                    b[i][j] = new Block(xPos, yPos);
                    b[i][j].setBlockState(false);
                    xPos += 10;
                }
            }
            yPos += 10;
        }

        xPos = xStartPos;
        yPos = yStartPos;
    }

    /**
     * private block class, blocks that make up the bunker
     * and are used to keep track of bunker state
     *
     * @author Ragnar E
     */
    private class Block {
        private boolean blockState;

        private int xPos;
        private int yPos;

        private Graphics g;

        /**
         * Constructor
         *
         * @param x - position of block
         * @param y - position of block
         */
        public Block(int x, int y) {
            xPos = x;
            yPos = y;
            g = null;
        }

        /**
         * sets the state of a block
         *
         * @param b
         */
        public void setBlockState(boolean b) {
            blockState = b;
        }

        /**
         * returns the state of a block
         *
         * @return
         */
        public boolean getBlockState() {
            return blockState;
        }

        /**
         * returns x position of block
         *
         * @return int x position
         */
        public int getXPos() {
            return xPos;
        }

        /**
         * returns y-position of block
         *
         * @return - int y position
         */
        public int getYPos() {
            return yPos;
        }

        /**
         * Sets graphics color to parameter
         *
         * @param c
         */
        public void setColor(Color c) {
            g.setColor(c);
        }

        /**
         * Set graphics to parameter
         *
         * @param g
         */
        public void setGraphics(Graphics g) {
            this.g = g;
        }

        /**
         * Draw single block
         */
        public void drawBlock() {
            g.fillRect(xPos, yPos, 10, 10);
        }
    }

    /**
     * Set state of block, dead/alive
     */
    public boolean blockState(int x, int y) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                if (b[i][j].getBlockState()) {
                    if ((b[i][j].getXPos() <= x) && (b[i][j].getXPos() + 10 >= x)) {
                        if ((b[i][j].getYPos() <= y) && (b[i][j].getYPos() + 10 >= y)) {
                            b[i][j].setBlockState(false);
                            b[i][j].setColor(Color.BLACK);
                            bunkerExplosion.bunkerExplosion();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Repaint bunker to game window
     *
     * @param g
     */
    public void repaintBunker(Graphics g) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                if (b[i][j].getBlockState() == true) {
                    b[i][j].setGraphics(g);
                    b[i][j].setColor(Color.GREEN);
                    b[i][j].drawBlock();
                }
            }
        }
    }
}