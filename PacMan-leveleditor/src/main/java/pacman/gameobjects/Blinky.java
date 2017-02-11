package pacman.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pacman.levelmanagement.LevelRunner;

/**
 *
 * @author eerop
 *
 * This class is responsible for managing the Blinky-ghosts. Blinky is the ghost
 * that follows and tries to catch pacman when it gets close enough. until then
 * it moves randomly.
 */
public class Blinky extends Ghost {

    public Blinky(int x, int y, PacMan pacman) {
        super(x, y, pacman);
    }

    @Override
    public void move() {
        if (ppEaten) {
            eatableTimer(System.currentTimeMillis());
        }
        if (move == 1 && !ppEaten) {
            if (x % 32 == 0 && y % 32 == 0) {
                move = 2;
            }
        }
        if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) < 192) {
            dSetRandomly = false;
            if (x % 32 == 0 && y % 32 == 0) {
                setDirectionAStar(pacman.getX(), pacman.getY(), ppEaten);
            }
        } else {
            dSetRandomly = true;
            if (x % 32 == 0 && y % 32 == 0) {
                randir.randomDirection(this);
            }
        }
        super.move();
    }
}
