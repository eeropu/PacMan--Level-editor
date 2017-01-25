package pacman.pacman.leveleditor;

import LevelManagement.LevelRunner;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowHandler implements Runnable{
    
    private CardLayout cardlayout;
    private JPanel cardPanel;

    public WindowHandler() {
        cardlayout = new CardLayout();
        cardPanel = new JPanel(cardlayout);
        LevelRunner lr = new LevelRunner();
        lr.start();
        cardPanel.add(lr, "lr");
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("PacMan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(960, 640));
        frame.setResizable(false);
        frame.add(cardPanel);
        frame.pack();
    }
    
}
