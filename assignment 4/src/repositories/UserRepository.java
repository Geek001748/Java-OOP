package repositories;

import Queries.UserQueries;
import data.IDB;
import entities.User;
import repositories.interfaces.IUserRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;
    private UserQueries q;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public void addUser(User user) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.add())) {
            stmt.setString(1, user.getUsername());
            stmt.setInt(2, user.getAge());
            stmt.setDouble(3, user.getBalance());
            stmt.setInt(4, user.getTicketAmount());
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean getUser(int id) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getById())) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public int getUserTicketAmount(int userId) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getUserTicketAmount())) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ticket_amount");
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
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.delete())) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getAll());
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getInt("age"),
                        rs.getDouble("balance"),
                        rs.getInt("ticket_amount")
                ));
            }
        }
        return users;
    }
}
