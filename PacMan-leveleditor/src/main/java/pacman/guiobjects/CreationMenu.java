package pacman.guiobjects;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;
import pacman.guilisteners.CreationMenuListener;
import pacman.pacman.leveleditor.ImageGetter;

/**
 *
 * @author eerop
 *
 * This class manages the creationmenu, that allows user to create a new level
 * or modify an old one.
 */
public class CreationMenu extends JPanel {

    private BufferedImage image;
    private JButton createNew, modifyOld, back, delete;
    private CreationMenuListener cml;
    private ImageGetter imgGetter;

    public CreationMenu(CreationMenuListener cml, ImageGetter imgGetter) {
        this.cml = cml;
        this.imgGetter = imgGetter;
        this.image = imgGetter.getImage("Pictures/PacMan_Createmode.png");
        repaint();

        setLayout(null);

        createNew = new JButton("Create New!");
        createNew.setBounds(64, 64, 320, 128);
        createNew.setFont(new Font("Verdana", Font.BOLD, 30));

        modifyOld = new JButton("Modify Existing!");
        modifyOld.setBounds(64, 192, 320, 128);
        modifyOld.setFont(new Font("Verdana", Font.BOLD, 30));

        delete = new JButton("Delete");

        delete.setBounds(96, 352, 256, 32);

        back = new JButton("back");
        back.setBounds(64, 608, 96, 32);

        cml.setButtons(createNew, modifyOld, back, delete);
        modifyOld.addActionListener(cml);
        back.addActionListener(cml);
        createNew.addActionListener(cml);
        delete.addActionListener(cml);

        add(createNew);
        add(modifyOld);
        add(back);
        add(delete);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
