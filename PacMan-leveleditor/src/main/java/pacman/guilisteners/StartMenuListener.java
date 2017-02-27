package pacman.guilisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import pacman.pacman.leveleditor.WindowHandler;

/**
 * Gives functionality to the components in the "Startmenu"-screen.
 *
 * @author eerop
 */
public class StartMenuListener implements ActionListener {

    private JButton play, create, highscores;
    private WindowHandler wh;

    /**
     * Constructor for the StartMenuListener-class.
     *
     * @param play Button that directs to the levelselectionmenu that leads to
     * playing a level
     * @param create Button that directs to the creationmenu
     * @param highscores Button that directs to the levelselectionmenu that
     * leads to highscores of a level
     * @param wh windowhandler that is used to change the content shown on the
     * screen
     */
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
        } else if (e.getSource() == highscores) {
            wh.lvlslctmenu("highscores");
        }
    }

}
