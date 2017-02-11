package pacman.pacman.leveleditor;

import pacman.guiobjects.StartMenu;
import pacman.guiobjects.LevelCompleted;
import pacman.levelmanagement.LevelRunner;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pacman.database.LevelsDAO;
import pacman.guilisteners.LevelCompleteListener;
import pacman.guiobjects.CreationMenu;
import pacman.guiobjects.LevelSelectionMenu;

/**
 *
 * @author eerop
 *
 * This class is responsible for showing the correct contents (e.g. menus) on
 * the programs frame.
 */
public class WindowHandler implements Runnable {

    private CardLayout cardlayout;
    private JPanel cardPanel;
    private LevelRunner lr;
    private LevelCompleteListener lcl;
    private LevelSelectionMenu lsm;
    private CreationMenu cm;

    public WindowHandler() {
        cardlayout = new CardLayout();
        cardPanel = new JPanel(cardlayout);

        lcl = new LevelCompleteListener(this);

        StartMenu startmenu = new StartMenu(this);
        cardPanel.add(startmenu, "start");

        lsm = new LevelSelectionMenu(new LevelsDAO(), this);
        cardPanel.add(lsm, "levelselection");
        
        cm = new CreationMenu();
        cardPanel.add(cm, "create");
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("PacMan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setPreferredSize(new Dimension(960, 672));
        frame.setResizable(false);
        frame.add(cardPanel);
        frame.pack();
    }

    public void startMenu() {
        cardlayout.show(cardPanel, "start");
    }

    public void lvlslctmenu() {
        lsm.build();
        cardlayout.show(cardPanel, "levelselection");
    }
    
    public void creationmenu(){
        cardlayout.show(cardPanel, "create");
    }

    public void runLevel(String s) {
        LevelRunner lr = new LevelRunner(this, s);
        cardPanel.add(lr, "lr");
        cardlayout.show(cardPanel, "lr");
        lr.start();
    }

    public void lvlCompleted() {
        cardPanel.add(new LevelCompleted(lcl), "lc");
        cardlayout.show(cardPanel, "lc");
    }

}
