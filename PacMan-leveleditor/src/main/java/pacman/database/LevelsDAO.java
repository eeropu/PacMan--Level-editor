package pacman.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Level data-access-object; This class is used to retrieve and send information
 * from and to the database.
 *
 * @author eerop
 */
public class LevelsDAO {

    private Database db;

    /**
     * Constructor used to define the database object that this class uses.
     */
    public LevelsDAO() {
        try {
            this.db = new Database("jdbc:sqlite::resource:database.db");
        } catch (ClassNotFoundException e) {
        }
    }

    /**
     * This method is used to add levels to the database that contains them.
     *
     * @param name the name of the level.
     * @param level the string that contains the instructions that are used by
     * levelbuilder to build the level.
     * @return returns a string that tells the calling method if something has
     * gone wrong and what's the problem.
     */
    public String add(String name, String level) {
        ArrayList<String> list = getAllLevels();
        for (String c : list) {
            if (c.equals(name)) {
                return "double";
            }
        }

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Levels (name, level, date) VALUES "
                    + "('" + name + "', '" + level + "', CURRENT_TIMESTAMP)");
            stmt.execute();
            conn.close();
            return "ok";
        } catch (SQLException e) {
            return "error";
        }

    }

    /**
     * Used to retrieve all Levels in the database.
     *
     * @return returns an ArrayList containing the names of all levels.
     */
    public ArrayList<String> getAllLevels() {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Levels ORDER BY name ASC;");
            ResultSet set = stmt.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            while (set.next()) {
                list.add(set.getString("name"));
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     *
     * Used to retrieve the string containing levelbuilding information.
     *
     * @param s the name of the level.
     * @return the string containing levelbuilding information.
     */
    public String getLevel(String s) {
        String returnable = "";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT level FROM Levels WHERE name = '" + s + "';");
            ResultSet set = stmt.executeQuery();
            returnable = set.getString("level");
            conn.close();
        } catch (SQLException e) {
        }
        return returnable;
    }

    /**
     * Used to delete a particular level from the database.
     *
     * @param s the name of the level.
     */
    public void delete(String s) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Levels WHERE name = '" + s + "';");
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
        }
    }
}
