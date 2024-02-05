package repositories;

import entities.Movie;
import entities.Ticket;
import entities.User;

import java.sql.*;

public class Repositories {

     private Connection connection;
        public  Repositories(Connection connection) {
            this.connection = connection;
        }
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";
    public Repositories() {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (Exception e) {
                throw new RuntimeException("Something went wrong." + e);
            }
    }

    public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
    public void createUsersTable() throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(Queries.createUsersTable());
            System.out.println("table created");
        }
    }
    public void createMoviesTable() throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(Queries.createMoviesTable());
            System.out.println("table Movies was created");
        }
    }

    public void createTicketsTable() throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(Queries.createTicketTable());
            System.out.println("table Tickets was created");
        }
    }

     public void addUser(User user) throws SQLException{
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.addUser(),
                    Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setInt(2, user.getAge());
                preparedStatement.setDouble(3, user.getBalance());
                preparedStatement.setInt(4, 0);


                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                    }
                    System.out.println("User added successfully!");
                } else {
                    System.out.println("Failed to add user. Please try again.");
                }
            }
        }
        public boolean getUser(int id) throws SQLException{
            boolean isFound = false;
            try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getUser(id));) {
                if (resultSet.next()) {
                    isFound = true;
                    userInfo(new User(resultSet.getInt("user_id"),resultSet.getString("username"),resultSet.getInt("age"),resultSet.getDouble("balance")));
                }else{
                    System.out.println("User wasn't found!");
                }
            }
            return isFound;
        }
        public User getUserClass(int id) throws SQLException{
            try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getUser(id));) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt("user_id"),resultSet.getString("username"),resultSet.getInt("age"),resultSet.getDouble("balance"));
                }else{
                    System.out.println("User wasn't found!");
                }
            }
            return null;
        }
        public Movie getMovieClass(int id) throws SQLException{
            try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getMovie(id));) {
                if (resultSet.next()) {
                    return new Movie(resultSet.getInt("movie_id"),resultSet.getString("movie_name"),resultSet.getDouble("price"));
                }else{
                    System.out.println("Movie wasn't found!");
                }
            }
            return null;
        }
        public Movie getMovieClassByName(String name) throws SQLException{
            try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getMovieByName(name));) {
                if (resultSet.next()) {
                    return new Movie(resultSet.getInt("movie_id"),resultSet.getString("movie_name"),resultSet.getDouble("price"));
                }else{
                    System.out.println("Movie wasn't found!");
                }
            }
            return null;
        }
        public Ticket getTicketClass(int id) throws SQLException{
            try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getTicket(id));) {
                if (resultSet.next()) {
                    return new Ticket(resultSet.getInt("ticket_id"),resultSet.getString("movie_name"),resultSet.getDouble("price"),resultSet.getString("time"),resultSet.getInt("ticketId"));
                }else{
                    System.out.println("User wasn't found!");
                }
            }
            return null;
        }

    public void updateUser(User user) throws SQLException {
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.updateUser(user.getId()));) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setInt(2, user.getAge());
                preparedStatement.setDouble(3, user.getBalance());
                preparedStatement.setInt(4, user.getTicketArraySize());
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected > 0) {
                    System.out.println("User updated successfully!");
                } else {
                    System.out.println("Failed to update user.");
                }
            }
    }
    public void deleteUser (int id) throws  SQLException{
        try (Statement statement = getConnection().createStatement();){
            int rowsAffected = statement.executeUpdate(Queries.deleteUser(id));
            if (rowsAffected > 0) {
                System.out.println("Deleted successfully");
            } else {
                System.out.println("Something  went wrong");
            }
        }
    }
    public void getAllUsers() throws SQLException {
            try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getAllUsers());) {
                while (resultSet.next()) {
                    System.out.println();
                    userInfo(new User(resultSet.getInt("user_id"),resultSet.getString("username"),resultSet.getInt("age"),resultSet.getDouble("balance")));
                }
            }
    }
        public void userInfo(User user){
            System.out.println("ID:       " + user.getId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Age:      " + user.getAge());
            System.out.println("Balance:  " + user.getBalance());
            System.out.println("Tickets:  ");
            user.getAllUserTickets();
        }
        public void movieInfo(Movie movie){
            System.out.println("Movie ID:  " + movie.getMovieId());
            System.out.println("Movie name: " + movie.getMovieName());
            System.out.println("   Price:   " + movie.getPrice());
        }

         public void ticketInfo(Ticket ticket){
            System.out.println("Ticket ID:  " + ticket.getMovieId());
            System.out.println("Ticket Movie name: " + ticket.getMovieName());
            System.out.println("   Price:   " + ticket.getPrice());
            System.out.println("   Time:   " + ticket.getPrice());
            System.out.println("   Amount:   " + ticket.getTicketAmount());
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
        public int getTicketAmountForAll(int id) throws SQLException{
            try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getTicketAmountForAll(id));) {
                if (resultSet.next()) {
                    return resultSet.getInt("ticketAmount");
                }else{
                    System.out.println("Ticket wasn't found!");
                }
            }
            return 0;
        }
        public void updateTicketAmountForAll(int id,int toBuy) throws SQLException {
                try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.updateTicketAmountForAll(id));) {
                        preparedStatement.setInt(1, getTicketAmountForAll(id)-toBuy);
                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Ticket amount changed successfully!");

                        } else {
                            System.out.println("Failed to change ticket amount.");
                        }
                }
        }

        public void addTicketToTable(Ticket ticket) throws SQLException{
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.addTicket(),
                    Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1,ticket.getMovieName());
                preparedStatement.setDouble(2, ticket.getPrice());
                preparedStatement.setString(3, ticket.getTime());
                preparedStatement.setInt(4, ticket.getTicketAmount());


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

        public void getAllTickets() throws SQLException {
            try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getAllTickets());) {
                while (resultSet.next()) {
                    System.out.println();
                    ticketInfo(new Ticket(resultSet.getInt("ticket_id"),resultSet.getString("movie_name"),resultSet.getDouble("balance"),resultSet.getString("time")));
                }
            }
        }


        public void addMovie(Movie movie) throws SQLException{
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.addMovie(),
                    Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, movie.getMovieName());
                preparedStatement.setDouble(2, movie.getPrice());


                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        movie.setGetMovieId(generatedKeys.getInt(1));
                    }
                    System.out.println("Movie added successfully!");
                } else {
                    System.out.println("Failed to add movie. Please try again.");
                }
            }
        }
        public boolean getMovie(int id) throws SQLException{
            boolean isFound = false;
            try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getMovie(id));) {
                if (resultSet.next()) {
                    isFound = true;
                    movieInfo(new Movie(resultSet.getInt("movie_id"),resultSet.getString("movie_name"),resultSet.getDouble("price")));
                }else{
                    System.out.println("Movie wasn't found!");
                }
            }
            return isFound;
        }

        public boolean getMovieByName(String name) throws SQLException{
            boolean isFound = false;
            try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getMovieByName(name));) {
                if (resultSet.next()) {
                    isFound = true;
                    movieInfo(new Movie(resultSet.getInt("movie_id"),resultSet.getString("movie_name"),resultSet.getDouble("price")));
                }else{
                    System.out.println("Movie wasn't found!");
                }
            }
            return isFound;
        }

        public void updateMovie(Movie movie) throws SQLException {
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.updateMovie(movie.getMovieId()));) {
                preparedStatement.setString(1, movie.getMovieName());
                preparedStatement.setDouble(2, movie.getPrice());
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected > 0) {
                    System.out.println("Movie updated successfully!");
                } else {
                    System.out.println("Failed to update movie.");
                }
            }
        }
        public void deleteMovie(int id) throws  SQLException{
            try (Statement statement = getConnection().createStatement();){
                int rowsAffected = statement.executeUpdate(Queries.deleteMovie(id));
                if (rowsAffected > 0) {
                    System.out.println("Deleted successfully");
                } else {
                    System.out.println("Something  went wrong");
                }
            }
        }
         public boolean getMovieByTime(String time) throws SQLException{
            boolean isFound = false;
            try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getMovieByTime(time));) {
                if (resultSet.next()) {
                    isFound = true;
                    movieInfo(new Movie(resultSet.getInt("movie_id"),resultSet.getString("movie_name"),resultSet.getDouble("price")));
                }else{
                    System.out.println("There is not any movie at this time!");
                }
            }
            return isFound;
        }

        public void getAllMovies() throws SQLException {
            try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getAllMovies());) {
                while (resultSet.next()) {
                    System.out.println();
                    movieInfo(new Movie(resultSet.getInt("movie_id"),resultSet.getString("movie_name"),resultSet.getDouble("price")));
                }
            }
        }

         public double getMoviePrice (String name) throws SQLException{
            try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getMoviePrice(name));) {
                if (resultSet.next()) {
                    Movie movie = new Movie(resultSet.getInt("movie_id"),resultSet.getString("movie_name"),resultSet.getDouble("price"));
                    movieInfo(new Movie(resultSet.getInt("movie_id"),resultSet.getString("movie_name"),resultSet.getDouble("price")));
                    return movie.getPrice();
                }else{
                    System.out.println("Movie wasn't found!");
                }
            }
            return 0  ;
        }
}
