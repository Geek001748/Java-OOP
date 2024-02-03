package repositories;

import java.sql.*;

public class SQLConnection {

     private java.sql.Connection connection;
     public SQLConnection(Connection connection) {
            this.connection = connection;
        }
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";
    public SQLConnection() {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (Exception e) {
                throw new RuntimeException("Something went wrong." + e);
            }
    }
    public java.sql.Connection getConnection() throws SQLException {
                return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
