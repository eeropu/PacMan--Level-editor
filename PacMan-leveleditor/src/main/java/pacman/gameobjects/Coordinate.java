package pacman.gameobjects;

/**
 *
 * @author eerop
 *
 * Coordinate is used by the Ghost-classes to find pacman and then deciding the
 * direction where the ghost moves.
 */
public class Coordinate implements Comparable<Coordinate> {

    private int x, y, distance;
    private double heuristic, expectedValue;
    private Coordinate previous;
    private boolean runAway;

    public Coordinate(int x, int y, int distance, int pacmanX, int pacmanY, Coordinate previous, boolean runaway) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.heuristic = Math.sqrt(Math.pow(pacmanX - x, 2) + Math.pow(pacmanY - y, 2));
        this.expectedValue = distance + heuristic;
        this.previous = previous;
        this.runAway = runaway;
    }

    @Override
    public int compareTo(Coordinate o) {
        if (this.expectedValue > o.expectedValue) {
            if (!runAway) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if (!runAway) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public void setPrevious(Coordinate previous) {
        this.previous = previous;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance() {
        return distance;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public Coordinate getPrevious() {
        return previous;
    }

}
