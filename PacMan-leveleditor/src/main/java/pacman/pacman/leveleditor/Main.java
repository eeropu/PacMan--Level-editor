package pacman.pacman.leveleditor;

import javax.swing.SwingUtilities;

/**
 * Starts the program.
 *
 * @author eerop
 */
public class Main {

    /**
     * This programs main method that starts the program.
     *
     * @param args standard parameter for the main-method
     */
    public static void main(String[] args) {
        WindowHandler wh = new WindowHandler();
        SwingUtilities.invokeLater(wh);
    }

}
