package LevelManagement;

import GameObjects.Direction;
import GameObjects.PacMan;
import GameObjects.Wall;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LevelRunner extends JPanel{
    
    private GameLoop gl;
    protected PacMan pacman; 
    private Wall w;
    private Timer timer;

    public LevelRunner() {
        gl = new GameLoop(this);
        pacman = new PacMan(100, 100, Direction.Right);
        w = new Wall(0, 0);
        
        ControlSetUp csu = new ControlSetUp(this);
        
        timer = new Timer(10, gl);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        pacman.paint(g);
        w.paint(g);
    }
    
    public void move(){
        pacman.move();
        w.move();
    }
    
    public void checkCollision(){
        if(w.checkCollision(pacman)){
            pacman.stop();
        }
    }
    
    public void start(){
        timer.start();
    }
}
