package pacman.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
     * Constructor where the Database is defined.
     */
    public HighscoresDAO() {
        try {
            this.db = new Database("jdbc:sqlite:database.db");
        } catch (ClassNotFoundException e) {
        }
    }

    /**
     * This method is used to add information to the database.
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

    /**
     * Used to get the highscores of a level.
     *
     * @param level the name of a level.
     * @return a two dimensional array of Strings that contains the names,
     * scores and dates of a levels highscores.
     */
    public String[][] getScores(String level) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM "
                    + "Highscores WHERE level = '" + level + "' ORDER BY score DESC;");
            ResultSet set = stmt.executeQuery();
            ArrayList<String> names = new ArrayList<>();
            ArrayList<String> score = new ArrayList<>();
            ArrayList<String> dates = new ArrayList<>();
            while (set.next()) {
                names.add(set.getString("name"));
                score.add(set.getString("score"));
                dates.add(set.getString("date"));
            }

            String[][] r = new String[3][names.size()];
            for (int i = 0; i < names.size(); i++) {
                r[0][i] = names.get(i);
                r[1][i] = score.get(i);
                r[2][i] = dates.get(i);
            }
            conn.close();
            return r;
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Used to delete all scoreinformations of a level.
     *
     * @param level name of a level
     */
    public void delete(String level) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Highscores WHERE level = '" + level + "';");
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Deleting failed, please try again");
        }
    }

}
