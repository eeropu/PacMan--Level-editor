package gameobjects;
/*
 * The ghost that tries to find PacMan
 */

public class Blinky extends Ghost {

    public Blinky(int x, int y, PacMan pacman) {
        super(x, y, pacman);
    }

    @Override
    public void move() {
        if (x % 32 == 0 && y % 32 == 0) {
            setDirectionAStar(pacman.getX(), pacman.getY());
        }
        super.move();
    }
}
