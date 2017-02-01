package gameobjects;

public class Coordinate implements Comparable<Coordinate>{
    
    private int x, y, distance;
    private double heuristic, expectedValue;
    private Coordinate previous;

    public Coordinate(int x, int y, int distance, int pacmanX, int pacmanY, Coordinate previous) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.heuristic = Math.sqrt(Math.pow(pacmanX - x, 2) + Math.pow(pacmanY - y, 2));
        this.expectedValue = distance + heuristic;
        this.previous = previous;
    }

    @Override
    public int compareTo(Coordinate o) {
        if(this.expectedValue > o.expectedValue){
            return 1;
        } else {
            return -1;
        }
    }
    
    @Override
    public String toString(){
        return x + "," + y;
    }
    
    public void setPrevious(Coordinate previous){
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
