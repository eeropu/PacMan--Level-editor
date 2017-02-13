package pacman.guilisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import pacman.pacman.leveleditor.WindowHandler;

/**
 *
 * @author eerop
 */
public class CreationModeButtonListener implements ActionListener{
    
    private JButton help, ready;
    private WindowHandler wh;
    private String[][] objectPositioning;

    public CreationModeButtonListener(JButton help, JButton ready, WindowHandler wh, String[][] op) {
        this.help = help;
        this.ready = ready;
        this.wh = wh;
        this.objectPositioning = op;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == help){
            wh.help();
        } else if (e.getSource() == ready){
            wh.finishedCreating();
        }
    }
    
}
