package repositories;

import Queries.MovieQueries;
import Queries.TicketQueries;
import data.DB;
import data.IDB;
import entities.Ticket;
import repositories.interfaces.ITicketRepository;

import java.sql.*;

public class TicketRepository implements ITicketRepository {
    private IDB db = new DB();
    private final TicketQueries q = new TicketQueries();
    private final MovieQueries mq = new MovieQueries();

    public TicketRepository(IDB db) {
        this.db = db;
    }

    public TicketRepository() {

    }

    // Example code to add a ticket while ensuring the movie_id exists
    @Override
    public void addTicket(Ticket ticket) {
        // Check if movieId exists in movies table
        if (movieExists(ticket.getMovieId())) {
            // Proceed with ticket insertion
            try (Connection conn = db.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(q.add())) {
                stmt.setDouble(1, ticket.getTicketPrice());
                stmt.setInt(2, ticket.getMovieId());
                // Execute the insert query
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Ticket added successfully!");
                } else {
                    System.out.println("Failed to add ticket.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Error: Movie with ID " + ticket.getMovieId() + " does not exist.");
        }
    }

    // Method to check if movie exists
    public boolean movieExists(int movieId) {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(mq.getByMovieID())) {
            stmt.setInt(1, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void addTicketToUser(Ticket ticket) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.addToUser(),
                     Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, ticket.getTicketPrice());
            stmt.setInt(2, ticket.getUserId());
            stmt.setInt(3, ticket.getMovieId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
//                    ticket.setTicketId(generatedKeys.getInt(1));
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
                    Ticket ticket = new Ticket.TicketBuilder()
                            .ticketId(rs.getInt("ticket_id"))
                            .ticketPrice(rs.getDouble("ticket_price"))
                            .build();
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
                    Ticket ticket = new Ticket.TicketBuilder()
                            .ticketId(rs.getInt("ticket_id"))
                            .ticketPrice(rs.getDouble("ticket_price"))
                            .build();
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
                    return new Ticket.TicketBuilder()
                            .ticketId(rs.getInt("ticket_id"))
                            .ticketPrice(rs.getDouble("ticket_price"))
                            .build();
                } else {
                    System.out.println("Ticket wasn't found!");
                }
            }
        }
        return null;
    }
}
