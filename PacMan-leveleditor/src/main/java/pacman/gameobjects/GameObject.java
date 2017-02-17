package pacman.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author eerop
 *
 * Interface that is implemented by all in-game objects.
 */
public interface GameObject {

    /**
     * Used to paint the gameobject.
     *
     * @param g graphics used by JPanel
     */
    public void paint(Graphics g);

    /**
     * Used to move the gameobject
     */
    public void move();

    /**
     * Returns a rectangle that represents the area this object occupies.
     *
     * @return
     */
    public Rectangle getBounds();

    /**
     * Checs if this object hits the PacMan.
     *
     * @param pacman
     * @return true if pacman hits this object, else false.
     */
    public boolean checkCollision(PacMan pacman);
}
