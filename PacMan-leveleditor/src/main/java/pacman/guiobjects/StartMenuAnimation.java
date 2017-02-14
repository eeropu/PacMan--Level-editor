package pacman.guiobjects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author eerop
 */
public class StartMenuAnimation implements ActionListener {

    private int currentimg;
    private StartMenu sm;
    private BufferedImage[] images;
    private boolean change;

    public StartMenuAnimation(StartMenu sm, BufferedImage[] images) {
        this.sm = sm;
        this.images = images;
        currentimg = 0;
        this.change = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentimg++;
        if (currentimg < 32) {
            sm.setImage(images[currentimg]);
        } else if (currentimg == 32) {
            sm.setImage(images[0]);
        }
        if (currentimg == 31 || currentimg == 15) {
            sm.setDelay(1000);
        } else if (currentimg == 0 || currentimg == 16) {
            sm.setDelay(20);
        } else if (currentimg == 32) {
            sm.setDelay(3);
        }
        if (currentimg > 31 && currentimg <= 720) {
            if (change) {
                sm.pacmanrunaway();
            } else {
                sm.pacmanchase();
            }
        } else if (currentimg == 721) {
            currentimg = 0;
            sm.setDelay(20);
            if (change) {
                change = false;
                sm.resetPacmanrun();
            } else {
                change = true;
                sm.resetPacmanchase();
            }
        }
        sm.repaint();
    }

}
