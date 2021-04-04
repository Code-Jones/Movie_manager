package sait.mms.drivers;

import sait.mms.contracts.DatabaseDriver;

import java.sql.*;

/**
 * Class description: Driver for connecting to and accessing a MySQL or MariaDB database.
 *
 * @author Nick Hamnett
 */
public class MariaDBDriver implements DatabaseDriver {
    private static final String SERVER = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE = "cprg251";
    private static final String USERNAME = "cprg251";
    private static final String PASSWORD = "password";

    private Connection connection = null;

    /**
     * Initializes the newly created JdbcDriver.
     */
    public MariaDBDriver() {
    }

    /* (non-Javadoc)
     * @see drivers.DatabaseDriver#connect()
     */
    @Override
    public void connect() throws SQLException {
        String dsn = this.getDsn();
        connection = DriverManager.getConnection(dsn);
    }

    /**
     * Gets the data source name to connect to the database.
     *
     * @return DSN
     */
    private String getDsn() {
        // Data source name is formatted as follows (for MariaDB): jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword

        return String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", SERVER, PORT, DATABASE, USERNAME, PASSWORD);
    }

    /* (non-Javadoc)
     * @see drivers.DatabaseDriver#get(java.lang.String)
     */
    @Override
    public ResultSet get(String query) throws SQLException {
        Statement statement = connection.createStatement();

        return statement.executeQuery(query);
    }

    /* (non-Javadoc)
     * @see drivers.DatabaseDriver#update(java.lang.String)
     */
    @Override
    public int update(String query) throws SQLException {
        Statement statement = connection.createStatement();

        return statement.executeUpdate(query);
    }

    /* (non-Javadoc)
     * @see drivers.DatabaseDriver#disconnect()
     */
    @Override
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed())
            connection.close();
    }
}
