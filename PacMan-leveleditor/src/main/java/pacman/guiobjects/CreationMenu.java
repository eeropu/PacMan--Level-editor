package pacman.guiobjects;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import pacman.pacman.leveleditor.ImageGetter;

/**
 *
 * @author eerop
 *
 * This class manages the creationmenu, that allowx user to create a new level
 * or modify an old one.
 */
public class CreationMenu extends JPanel{
    
    private BufferedImage image;
    private JButton createNew, modifyOld, back;
    private ImageGetter imgGetter;

    public CreationMenu(ImageGetter imgGetter) {
        
        this.imgGetter = imgGetter;
        this.image = imgGetter.getImage("Pictures/PacMan_Createmode.png");
        repaint();
        
        setLayout(null);
        
        createNew = new JButton("Create New!");
        createNew.setBounds(64, 64, 320, 128);
        createNew.setFont(new Font("Verdana", Font.BOLD, 30));
        add(createNew);
        
        modifyOld = new JButton("Modify Existing!");
        modifyOld.setBounds(64, 192, 320, 128);
        modifyOld.setFont(new Font("Verdana", Font.BOLD, 30));
        add(modifyOld);
        
        back = new JButton("back");
        back.setBounds(64, 608, 96, 32);
        add(back);
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(image, 0, 0, this);
    }
}
