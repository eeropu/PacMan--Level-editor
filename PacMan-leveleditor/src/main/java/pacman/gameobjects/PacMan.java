package pacman.gameobjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import pacman.levelmanagement.LevelRunner;

/**
 * This class is responsible for PacMan.
 *
 * @author eerop
 *
 */
public class PacMan implements GameObject {

    private int x, y, origX, origY, move;
    private Direction current, changed;
    private double rotationCurrent, rotationChanged;
    private AffineTransform aT;
    private BufferedImage drawable, mouthClosed, mouthOpen;
    private LevelRunner lr;

    /**
     * Constructor for the class PacMan.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param d Starting direction
     */
    public PacMan(int x, int y, Direction d) {
        this.x = 32 * x - 32;
        this.y = 32 * y - 32;
        this.origX = this.x;
        this.origY = this.y;
        this.rotationCurrent = 0;
        this.rotationChanged = 0;
        this.current = d;
        this.changed = d;
        this.move = 2;
        this.aT = new AffineTransform();

        Timer timer = new Timer(200, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changeImg();
            }
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        aT.rotate(0, 0, 0);
        g2d.rotate(rotationCurrent, x + 16, y + 16);
        g2d.drawImage(drawable, x, y, lr);
        g2d.setTransform(aT);
    }

    @Override
    public void move() {
        if (this.current == Direction.Right) {
            x += move;
        } else if (this.current == Direction.Left) {
            x -= move;
        } else if (this.current == Direction.Down) {
            y += move;
        } else if (this.current == Direction.Up) {
            y -= move;
        }
        outOfScreen();
        changeDirection();
    }

    /**
     * Moves the pacman to the opposite side of screen when it gets out of it.
     */
    public void outOfScreen() {
        if (x > 960) {
            x = -32;
        } else if (x < -32) {
            x = 960;
        }
        if (y > 640) {
            y = -32;
        } else if (y < -32) {
            y = 640;
        }
    }

    /**
     * changes the direction of pacman when it gets to an allowed position.
     */
    public void changeDirection() {
        if (x >= 0 && x <= 928 && y >= 0 && y <= 608) {
            if (x % 32 == 0 && y % 32 == 0) {
                current = changed;
                rotationCurrent = rotationChanged;
            }
        }
    }

    /**
     * Changes the direction to right.
     */
    public void right() {
        this.changed = Direction.Right;
        this.rotationChanged = 0;
    }

    /**
     * Changes the direction to left.
     */
    public void left() {
        this.changed = Direction.Left;
        this.rotationChanged = Math.PI;
    }

    /**
     * Changes the direction to down.
     */
    public void down() {
        this.changed = Direction.Down;
        this.rotationChanged = Math.PI / 2;
    }

    /**
     * Changes the direction to up.
     */
    public void up() {
        this.changed = Direction.Up;
        this.rotationChanged = -Math.PI / 2;
    }

    /**
     * stops pacmans movementand moves it to a new position to avoid getting
     * stuck in a wall.
     */
    public void stop() {
        if (current == Direction.Right) {
            x -= move;
        } else if (current == Direction.Left) {
            x += move;
        } else if (current == Direction.Down) {
            y -= move;
        } else if (current == Direction.Up) {
            y += move;
        }
        this.current = Direction.Stop;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public boolean checkCollision(PacMan pacman) {
        return false;
    }

    /**
     * Moves pacman to it's original position.
     */
    public void reset() {
        x = origX;
        y = origY;
    }

    /**
     * Sets the images for pacman.
     *
     * @param img image where pacmans mouth is open
     * @param img2 image where pacmans mouth is closed
     */
    public void setImages(BufferedImage img, BufferedImage img2) {
        this.drawable = img;
        this.mouthOpen = img;
        this.mouthClosed = img2;
    }

    public void setImageObserver(LevelRunner lr) {
        this.lr = lr;
    }

    /**
     * Changes the image of pacman (Allows pacmans mouth to open and close).
     */
    public void changeImg() {
        if (drawable == mouthOpen) {
            drawable = mouthClosed;
        } else {
            drawable = mouthOpen;
        }
    }

    //Following classes are for test purposes
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMove() {
        return move;
    }

    public Direction getCurrent() {
        return current;
    }

    public Direction getChanged() {
        return changed;
    }

    public BufferedImage getDrawable() {
        return drawable;
    }

    public BufferedImage getMouthClosed() {
        return mouthClosed;
    }

    public BufferedImage getMouthOpen() {
        return mouthOpen;
    }
}
