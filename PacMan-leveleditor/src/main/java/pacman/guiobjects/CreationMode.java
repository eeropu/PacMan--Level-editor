package pacman.guiobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import pacman.guilisteners.CreationModeButtonListener;
import pacman.guilisteners.CreationModeListener;
import pacman.pacman.leveleditor.ImageGetter;
import pacman.pacman.leveleditor.WindowHandler;

/**
 * Used to create and modify levels.
 *
 * @author eerop
 */
public class CreationMode extends JPanel {

    private BufferedImage grid, wall, pacman, blinky, pinky, clyde, randomghost, pb, pp;
    private JRadioButton wallR, pacmanR, blinkyR, pinkyR, clydeR, randomghostR, pbR, ppR, autofill;
    private JButton help, ready;
    private JLabel[][] labels;
    private String[][] objectpositioning;
    private ImageGetter imgGetter;
    private WindowHandler wh;
    private CreationModeListener cml;
    private CreationModeButtonListener cmbl;

    public CreationMode(ImageGetter imgGetter, WindowHandler wh, String old) {
        setLayout(null);
        this.imgGetter = imgGetter;
        getImages();
        this.wh = wh;
        setRadioButtons();

        cml = new CreationModeListener(grid, wall, pacman, blinky, pinky, clyde, randomghost, pb, pp,
                wallR, pacmanR, blinkyR, pinkyR, clydeR, randomghostR, pbR, ppR, autofill);

        this.labels = new JLabel[30][20];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 20; j++) {
                labels[i][j] = new JLabel();
                labels[i][j].setBounds(i * 32, j * 32, 32, 32);
                labels[i][j].addMouseListener(cml);
                add(labels[i][j]);
            }
        }

        this.objectpositioning = new String[30][20];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 20; j++) {
                objectpositioning[i][j] = "x";
            }
        }

        if (!old.equals("new")) {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 30; j++) {
                    char c = old.charAt(i * 30 + j);
                    if (c == 'W') {
                        objectpositioning[j][i] = "W";
                        labels[j][i].setIcon(new ImageIcon(wall));
                    } else if (c == 'P') {
                        objectpositioning[j][i] = "P";
                        labels[j][i].setIcon(new ImageIcon(pacman));
                    } else if (c == 'L') {
                        objectpositioning[j][i] = "L";
                        labels[j][i].setIcon(new ImageIcon(blinky));
                    } else if (c == 'I') {
                        objectpositioning[j][i] = "I";
                        labels[j][i].setIcon(new ImageIcon(pinky));
                    } else if (c == 'C') {
                        objectpositioning[j][i] = "C";
                        labels[j][i].setIcon(new ImageIcon(clyde));
                    } else if (c == 'R') {
                        objectpositioning[j][i] = "R";
                        labels[j][i].setIcon(new ImageIcon(randomghost));
                    } else if (c == 'b') {
                        objectpositioning[j][i] = "b";
                        labels[j][i].setIcon(new ImageIcon(pb));
                    } else if (c == 'p') {
                        objectpositioning[j][i] = "p";
                        labels[j][i].setIcon(new ImageIcon(pp));
                    }
                }
            }
        }

        cml.setLabels(labels);
        cml.setObjectPositioning(objectpositioning);

        setButtons();
    }

    private void getImages() {
        grid = imgGetter.getImage("Pictures/PacMan_Createmodegrid.png");
        wall = imgGetter.getSubImage(1);
        pacman = imgGetter.getSubImage(3);
        blinky = imgGetter.getSubImage(5);
        pinky = imgGetter.getSubImage(4);
        clyde = imgGetter.getSubImage(6);
        randomghost = imgGetter.getSubImage(7);
        pb = imgGetter.getSubImage(9);
        pp = imgGetter.getSubImage(10);
    }

    private void setRadioButtons() {
        wallR = new JRadioButton();
        wallR.setBounds(38, 646, 20, 20);
        wallR.setBackground(Color.black);
        pacmanR = new JRadioButton();
        pacmanR.setBounds(134, 646, 20, 20);
        pacmanR.setBackground(Color.black);
        blinkyR = new JRadioButton();
        blinkyR.setBounds(230, 646, 20, 20);
        blinkyR.setBackground(Color.black);
        pinkyR = new JRadioButton();
        pinkyR.setBounds(326, 646, 20, 20);
        pinkyR.setBackground(Color.black);
        clydeR = new JRadioButton();
        clydeR.setBounds(422, 646, 20, 20);
        clydeR.setBackground(Color.black);
        randomghostR = new JRadioButton();
        randomghostR.setBounds(518, 646, 20, 20);
        randomghostR.setBackground(Color.black);
        pbR = new JRadioButton();
        pbR.setBounds(598, 646, 20, 20);
        pbR.setBackground(Color.black);
        ppR = new JRadioButton();
        ppR.setBounds(678, 646, 20, 20);
        ppR.setBackground(Color.black);
        autofill = new JRadioButton("autofill");
        autofill.setBounds(758, 646, 64, 20);
        autofill.setBackground(Color.black);
        autofill.setForeground(Color.white);

        ButtonGroup bg = new ButtonGroup();
        bg.add(wallR);
        bg.add(pacmanR);
        bg.add(blinkyR);
        bg.add(pinkyR);
        bg.add(clydeR);
        bg.add(randomghostR);
        bg.add(pbR);
        bg.add(ppR);

        add(wallR);
        add(pacmanR);
        add(blinkyR);
        add(pinkyR);
        add(clydeR);
        add(randomghostR);
        add(pbR);
        add(ppR);
        add(autofill);

    }

    private void setButtons() {
        this.help = new JButton("?");
        help.setBounds(824, 640, 48, 32);
        help.setBackground(Color.darkGray);
        help.setForeground(Color.white);
        this.ready = new JButton("ready");
        ready.setBounds(880, 640, 80, 32);
        ready.setBackground(Color.darkGray);
        ready.setForeground(Color.white);

        cmbl = new CreationModeButtonListener(help, ready, wh, objectpositioning);

        help.addActionListener(cmbl);
        ready.addActionListener(cmbl);

        add(help);
        add(ready);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(grid, 0, 0, this);
        g.setColor(Color.black);
        g.fillRect(0, 640, 960, 32);
        g.drawImage(wall, 64, 640, this);
        g.drawImage(pacman, 160, 640, this);
        g.drawImage(blinky, 256, 640, this);
        g.drawImage(pinky, 352, 640, this);
        g.drawImage(clyde, 448, 640, this);
        g.drawImage(randomghost, 544, 640, this);
        g.drawImage(pb, 624, 640, this);
        g.drawImage(pp, 704, 640, this);
    }

}
