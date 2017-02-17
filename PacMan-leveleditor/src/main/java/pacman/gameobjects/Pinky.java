package pacman.gameobjects;

/**
 *
 * @author eerop
 *
 * This class is responsible for managing the Pinky-ghost. Pinky is the ghost
 * that tries to position itself infront of pacman until it gets close enough
 * and then it just tries to catch it.
 */
public class Pinky extends Ghost {

    public Pinky(int x, int y, PacMan pacman) {
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
        if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) <= 160) {
            dSetRandomly = false;
            if (x % 32 == 0 && y % 32 == 0) {
                setDirectionAStar(pacman.getX(), pacman.getY(), ppEaten);
            }
        } else if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) < 320) {
            dSetRandomly = false;
            if (x % 32 == 0 && y % 32 == 0) {
                if (ppEaten) {
                    setDirectionAStar(pacman.getX(), pacman.getY(), true);
                } else {
                    if (pacman.getCurrent() == Direction.Right) {
                        setDirectionAStar(pacman.getX() + 128, pacman.getY(), false);
                    } else if (pacman.getCurrent() == Direction.Left) {
                        setDirectionAStar(pacman.getX() - 128, pacman.getY(), false);
                    } else if (pacman.getCurrent() == Direction.Down) {
                        setDirectionAStar(pacman.getX(), pacman.getY() + 128, false);
                    } else if (pacman.getCurrent() == Direction.Up) {
                        setDirectionAStar(pacman.getX(), pacman.getY() - 128, false);
                    }
                }
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
