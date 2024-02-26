package Queries;

public class UserQueries implements IQueries {
    @Override
    public String createTable() {
        return "CREATE TABLE IF NOT EXISTS users ("
                + "user_id SERIAL PRIMARY KEY,"
                + "username VARCHAR(50) NOT NULL,"
                + "age INT NOT NULL,"
                + "balance DOUBLE PRECISION NOT NULL,"
                + "ticket_amount INT NOT NULL,"
                + "premium BOOLEAN DEFAULT FALSE )";
    }

    @Override
    public String add() {
        return "INSERT INTO users (username, age, balance, ticket_amount) VALUES (?, ?, ?, ?)";
    }

    @Override
    public String update() {
        return "UPDATE users SET username = ?, age = ?, balance = ?, ticket_amount = ?, premium = ? WHERE user_id = ?";
    }

    @Override
    public String delete() {
        return "DELETE FROM users WHERE user_id = ?";
    }

    @Override
    public String getAll() {
        return "SELECT * FROM users";
    }

    @Override
    public String getById() {
        return "SELECT * FROM users WHERE user_id = ?";
    }
    public String getUserTicketAmount() {
        return "SELECT ticket_amount FROM users WHERE user_id = ?";
    }
    public String getUserClass() {
        return "SELECT * FROM users WHERE user_id = ?";
    }
    public String topUpBalance() {
        return "UPDATE users SET balance = balance + ? WHERE user_id = ?";
    }
    public String getAllInTable() {
        return "SELECT users.*, COUNT(tickets.ticket_id) AS ticket_count, movies.movie_name " +
               "FROM users " +
               "LEFT JOIN tickets ON users.user_id = tickets.user_id " +
               "LEFT JOIN movies ON tickets.movie_id = movies.movie_id " +
               "GROUP BY users.user_id, movies.movie_name";
    }

    public String getByIdInTable() {
        return "SELECT users.*, COUNT(tickets.ticket_id) AS ticket_count, movies.movie_name " +
               "FROM users " +
               "LEFT JOIN tickets ON users.user_id = tickets.user_id " +
               "LEFT JOIN movies ON tickets.movie_id = movies.movie_id " +
               "WHERE users.user_id = ? " +
               "GROUP BY users.user_id, movies.movie_name";
    }
}