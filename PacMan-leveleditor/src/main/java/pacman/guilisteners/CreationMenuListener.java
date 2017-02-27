package pacman.guilisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import pacman.pacman.leveleditor.WindowHandler;

/**
 * Used to set actions for the buttons in the creationmenu.
 *
 * @author eerop
 */
public class CreationMenuListener implements ActionListener {

    private JButton createNew, modifyOld, back, delete;
    private WindowHandler wh;

    /**
     * Constructor for the CreationMenuListener-class.
     *
     * @param wh reference to windowhandler that allows buttons used in this
     * class to change the content on screen
     */
    public CreationMenuListener(WindowHandler wh) {
        this.wh = wh;
    }

    /**
     * Sets the buttons that this class gives actions to.
     *
     * @param create button directing to a creation mode of a new level
     * @param modify button directiong to the creation mode of an existing level
     * @param back button direction to the start menu
     * @param delete buttom directiong to levelselection where user can delete
     * levels
     */
    public void setButtons(JButton create, JButton modify, JButton back, JButton delete) {
        this.createNew = create;
        this.modifyOld = modify;
        this.back = back;
        this.delete = delete;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createNew) {
            wh.createMode("new");
        } else if (e.getSource() == modifyOld) {
            wh.lvlslctmenu("modify");
        } else if (e.getSource() == back) {
            wh.startMenu();
        } else if (e.getSource() == delete) {
            wh.lvlslctmenu("delete");
        }
    }
}
