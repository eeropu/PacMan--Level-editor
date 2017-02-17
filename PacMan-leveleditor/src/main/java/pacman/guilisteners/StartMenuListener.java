package pacman.guilisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import pacman.pacman.leveleditor.WindowHandler;

/**
 *
 * @author eerop
 *
 * Gives functionality to the components in the "Startmenu"-screen.
 */
public class StartMenuListener implements ActionListener {

    private JButton play, create, highscores;
    private WindowHandler wh;

    public StartMenuListener(JButton play, JButton create, JButton highscores, WindowHandler wh) {
        this.play = play;
        this.create = create;
        this.highscores = highscores;
        this.wh = wh;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            wh.lvlslctmenu("play");
        } else if (e.getSource() == create) {
            wh.creationmenu();
        }
    }

}
