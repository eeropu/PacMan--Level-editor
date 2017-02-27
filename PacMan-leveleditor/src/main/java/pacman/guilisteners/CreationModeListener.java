package pacman.guilisteners;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 * Used to set the functionality of creationmode.
 *
 * @author eerop
 */
public class CreationModeListener implements MouseListener {

    private final ImageIcon grid, wall, pacman, blinky, pinky, clyde, randomghost, pb, pp;
    private final JRadioButton wallR, pacmanR, blinkyR, pinkyR, clydeR, randomghostR, pbR, ppR, autofill;
    private int previousX, previousY;
    private boolean first;
    private JLabel[][] labels;
    private String[][] objectPositioning;

    /**
     * Constructor for the CreationMOdeListener-class.
     *
     * @param grid image of the grid in creationmode
     * @param wall image of a wall
     * @param pacman image of the Pacman
     * @param blinky image of Blinky
     * @param pinky image of Pinky
     * @param clyde image of Clyde
     * @param randomghost image of randomghost
     * @param pb image of pointbubble
     * @param pp image of powerpellet
     * @param wallR radiobutton for wall
     * @param pacmanR radiobutton for Pacman
     * @param blinkyR radiobutton for Blinky
     * @param pinkyR radiobutton for Pinky
     * @param clydeR radiobutton for Clyde
     * @param randomghostR radiobutton for randomghost
     * @param pbR radiobutton for pointbubble
     * @param ppR radiobutton for PowerPellet
     * @param autofill radiobutton for autofill
     */
    public CreationModeListener(BufferedImage grid, BufferedImage wall, BufferedImage pacman,
            BufferedImage blinky, BufferedImage pinky, BufferedImage clyde, BufferedImage randomghost,
            BufferedImage pb, BufferedImage pp, JRadioButton wallR, JRadioButton pacmanR,
            JRadioButton blinkyR, JRadioButton pinkyR, JRadioButton clydeR, JRadioButton randomghostR,
            JRadioButton pbR, JRadioButton ppR, JRadioButton autofill) {

        this.grid = new ImageIcon(grid);
        this.wall = new ImageIcon(wall);
        this.pacman = new ImageIcon(pacman);
        this.blinky = new ImageIcon(blinky);
        this.pinky = new ImageIcon(pinky);
        this.clyde = new ImageIcon(clyde);
        this.randomghost = new ImageIcon(randomghost);
        this.pb = new ImageIcon(pb);
        this.pp = new ImageIcon(pp);

        this.wallR = wallR;
        this.pacmanR = pacmanR;
        this.blinkyR = blinkyR;
        this.pinkyR = pinkyR;
        this.clydeR = clydeR;
        this.randomghostR = randomghostR;
        this.pbR = pbR;
        this.ppR = ppR;
        this.autofill = autofill;

        this.first = false;
        this.previousX = -1;
        this.previousY = -1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel l = (JLabel) e.getSource();
        if (wallR.isSelected()) {
            if (autofill.isSelected()) {
                if (first) {
                    if (l.getX() / 32 == previousX) {
                        if (l.getY() / 32 < previousY) {
                            for (int i = l.getY() / 32; i <= previousY; i++) {
                                objectPositioning[previousX][i] = "W";
                                setImage(labels[previousX][i], wall);
                            }
                        } else {
                            for (int i = previousY; i <= l.getY() / 32; i++) {
                                objectPositioning[previousX][i] = "W";
                                setImage(labels[previousX][i], wall);
                            }
                        }
                    } else if (l.getY() / 32 == previousY) {
                        if (l.getX() / 32 < previousX) {
                            for (int i = l.getX() / 32; i <= previousX; i++) {
                                objectPositioning[i][previousY] = "W";
                                setImage(labels[i][previousY], wall);
                            }
                        } else {
                            for (int i = previousX; i <= l.getX() / 32; i++) {
                                objectPositioning[i][previousY] = "W";
                                setImage(labels[i][previousY], wall);
                            }
                        }
                    }
                    for (int i = 0; i < 30; i++) {
                        labels[i][previousY].setBackground(null);
                        labels[i][previousY].setOpaque(false);
                    }
                    for (int i = 0; i < 20; i++) {
                        labels[previousX][i].setBackground(null);
                        labels[previousX][i].setOpaque(false);
                    }

                    first = false;
                } else {
                    previousX = l.getX() / 32;
                    previousY = l.getY() / 32;
                    first = true;
                    for (int i = 0; i < 30; i++) {
                        labels[i][previousY].setBackground(Color.LIGHT_GRAY);
                        labels[i][previousY].setOpaque(true);
                    }
                    for (int i = 0; i < 20; i++) {
                        labels[previousX][i].setBackground(Color.LIGHT_GRAY);
                        labels[previousX][i].setOpaque(true);
                    }
                    l.setBackground(Color.pink);
                    l.setOpaque(true);
                }
            } else {
                objectPositioning[l.getX() / 32][l.getY() / 32] = "W";
                setImage(l, wall);
            }

        } else if (pacmanR.isSelected()) {
            objectPositioning[l.getX() / 32][l.getY() / 32] = "P";
            setImage(l, pacman);
            if (first) {
                clearSelectable();
            }
        } else if (blinkyR.isSelected()) {
            objectPositioning[l.getX() / 32][l.getY() / 32] = "L";
            setImage(l, blinky);
            if (first) {
                clearSelectable();
            }
        } else if (pinkyR.isSelected()) {
            objectPositioning[l.getX() / 32][l.getY() / 32] = "I";
            setImage(l, pinky);
            if (first) {
                clearSelectable();
            }
        } else if (clydeR.isSelected()) {
            objectPositioning[l.getX() / 32][l.getY() / 32] = "C";
            setImage(l, clyde);
            if (first) {
                clearSelectable();
            }
        } else if (randomghostR.isSelected()) {
            objectPositioning[l.getX() / 32][l.getY() / 32] = "R";
            setImage(l, randomghost);
            if (first) {
                clearSelectable();
            }
        } else if (pbR.isSelected()) {
            objectPositioning[l.getX() / 32][l.getY() / 32] = "b";
            setImage(l, pb);
            if (first) {
                clearSelectable();
            }
        } else if (ppR.isSelected()) {
            objectPositioning[l.getX() / 32][l.getY() / 32] = "p";
            setImage(l, pp);
            if (first) {
                clearSelectable();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Removes the changes in the background of the boxes.
     */
    public void clearSelectable() {
        for (int i = 0; i < 30; i++) {
            labels[i][previousY].setBackground(null);
            labels[i][previousY].setOpaque(false);
        }
        for (int i = 0; i < 20; i++) {
            labels[previousX][i].setBackground(null);
            labels[previousX][i].setOpaque(false);
        }

        first = false;
    }

    /**
     * Sets the correct image to the JLabel in the clicked box.
     *
     * @param l the label in the clicked box.
     * @param i the icon that is going to be set (or removed if the label
     * already contains it).
     */
    public void setImage(JLabel l, ImageIcon i) {
        try {
            if (l.getIcon().equals(i)) {
                if (i.equals(wall) && (l.getX() == 0 || l.getX() == 928
                        || l.getY() == 608 || l.getY() == 0)) {
                    removeOtherWall(l);
                } else {
                    wallCheck(l, i);
                }
                l.setIcon(null);
                objectPositioning[l.getX() / 32][l.getY() / 32] = "x";
            } else {
                l.setIcon(i);
                wallCheck(l, i);
            }
        } catch (NullPointerException ex) {
            l.setIcon(i);
            wallCheck(l, i);
        }
        if (i.equals(pacman)) {
            for (JLabel[] label : labels) {
                for (JLabel label1 : label) {
                    try {
                        if (label1.getIcon().equals(pacman) && !label1.equals(l)) {
                            label1.setIcon(null);
                            objectPositioning[label1.getX() / 32][label1.getY() / 32] = "x";
                        }
                    } catch (NullPointerException e) {
                        continue;
                    }
                }
            }
        }
    }

    /**
     * Removes the corresponding wall from the other side of screen if one wall
     * is removed.
     *
     * @param l the first modified label
     */
    public void removeOtherWall(JLabel l) {
        if (l.getX() == 0) {
            labels[29][l.getY() / 32].setIcon(null);
            objectPositioning[29][l.getY() / 32] = "x";
            if (l.getY() == 0) {
                labels[29][19].setIcon(null);
                objectPositioning[29][19] = "x";
            } else if (l.getY() == 608) {
                labels[29][0].setIcon(null);
                objectPositioning[29][0] = "x";
            }
        } else if (l.getX() == 928) {
            labels[0][l.getY() / 32].setIcon(null);
            objectPositioning[0][l.getY() / 32] = "x";
            if (l.getY() == 0) {
                labels[0][19].setIcon(null);
                objectPositioning[0][19] = "x";
            } else if (l.getY() == 608) {
                labels[0][0].setIcon(null);
                objectPositioning[0][0] = "x";
            }
        }
        if (l.getY() == 0) {
            labels[l.getX() / 32][19].setIcon(null);
            objectPositioning[l.getX() / 32][19] = "x";
        } else if (l.getY() == 608) {
            labels[l.getX() / 32][0].setIcon(null);
            objectPositioning[l.getX() / 32][0] = "x";
        }
    }

    /**
     * adds a wall to the other side of screen if one wall is added.
     *
     * @param l first clicked label
     * @param i image
     */
    public void wallCheck(JLabel l, ImageIcon i) {
        try {
            if (l.getX() == 0) {
                if (i.equals(wall)) {
                    labels[29][l.getY() / 32].setIcon(wall);
                    objectPositioning[29][l.getY() / 32] = "W";
                    if (l.getY() == 0) {
                        labels[29][19].setIcon(wall);
                        objectPositioning[29][19] = "W";
                    } else if (l.getY() == 608) {
                        labels[29][0].setIcon(wall);
                        objectPositioning[29][0] = "W";
                    }
                } else {
                    if (labels[29][l.getY() / 32].getIcon().equals(wall)) {
                        removeOtherWall(l);
                    }
                }
            } else if (l.getX() == 928) {
                if (i.equals(wall)) {
                    labels[0][l.getY() / 32].setIcon(wall);
                    objectPositioning[0][l.getY() / 32] = "W";
                    if (l.getY() == 0) {
                        labels[0][19].setIcon(wall);
                        objectPositioning[0][19] = "W";
                    } else if (l.getY() == 608) {
                        labels[0][0].setIcon(wall);
                        objectPositioning[0][0] = "W";
                    }
                } else {
                    if (labels[0][l.getY() / 32].getIcon().equals(wall)) {
                        removeOtherWall(l);
                    }
                }
            }
            if (l.getY() == 0) {
                if (i.equals(wall)) {
                    labels[l.getX() / 32][19].setIcon(wall);
                    objectPositioning[l.getX() / 32][19] = "W";
                } else {
                    if (labels[l.getX() / 32][19].getIcon().equals(wall)) {
                        labels[l.getX() / 32][19].setIcon(null);
                        objectPositioning[l.getX() / 32][0] = "x";
                    }
                }
            } else if (l.getY() == 608) {
                if (i.equals(wall)) {
                    labels[l.getX() / 32][0].setIcon(wall);
                    objectPositioning[l.getX() / 32][0] = "W";
                } else {
                    if (labels[l.getX() / 32][0].getIcon().equals(wall)) {
                        labels[l.getX() / 32][0].setIcon(null);
                        objectPositioning[l.getX() / 32][0] = "x";
                    }
                }
            }
        } catch (NullPointerException e) {
        }
    }

    public void setLabels(JLabel[][] labels) {
        this.labels = labels;
    }

    public void setObjectPositioning(String[][] objectPositioning) {
        this.objectPositioning = objectPositioning;
    }

}
