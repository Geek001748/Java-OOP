package repositories;

import entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTable extends  SQLConnection implements TableManagement {
    @Override
    public void createTable() throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(Queries.createUsersTable());
            System.out.println("Table Users created");
        }
    }

    @Override
    public void addToTable(Object object) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.addUser(),
                Statement.RETURN_GENERATED_KEYS)) {
            User user = (User) object;

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setDouble(3, user.getBalance());
            preparedStatement.setInt(4, 0);


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

    @Override
    public boolean getObjectFromTable(int id) throws SQLException {
        boolean isFound = false;
        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getUser(id));) {
            if (resultSet.next()) {
                isFound = true;
                objectInfo(new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getInt("age"), resultSet.getDouble("balance")));
            } else {
                System.out.println("User wasn't found!");
            }
        }
        return isFound;
    }

    @Override
    public Object getObjectClass(int id) throws SQLException {
        try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getUser(id));) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt("id"),resultSet.getString("username"),resultSet.getInt("age"),resultSet.getDouble("balance"),resultSet.getInt("ticketAmount"));
                }else{
                    System.out.println("User wasn't found!");
                }
            }
            return null;
    }
    @Override
    public void updateObjectTable(Object object) throws SQLException {
        User user = (User)object;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.updateUser(user.getId()));) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setInt(2, user.getAge());
                preparedStatement.setDouble(3, user.getBalance());
                preparedStatement.setInt(4, user.getTicketArraySize());
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected > 0) {
                    System.out.println("User updated successfully!");
                } else {
                    System.out.println("Failed to update user.");
                }
        }
    }
    @Override
    public void deleteObjectFromTable (int id) throws  SQLException{
        try (Statement statement = getConnection().createStatement();){
            int rowsAffected = statement.executeUpdate(Queries.deleteUser(id));
            if (rowsAffected > 0) {
                System.out.println("Deleted successfully");
            } else {
                System.out.println("Something  went wrong");
            }
        }
    }
    @Override
    public void getAllObjectsFromTable() throws SQLException {
        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getAllUsers());) {
            while (resultSet.next()) {
                    System.out.println();
                    objectInfo(new User(resultSet.getInt("id"),resultSet.getString("username"),resultSet.getInt("age"),resultSet.getDouble("balance")));
            }
        }
    }
    @Override
    public void objectInfo(Object object){
        User user = (User)object;
            System.out.println("ID:       " + user.getId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Age:      " + user.getAge());
            System.out.println("Balance:  " + user.getBalance());
            System.out.println("Tickets:  ");
            user.getAllUserTickets();
        }

}
