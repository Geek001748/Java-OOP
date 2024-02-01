package repositories;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.util.LinkedList;

import entities.User;

public class UserRepository {
        private Connection connection;
        public UserRepository(Connection connection) {
            this.connection = connection;
        }
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";
    public UserRepository() {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (Exception e) {
                throw new RuntimeException("Something went wrong." + e);
            }
    }

    public Connection getConnection() throws SQLException{
                return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
        public void createUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = Queries.createUsersTable();

            statement.executeUpdate(createTableQuery);
        }
    }
        public void addUser(User user) throws SQLException{
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.addUser(),
                    Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setInt(2, user.getAge());
                preparedStatement.setDouble(3, user.getBalance());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                    }
                    System.out.println("User added successfully!");
                } else {
                    System.out.println("Failed to add user. Please try again.");
                }
            }
        }
        public boolean getUser(int id) throws SQLException{
            boolean isFound = false;
            try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getUser(id));) {
                if (resultSet.next()) {
                    isFound = true;
                    userInfo(new User(resultSet.getInt("id"),resultSet.getString("username"),resultSet.getInt("age"),resultSet.getDouble("balance")));
                }else{
                    System.out.println("User wasn't found!");
                }
            }
            return isFound;
        }

    public void updateUser(User user) throws SQLException {
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.updateUser(user.getId()));) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setInt(2, user.getAge());
                preparedStatement.setDouble(3, user.getBalance());
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected > 0) {
                    System.out.println("User updated successfully!");
                } else {
                    System.out.println("Failed to update user.");
                }
            }
    }
    public void deleteUser (int id) throws  SQLException{
        try (Statement statement = getConnection().createStatement();){
            int rowsAffected = statement.executeUpdate(Queries.updateUser(id));
            if (rowsAffected > 0) {
                System.out.println("Deleted successfully");
            } else {
                System.out.println("Something  went wrong");
            }
        }
    }
    public void getAllUsers() throws SQLException {
            try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getAllUsers());) {
                while (resultSet.next()) {
                    System.out.println();
                    userInfo(new User(resultSet.getInt("id"),resultSet.getString("username"),resultSet.getInt("age"),resultSet.getDouble("balance")));
                }
            }
        }
        public void userInfo(User user){
            System.out.println("ID:       " + user.getId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Age:      " + user.getAge());
            System.out.println("Balance:  " + user.getBalance());
        }
    }