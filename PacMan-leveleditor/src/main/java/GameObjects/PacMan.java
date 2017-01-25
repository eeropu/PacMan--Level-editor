package GameObjects;

import java.awt.Graphics;

public class PacMan implements GameObject {

    private int x, y;
    private Direction d;

    public PacMan(int x, int y, Direction d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    @Override
    public void paint(Graphics g) {
        g.fillOval(x, y, 32, 32);
    }

    @Override
    public void move() {
        if (this.d == Direction.Right) {
            x += 2;
        } else if (this.d == Direction.Left){
            x -= 2;
        } else if (this.d == Direction.Down){
            y += 2;
        } else if (this.d == Direction.Up){
            y -= 2;
        }
    }
    
    public void right(){
        this.d = Direction.Right;
    }
    public void left(){
        this.d = Direction.Left;
    }
    public void down(){
        this.d = Direction.Down;
    }
    public void up(){
        this.d = Direction.Up;
    }

}
