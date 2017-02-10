package pacman.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eerop
 *
 * Level data-access-object. This class is used to retrieve and send information
 * from and to the database.
 */
public class LevelsDAO {

    private Database db;
    private String orderby;

    public LevelsDAO() {
        try {
            this.db = new Database("jdbc:sqlite:database.db");
        } catch (ClassNotFoundException e) {
        }
        this.orderby = "name DESC";
    }

    public String add(String name, String level, boolean check) {
        if (check) {
            ArrayList<String> list = getAllLevels();
            for (String c : list) {
                if (c.equals(name)) {
                    return "double";
                }
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

    public ArrayList<String> getAllLevels() {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Levels ORDER BY " + orderby);
            ResultSet set = stmt.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            while (set.next()) {
                list.add(set.getString("name"));
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public String getLevel(String s) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT level FROM Levels WHERE name = '" + s + "';");
            ResultSet set = stmt.executeQuery();
            return set.getString("level");
        } catch (SQLException e) {
            return "";
        }
    }
}
