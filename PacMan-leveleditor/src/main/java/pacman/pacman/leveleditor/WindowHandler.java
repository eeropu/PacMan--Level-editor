package pacman.pacman.leveleditor;

import pacman.guiobjects.StartMenu;
import pacman.guiobjects.LevelCompleted;
import pacman.levelmanagement.LevelRunner;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pacman.database.HighscoresDAO;
import pacman.database.LevelsDAO;
import pacman.guilisteners.CreationMenuListener;
import pacman.guilisteners.LevelCompleteListener;
import pacman.guiobjects.CreationMenu;
import pacman.guiobjects.CreationMode;
import pacman.guiobjects.FinishCreation;
import pacman.guiobjects.Help;
import pacman.guiobjects.LevelFailed;
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
    private CreationMenuListener cml;
    private LevelFailed lf;
    private Help help;
    private ImageGetter imgGetter;

    public WindowHandler() {
        cardlayout = new CardLayout();
        cardPanel = new JPanel(cardlayout);
        imgGetter = new ImageGetter();

        lcl = new LevelCompleteListener(this, new HighscoresDAO());

        StartMenu startmenu = new StartMenu(this, imgGetter);
        cardPanel.add(startmenu, "start");

        lsm = new LevelSelectionMenu(new LevelsDAO(), this);
        cardPanel.add(lsm, "levelselection");
        
        cml = new CreationMenuListener(this);
        
        cm = new CreationMenu(cml, imgGetter);
        cardPanel.add(cm, "creationmenu");
        
        help = new Help(imgGetter, this);
        cardPanel.add(help, "help");
        
        lf = new LevelFailed(imgGetter, this);
        cardPanel.add(lf, "failed");
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

    public void lvlslctmenu(String s) {
        lsm.build(s);
        cardlayout.show(cardPanel, "levelselection");
    }
    
    public void creationmenu(){
        cardlayout.show(cardPanel, "creationmenu");
    }
    
    public void createMode(String s){
        cardPanel.add(new CreationMode(imgGetter, this, s), "create");
        cardlayout.show(cardPanel, "create");
    }
    
    public void help(){
        cardlayout.show(cardPanel, "help");
    }
    
    public void returnToCreation(){
        cardlayout.show(cardPanel, "create");
    }
    
    public void finishedCreating(String level){
        cardPanel.add(new FinishCreation(level, this), "finish");
        cardlayout.show(cardPanel, "finish");
    }

    public void runLevel(String name, String level) {
        LevelRunner lr = new LevelRunner(this, name, level);
        cardPanel.add(lr, "lr");
        cardlayout.show(cardPanel, "lr");
        lr.start();
    }

    public void lvlCompleted(String name, int points) {
        cardPanel.add(new LevelCompleted(lcl, imgGetter, name, points), "lc");
        cardlayout.show(cardPanel, "lc");
    }
    
    public void lvlFailed(){
        cardlayout.show(cardPanel, "failed");
    }

}
