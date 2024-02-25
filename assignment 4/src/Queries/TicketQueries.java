package Queries;

public class TicketQueries implements IQueries {

    @Override
    public String createTable() {
        return "CREATE TABLE IF NOT EXISTS tickets ("
                + "ticket_id SERIAL PRIMARY KEY,"
                + "ticket_price DOUBLE PRECISION NOT NULL,"
                + "user_id INT,"
                + "movie_id INT,"
                + "FOREIGN KEY (user_id) REFERENCES users(user_id),"
                + "FOREIGN KEY (movie_id) REFERENCES movies(movie_id)"
                + ")";
    }

    @Override
    public String add() {
        return "INSERT INTO tickets (ticket_price, movie_id) VALUES (?, ?)";
    }
    public String addToUser() {
        return "UPDATE users SET ticket_amount = ? WHERE user_id = ?";
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
