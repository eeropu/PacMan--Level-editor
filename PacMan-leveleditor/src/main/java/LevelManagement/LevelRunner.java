package LevelManagement;

import GameObjects.Direction;
import GameObjects.PacMan;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class LevelRunner extends JPanel{
    
    private GameLoop gl;
    private PacMan pacman; 
    private Timer timer;

    public LevelRunner() {
        gl = new GameLoop(this);
        pacman = new PacMan(100, 100, Direction.Right);
        
        controlSetUp();
        
        timer = new Timer(15, gl);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        pacman.paint(g);
    }
    
    public void move(){
        pacman.move();
    }
    
    public void start(){
        timer.start();
    }
    
    public void controlSetUp(){
        Action right = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pacman.right();
            }
        };
        Action left = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pacman.left();
            }
        };
        Action down = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pacman.down();
            }
        };
        Action up = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pacman.up();
            }
        };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");
        getActionMap().put("right", right);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");
        getActionMap().put("left", left);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
        getActionMap().put("down", down);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
        getActionMap().put("up", up);
    }
}
