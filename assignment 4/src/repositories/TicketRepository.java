package repositories;

import Queries.TicketQueries;
import data.IDB;
import entities.Ticket;
import repositories.interfaces.ITicketRepository;

import java.sql.*;

public class TicketRepository implements ITicketRepository {
    private final IDB db;
    private TicketQueries q;

    public TicketRepository(IDB db) {
        this.db = db;
    }

    @Override
    public void addTicket(Ticket ticket) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.add(),
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, ticket.getTicketPrice());
            stmt.executeUpdate();

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    ticket.setTicketId(generatedKeys.getInt(1));
                }
                System.out.println("Ticket added successfully!");
            } else {
                System.out.println("Failed to ticket user. Please try again.");
            }
        }
    }
    @Override
    public boolean getTicket(int id) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getById())) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Ticket ticket = new Ticket(
                            rs.getInt("ticket_id"),
                            rs.getDouble("ticket_price")
                    );
                    System.out.println(ticket.toString());
                    return true;
                } else {
                    System.out.println("Ticket wasn't found!");
                }
            }
        }
        return false;
    }

    @Override
    public void updateTicket(Ticket ticket) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.update())) {
            stmt.setDouble(1, ticket.getTicketPrice());
            stmt.setInt(2, ticket.getTicketId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted successfully");
            } else {
                System.out.println("Something went wrong");
            }
        }
    }

    @Override
    public void deleteTicket(int id) throws SQLException {
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
    public void getAllTickets() throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getAll())) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ticket ticket = new Ticket(
                            rs.getInt("ticket_id"),
                            rs.getDouble("ticket_price"));
                    System.out.println(ticket.toString());
                }
            }
        }
    }
    @Override
    public Ticket getTicketClass(int id) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getById())) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Ticket(
                            rs.getInt("ticket_id"),
                            rs.getInt("ticket_price")
                    );
                } else {
                    System.out.println("Ticket wasn't found!");
                }
            }
        }
        return null;
    }
}
