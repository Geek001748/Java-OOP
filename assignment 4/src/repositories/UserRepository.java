package repositories;

import Queries.TicketQueries;
import Queries.UserQueries;
import data.IDB;
import entities.Ticket;
import entities.userFunc.User;
import repositories.interfaces.IUserRepository;

import java.sql.*;

public class UserRepository implements IUserRepository {
    private final IDB db;
    private final UserQueries q;
    private final TicketQueries tq = new TicketQueries();

    public UserRepository(IDB db) {
        this.db = db;
        this.q = new UserQueries(); // Initialize UserQueries
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
             PreparedStatement stmt = conn.prepareStatement(q.getById())) {
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
                System.out.println("Something went wrong");
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
    public void topUpBalance(double amount, int userId) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.topUpBalance())) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, userId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("User balance topped up successfully!");
            } else {
                System.out.println("Failed to top up user balance.");
            }
        }
    }

    public void buyTicket(int amount, Ticket ticket) throws SQLException {
    double totalPrice = ticket.getTicketPrice() * amount;

    // Get user's balance
    User user = getUserClass(ticket.getUserId());
    double balance = user.getBalance();

    // Check if user has sufficient balance
    if (balance >= totalPrice) {
        try (Connection conn = db.getConnection();
             PreparedStatement buyStmt = conn.prepareStatement(tq.addToUser());
             PreparedStatement updateStmt = conn.prepareStatement(q.update())) {

            // Proceed with the purchase
            buyStmt.setInt(1, amount + user.getTicketAmount());
            buyStmt.setInt(2, ticket.getUserId());
            int rows = buyStmt.executeUpdate();

            if (rows > 0) {
                // Update user's balance and ticket count
                user.setBalance(balance - totalPrice);
                user.setTicketAmount(user.getTicketAmount() + amount);
                updateStmt.setString(1, user.getUsername());
                updateStmt.setInt(2, user.getAge());
                updateStmt.setDouble(3, user.getBalance());
                updateStmt.setInt(4, user.getTicketAmount());
                updateStmt.setInt(5, user.getId());
                updateStmt.executeUpdate();

                System.out.println("Ticket purchased successfully!");
            } else {
                System.out.println("Failed to purchase ticket.");
            }
        }
    } else {
        System.out.println("Insufficient balance to buy the tickets.");
    }
}

    public void getAllUsersInTable() throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getAllInTable());
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = extractUserFromResultSet(rs);
                int ticketCount = rs.getInt("ticket_count");
                String movieName = rs.getString("movie_name");
                System.out.println(user.toString() + " | Ticket Count: " + ticketCount + " | Movie: " + movieName);
            }
        }
    }

    public void getUserByIdInTable(int userId) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getByIdInTable())) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = extractUserFromResultSet(rs);
                    int ticketCount = rs.getInt("ticket_count");
                    String movieName = rs.getString("movie_name");
                    System.out.println(user.toString() + " | Ticket Count: " + ticketCount + " | Movie: " + movieName);
                } else {
                    System.out.println("User not found!");
                }
            }
        }
    }
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("user_id"),
                rs.getString("username"),
                rs.getInt("age"),
                rs.getDouble("balance"),
                rs.getInt("ticket_amount"));
    }
}
