package pacman.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public void add(String name, String level) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Levels (name, level, date) VALUES "
                    + "('" + name + "', '" + level + "', CURRENT_TIMESTAMP)");
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
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
            System.out.println("virhe");
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
