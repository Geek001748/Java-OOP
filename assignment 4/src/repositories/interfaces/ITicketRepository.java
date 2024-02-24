package repositories.interfaces;

import entities.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface ITicketRepository {
    void addTicket(Ticket ticket) throws SQLException;
    boolean getTicket(int id) throws SQLException;
    void updateTicket(Ticket ticket) throws SQLException;
    void deleteTicket(int id) throws SQLException;
    void getAllTickets() throws SQLException;
}