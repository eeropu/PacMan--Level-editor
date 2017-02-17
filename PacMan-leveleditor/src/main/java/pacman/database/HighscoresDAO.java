package pacman.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * This class is used to get access to the database that contains the highscores
 * and to modify it's contents.
 *
 * @author eerop
 */
public class HighscoresDAO {

    private Database db;

    /**
     * Constructor where the Database is defined
     */
    public HighscoresDAO() {
        try {
            this.db = new Database("jdbc:sqlite:database.db");
        } catch (ClassNotFoundException e) {
        }
    }

    /**
     * This method is used to add information to the database
     *
     * @param name the name of the player that completed the level
     * @param points the score that player got in the previously played level
     * @param level the name of the level
     */
    public void add(String name, int points, String level) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Highscores VALUES ('" + name
                    + "', " + points + ", CURRENT_TIMESTAMP, '" + level + "');");
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Score submitting failed. Please try again");
        }
    }

}
