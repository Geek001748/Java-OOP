package Queries;

public class UserQueries implements IQueries {
    @Override
    public String createTable() {
        return "CREATE TABLE IF NOT EXISTS users ("
                + "user_id SERIAL PRIMARY KEY,"
                + "username VARCHAR(50) NOT NULL,"
                + "age INT NOT NULL,"
                + "balance DOUBLE PRECISION NOT NULL,"
                + "ticket_amount INT NOT NULL )";
    }

    @Override
    public String add() {
        return "INSERT INTO users (username, age, balance, ticket_amount) VALUES (?, ?, ?, ?)";
    }

    @Override
    public String update() {
        return "UPDATE users SET username = ?, age = ?, balance = ?, ticket_amount = ? WHERE user_id = ?";
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
    public String updateUserBalance() {
        return "UPDATE users SET balance = ? WHERE user_id = ?";
    }
}