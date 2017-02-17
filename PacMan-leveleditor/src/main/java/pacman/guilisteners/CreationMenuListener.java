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
    
    private JButton createNew, modifyOld, back, delete;
    private WindowHandler wh;

    public CreationMenuListener(WindowHandler wh) {
        this.wh = wh;
    }
    
    public void setButtons(JButton create, JButton modify, JButton back, JButton delete){
        this.createNew = create;
        this.modifyOld = modify;
        this.back = back;
        this.delete = delete;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createNew){
            wh.createMode("new");
        } else if(e.getSource() == modifyOld){
            wh.lvlslctmenu("modify");
        } else if(e.getSource() == back){
            wh.startMenu();
        } else if(e.getSource() == delete){
            wh.lvlslctmenu("delete");
        }
    }
}
