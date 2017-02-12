package pacman.guilisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import pacman.pacman.leveleditor.WindowHandler;

/**
 *
 * @author eerop
 */
public class CreationMenuListener implements ActionListener {
    
    private JButton createNew, modifyOld, back;
    private WindowHandler wh;

    public CreationMenuListener(WindowHandler wh) {
        this.wh = wh;
    }
    
    public void setButtons(JButton create, JButton modify, JButton back){
        this.createNew = create;
        this.modifyOld = modify;
        this.back = back;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createNew){
            wh.createMode();
        } else if(e.getSource() == modifyOld){
            
        } else if(e.getSource() == back){
            wh.startMenu();
        }
    }
}
