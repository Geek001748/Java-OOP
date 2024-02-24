package repositories;

import Queries.UserQueries;
import data.IDB;
import entities.User;
import repositories.interfaces.IUserRepository;

import java.sql.*;

public class UserRepository implements IUserRepository {
    private final IDB db;
    private UserQueries q;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public void addUser(User user) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.add(),
                     Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setInt(2, user.getAge());
            stmt.setDouble(3, user.getBalance());
            stmt.setInt(4, user.getTicketAmount());
            stmt.executeUpdate();

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
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
    public boolean getUser(int id) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getById())) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getInt("age"),
                            rs.getDouble("balance"),
                            rs.getInt("ticketAmount")
                    );
                    System.out.println(user.toString());
                    return true;
                } else {
                    System.out.println("User wasn't found!");
                }
            }
        }
        return false;
    }


    @Override
    public int getUserTicketAmount(int userId) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getUserTicketAmount())) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ticket_amount");
                } else {
                    System.out.println("User wasn't found!");
                }
            }
        }
        return 0;
    }
    @Override
    public User getUserClass(int id) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getUserClass())) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getInt("age"),
                            rs.getDouble("balance"),
                            rs.getInt("ticket_amount")
                    );
                } else {
                    System.out.println("User wasn't found!");
                }
            }
        }
        return null;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.update())) {
            stmt.setString(1, user.getUsername());
            stmt.setInt(2, user.getAge());
            stmt.setDouble(3, user.getBalance());
            stmt.setInt(4, user.getTicketAmount());
            stmt.setInt(5, user.getId());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("User updated successfully!");
            } else {
                System.out.println("Failed to update user.");
            }
        }
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.delete())) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted successfully");
            } else {
                System.out.println("Something  went wrong");
            }
        }
    }

    @Override
    public void getAllUsers() throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getAll())) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getInt("age"),
                            rs.getDouble("balance"),
                            rs.getInt("ticket_amount"));
                    System.out.println(user.toString());
                }
            }
        }
    }
}
