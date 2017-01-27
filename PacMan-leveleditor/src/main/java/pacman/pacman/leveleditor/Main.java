package pacman.pacman.leveleditor;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        WindowHandler wh = new WindowHandler();
        SwingUtilities.invokeLater(wh);
    }

}
