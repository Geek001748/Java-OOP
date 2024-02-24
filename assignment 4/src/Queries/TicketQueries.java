package Queries;

public class TicketQueries implements IQueries {
    @Override
    public String createTable() {
        return "CREATE TABLE IF NOT EXISTS tickets ("
                + "ticket_id SERIAL PRIMARY KEY,"
                + "ticket_price DOUBLE PRECISION  NOT NULL)";
    }

    @Override
    public String add() {
        return "INSERT INTO tickets (ticket_price) VALUES (?)";
    }

    @Override
    public String update() {
        return "UPDATE tickets SET ticket_price = ? WHERE ticket_id = ?";
    }

    @Override
    public String delete() {
        return "DELETE FROM tickets WHERE ticket_id = ?";
    }

    @Override
    public String getAll() {
        return "SELECT * FROM tickets";
    }

    @Override
    public String getById() {
        return "SELECT * FROM tickets WHERE ticket_id = ?";
    }
}