package pacman.guiobjects;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;
import pacman.pacman.leveleditor.ImageGetter;
import pacman.pacman.leveleditor.WindowHandler;

/**
 *
 * @author eerop
 */
public class LevelFailed extends JPanel{
    
    private JButton button;
    private ImageGetter imgGetter;
    private BufferedImage image;
    private WindowHandler wh;

    public LevelFailed(ImageGetter imgGetter, WindowHandler wh) {
        this.wh = wh;
        this.imgGetter = imgGetter;
        this.image = imgGetter.getImage("Pictures/PacMan_lvlfailed.png");
        repaint();
        
        setLayout(null);
        
        this.button = new JButton("Back to Menu");
        button.setFont(new Font("Verdana", Font.BOLD, 20));
        button.setBounds(704, 576, 224, 64);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                wh.startMenu();
            }
        });
        
        add(button);
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(image, 0, 0, this);
    }
    
}
