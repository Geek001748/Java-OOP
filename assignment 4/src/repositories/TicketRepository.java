package repositories;

import Queries.TicketQueries;
import data.IDB;
import entities.Ticket;
import repositories.interfaces.ITicketRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            stmt.setString(1, ticket.getMovieName());
            stmt.setString(2, ticket.getMovieGenre());
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
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tickets WHERE ticket_id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public void updateTicket(Ticket ticket) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE tickets SET price = ?, time = ?, ticket_amount = ? WHERE ticket_id = ?")) {
            stmt.setDouble(1, ticket.getTicketPrice());
            stmt.setString(2, ticket.getTime());
            stmt.setInt(3, ticket.getTicketAmount());
            stmt.setInt(4, ticket.getTicketId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteTicket(int id) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM tickets WHERE ticket_id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void getAllTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tickets");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                tickets.add(new Ticket(
                        rs.getInt("ticket_id"),
                        rs.getString("movie_name"),
                        rs.getString("time"),
                        rs.getDouble("price"),
                        rs.getInt("ticket_amount")
                ));
            }
        }
        return tickets;
    }
}
