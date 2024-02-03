package repositories;

import entities.Ticket;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class TicketTable extends SQLConnection implements TableManagement{
    @Override
    public void createTable() throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(Queries.createTicketTable());
            System.out.println("Table Tickets was created");
        }
    }

    @Override
    public void updateObjectTable(Object object) throws SQLException {

    }

    @Override
    public void deleteObjectFromTable(int id) throws SQLException {

    }

    @Override
    public boolean getObjectFromTable(int id) throws SQLException {
        return false;
    }

    @Override
    public void addToTable(Object object) throws SQLException {
        Ticket ticket = (Ticket)object;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.addTicket(),
                    Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, ticket.getMovieName());
                preparedStatement.setDouble(2, ticket.getPrice());
                preparedStatement.setString(3, ticket.getTime());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

                    if (generatedKeys.next()) {
                        ticket.setTicketId(generatedKeys.getInt(1));
                    }
                    System.out.println("Ticket added successfully!");
                } else {
                    System.out.println("Failed to add user. Please try again.");
                }
        }
    }

    public void addTicket(Ticket ticket, int id) throws SQLException{

    }

    @Override
    public void getAllObjectsFromTable() throws SQLException {
     try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getAllTickets());) {
                while (resultSet.next()) {
                    System.out.println();
                    objectInfo(new Ticket(resultSet.getInt("ticketId"),resultSet.getString("movieName"),resultSet.getDouble("balance"), "ticketId"),resultSet.getString("time")));
                }
            }
    }

    @Override
    public Object getObjectClass(int id) throws SQLException {
        try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getTicket(id));) {
                if (resultSet.next()) {
                    return new Ticket(resultSet.getInt("id"),resultSet.getString("movieName"),resultSet.getDouble("price"),resultSet.getString("time"),resultSet.getInt("ticketId"));
                }else{
                    System.out.println("User wasn't found!");
                }
            }
        return null;
    }
    @Override
    public void objectInfo(Object object){
        Ticket ticket = (Ticket)object;
        System.out.println("Ticket ID:  " + ticket.getMovieId());
        System.out.println("Ticket Movie name: " + ticket.getMovieName());
        System.out.println("   Price:   " + ticket.getPrice());
        System.out.println("   Time:   " + ticket.getPrice());
    }
    public void ticketAmount(User user) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.updateTicketAmount(user.getId()));) {
                preparedStatement.setInt(1, user.getTicketArraySize());
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected > 0) {
                    System.out.println("Ticket amount changed successfully!");

                } else {
                    System.out.println("Failed to change ticket amount.");
                }
            }
        }
}
