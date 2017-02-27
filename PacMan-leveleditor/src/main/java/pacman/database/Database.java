package pacman.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * This class is responsible for creating the connection to databases.
 *
 * @author eerop.
 */
public class Database {

    private String databaseAddress;

    /**
     * Constructor that defines the address to the database.
     *
     * @param databaseAddress the path that is used by jdbc-blugin to create a
     * connection to database.
     * @throws ClassNotFoundException needed exception throw.
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    /**
     * This method returns the connection to databases allowing other classes to
     * access the databases contents.
     *
     * @return returns the connection to the database
     * @throws SQLException needed incase connecting to database fails.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

}
