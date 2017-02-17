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

    public LevelsDAO() {
        try {
            this.db = new Database("jdbc:sqlite:database.db");
        } catch (ClassNotFoundException e) {
        }
    }

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
    
    public void delete(String s){
        try{
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Levels WHERE name = '" + s + "';");
            stmt.execute();
            conn.close();
        } catch (SQLException e){
        }
    }
}
