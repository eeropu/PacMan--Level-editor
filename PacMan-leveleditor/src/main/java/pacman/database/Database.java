package pacman.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author eerop This class is responsible for creating the connection to
 * databases.
 */
public class Database {

    private String databaseAddress;

    /**
     * Constructor that defines the address to the database.
     * 
     * @param databaseAddress
     * @throws ClassNotFoundException
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    /**
     * This method returns the connection to databases allowing other classes to
     * access the databases contents.
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

}
