package pacman.gameobjects;

/**
 * This class is responsible for managing the Blinky-ghosts. Blinky is the ghost
 * that follows and tries to catch pacman when it gets close enough. until then
 * it moves randomly.
 *
 * @author eerop
 */
public class Blinky extends Ghost {

    /**
     * Equal to Ghost class -constructor.
     *
     * @param x starting x position of this ghost
     * @param y starting y position of this ghost
     * @param pacman reference to pacman that is used to by the checkCollision
     * method
     */
    public Blinky(int x, int y, PacMan pacman) {
        super(x, y, pacman);
    }

    /**
     * Used to move this ghost and to set it's direction and movement speed.
     */
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
