package pacman.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class HighscoresDAO {
    
    private Database db;

    public HighscoresDAO() {
        try{
            this.db = new Database("jdbc:sqlite:database.db");
        } catch (ClassNotFoundException e){
        }
    }
    
    public void add(String name, int points, String level){
        try{
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Highscores VALUES ('" + name
            + "', " + points + ", CURRENT_TIMESTAMP, '" + level + "');");
            stmt.execute();
            conn.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Score submitting failed. Please try again");
        }
    }
    
}
