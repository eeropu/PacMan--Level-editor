package levelmanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop implements ActionListener {

    private LevelRunner lr;

    public GameLoop(LevelRunner lr) {
        this.lr = lr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        lr.move();
        lr.checkCollision();
        lr.repaint();
    }

}
