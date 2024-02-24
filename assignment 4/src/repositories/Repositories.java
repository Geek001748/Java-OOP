//package repositories;
//
//import entities.Movie;
//import entities.Ticket;
//import entities.User;
//
//import java.sql.*;
//
//public class Repositories {
//
//    private Connection connection;
//
//    public Repositories(Connection connection) {
//        this.connection = connection;
//    }
//
//    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "1234";
//
//    public Repositories() {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (Exception e) {
//            throw new RuntimeException("Something went wrong." + e);
//        }
//    }
//
//    public Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
//    }
//
//    public void createUsersTable() throws SQLException {
//        try (Statement statement = getConnection().createStatement()) {
//            statement.executeUpdate(Queries.createUsersTable());
//            System.out.println("table created");
//        }
//    }
//
//    public void createMoviesTable() throws SQLException {
//        try (Statement statement = getConnection().createStatement()) {
//            statement.executeUpdate(Queries.createMoviesTable());
//            System.out.println("table Movies was created");
//        }
//    }
//
//    public void createTicketsTable() throws SQLException {
//        try (Statement statement = getConnection().createStatement()) {
//            statement.executeUpdate(Queries.createTicketTable());
//            System.out.println("table Tickets was created");
//        }
//    }
//
//    public void addUser(User user) throws SQLException {
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.addUser(),
//                Statement.RETURN_GENERATED_KEYS)) {
//
//            preparedStatement.setString(1, user.getUsername());
//            preparedStatement.setInt(2, user.getAge());
//            preparedStatement.setDouble(3, user.getBalance());
//            preparedStatement.setInt(4, 0);
//
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    user.setId(generatedKeys.getInt(1));
//                }
//                System.out.println("User added successfully!");
//            } else {
//                System.out.println("Failed to add user. Please try again.");
//            }
//        }
//    }
//
//    public boolean getUser(int id) throws SQLException {
//        boolean isFound = false;
//        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getUser(id));) {
//            if (resultSet.next()) {
//                isFound = true;
//                userInfo(new User(resultSet.getInt("user_id"), resultSet.getString("username"), resultSet.getInt("age"), resultSet.getDouble("balance")));
//            } else {
//                System.out.println("User wasn't found!");
//            }
//        }
//        return isFound;
//    }
//
//    public int getUserTicketAmount(int userId) throws SQLException {
//        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getUserTicketAmount(userId));) {
//            if (resultSet.next()) {
//                return resultSet.getInt("ticket_amount");
//            } else {
//                System.out.println("User wasn't found!");
//            }
//        }
//        return 0;
//    }
//
//    public User getUserClass(int id) throws SQLException {
//        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getUser(id));) {
//            if (resultSet.next()) {
//                return new User(resultSet.getInt("user_id"), resultSet.getString("username"), resultSet.getInt("age"), resultSet.getDouble("balance"));
//            } else {
//                System.out.println("User wasn't found!");
//            }
//        }
//        return null;
//    }
//
//    public Movie getMovieClass(int id) throws SQLException {
//        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getMovie(id));) {
//            if (resultSet.next()) {
//                return new Movie(resultSet.getInt("movie_id"), resultSet.getString("movie_name"), resultSet.getDouble("price"));
//            } else {
//                System.out.println("Movie wasn't found!");
//            }
//        }
//        return null;
//    }
//
//    public Movie getMovieClassByName(String name) throws SQLException {
//        boolean isFound = false;
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.getMovieByName()); ResultSet resultSet = preparedStatement.executeQuery()) {
//            preparedStatement.setString(1, name);
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                System.out.println("Movie found successfully!");
//                return new Movie(resultSet.getInt("movie_id"), resultSet.getString("movie_name"), resultSet.getDouble("price"));
//            } else {
//                System.out.println("Failed to add movie. Please try again.");
//            }
//        }
//        return null;
//    }
//
//    public Ticket getTicketClass(int id) throws SQLException {
//        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getTicket(id));) {
//            if (resultSet.next()) {
//                return new Ticket(resultSet.getInt("ticket_id"), resultSet.getString("movie_name"), resultSet.getDouble("price"), resultSet.getString("time"), resultSet.getInt("ticketId"));
//            } else {
//                System.out.println("User wasn't found!");
//            }
//        }
//        return null;
//    }
//
//    public void updateUser(User user) throws SQLException {
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.updateUser(user.getId()));) {
//            preparedStatement.setString(1, user.getUsername());
//            preparedStatement.setInt(2, user.getAge());
//            preparedStatement.setDouble(3, user.getBalance());
//            preparedStatement.setInt(4, user.getTicketArraySize());
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("User updated successfully!");
//            } else {
//                System.out.println("Failed to update user.");
//            }
//        }
//    }
//
//    public void deleteUser(int id) throws SQLException {
//        try (Statement statement = getConnection().createStatement();) {
//            int rowsAffected = statement.executeUpdate(Queries.deleteUser(id));
//            if (rowsAffected > 0) {
//                System.out.println("Deleted successfully");
//            } else {
//                System.out.println("Something  went wrong");
//            }
//        }
//    }
//
//    public void getAllUsers() throws SQLException {
//        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getAllUsers());) {
//            while (resultSet.next()) {
//                System.out.println();
//                userInfo(new User(resultSet.getInt("user_id"), resultSet.getString("username"), resultSet.getInt("age"), resultSet.getDouble("balance")));
//            }
//        }
//    }
//
//    public void userInfo(User user) {
//        System.out.println("ID:       " + user.getId());
//        System.out.println("Username: " + user.getUsername());
//        System.out.println("Age:      " + user.getAge());
//        System.out.println("Balance:  " + user.getBalance());
//        System.out.println("Tickets:  ");
//        user.getAllUserTickets();
//    }
//
//    public void movieInfo(Movie movie) {
//        System.out.println("Movie ID:  " + movie.getMovieId());
//        System.out.println("Movie name: " + movie.getMovieName());
//        System.out.println("   Price:   " + movie.getPrice());
//    }
//
//    public void ticketInfo(Ticket ticket) throws SQLException {
//        System.out.println("Ticket ID:  " + ticket.getMovieId());
//        System.out.println("Ticket Movie name: " + ticket.getMovieName());
//        System.out.println("   Price:   " + ticket.getPrice());
//        System.out.println("   Time:   " + ticket.getTime());
//        System.out.println("   Amount:   " + getTicketAmountForAll(ticket.getTime()));
//    }
//
//    public void timeInfo(String time) {
//        System.out.println(time);
//    }
//
//    public void ticketAmount(int userId, int n) throws SQLException, NumberFormatException {
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.updateTicketAmount(userId));) {
//            int val = getUserTicketAmount(userId) + n;
//            preparedStatement.setInt(1, val);
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Ticket amount changed successfully!");
//            } else {
//                System.out.println("Failed to change ticket amount.");
//            }
//        }
//    }
//
//    public void updateTicketAmountForAll(String time, int toBuy) throws SQLException {
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.updateTicketAmountForAll());) {
//            int n = getTicketAmountForAll(time);
//            preparedStatement.setInt(1, n - toBuy);
//            preparedStatement.setString(2, time);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Ticket amount changed successfully!");
//
//            } else {
//                System.out.println("Failed to change ticket amount.");
//            }
//        }
//    }
//
//    public void addTicketToTable(Ticket ticket) throws SQLException {
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.addTicket(),
//                Statement.RETURN_GENERATED_KEYS)) {
//
//            preparedStatement.setString(1, ticket.getMovieName());
//            preparedStatement.setDouble(2, ticket.getPrice());
//            preparedStatement.setString(3, ticket.getTime());
//            preparedStatement.setInt(4, ticket.getTicketAmount());
//
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//
//                if (generatedKeys.next()) {
//                    ticket.setTicketId(generatedKeys.getInt(1));
//                }
//                System.out.println("Ticket added successfully!");
//            } else {
//                System.out.println("Failed to add user. Please try again.");
//            }
//        }
//    }
//
//    public void getAllTimes(String movieName) throws SQLException {
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.getAllTimes(movieName));) {
//            preparedStatement.setString(1, movieName);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) { // Check if any rows exist
//                System.out.println("All times for '" + movieName + "':");
//                do { // Process all rows
//                    timeInfo(resultSet.getString("time"));
//                } while (resultSet.next());
//            } else {
//                System.out.println("No times found for the movie.");
//            }
//        }
//    }
//
//
//    public void addMovie(Movie movie) throws SQLException {
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.addMovie(),
//                Statement.RETURN_GENERATED_KEYS)) {
//
//            preparedStatement.setString(1, movie.getMovieName());
//            preparedStatement.setDouble(2, movie.getPrice());
//
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    movie.setMovieId(generatedKeys.getInt(1));
//                }
//                System.out.println("Movie added successfully!");
//            } else {
//                System.out.println("Failed to add movie. Please try again.");
//            }
//        }
//    }
//
//    public boolean getMovie(int id) throws SQLException {
//        boolean isFound = false;
//        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getMovie(id));) {
//            if (resultSet.next()) {
//                isFound = true;
//                movieInfo(new Movie(resultSet.getInt("movie_id"), resultSet.getString("movie_name"), resultSet.getDouble("price")));
//            } else {
//                System.out.println("Movie wasn't found!");
//            }
//        }
//        return isFound;
//    }
//
//    public boolean getMovieByName(String name) throws SQLException {
//        boolean isFound = false;
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.getMovieByName());) {
//            preparedStatement.setString(1, name);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) { // Check if any rows were found
//                System.out.println("Movie found successfully!");
//                movieInfo(new Movie(resultSet.getInt("movie_id"), resultSet.getString("movie_name"), resultSet.getDouble("price")));
//                isFound = true;
//            } else {
//                System.out.println("Movie not found.");
//            }
//        }
//        return isFound;
//    }
//
//    public int getTicketAmountForAll(String time) throws SQLException {
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.getMovieByTime());) {
//            preparedStatement.setString(1, time);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) { // Check if any rows were found
//                return resultSet.getInt("ticket_amount");
//            } else {
//                System.out.println("Movie not found.");
//            }
//        }
//        return 0;
//    }
//
//    public boolean getMovieByTime(String time) throws SQLException {
//        boolean isFound = false;
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.getMovieByTime());) {
//            preparedStatement.setString(1, time);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) { // Check if any rows were found
//                System.out.println("Time found successfully!");
//                ticketInfo(new Ticket(resultSet.getInt("ticket_id"), resultSet.getString("movie_name"), resultSet.getDouble("price"), resultSet.getString("time")));
//                isFound = true;
//            } else {
//                System.out.println("Movie not found.");
//            }
//        }
//        return isFound;
//    }
//
//    public void updateMovie(Movie movie) throws SQLException {
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.updateMovie(movie.getMovieId()));) {
//            preparedStatement.setString(1, movie.getMovieName());
//            preparedStatement.setDouble(2, movie.getPrice());
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Movie updated successfully!");
//            } else {
//                System.out.println("Failed to update movie.");
//            }
//        }
//    }
//
//    public void deleteMovie(int id) throws SQLException {
//        try (Statement statement = getConnection().createStatement();) {
//            int rowsAffected = statement.executeUpdate(Queries.deleteMovie(id));
//            if (rowsAffected > 0) {
//                System.out.println("Deleted successfully");
//            } else {
//                System.out.println("Something  went wrong");
//            }
//        }
//    }
//
//    public void getAllMovies() throws SQLException {
//        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getAllMovies());) {
//            while (resultSet.next()) {
//                System.out.println();
//                movieInfo(new Movie(resultSet.getInt("movie_id"), resultSet.getString("movie_name"), resultSet.getDouble("price")));
//            }
//        }
//    }
//
//    public double getMoviePrice(String name) throws SQLException {
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.getMoviePrice());) {
//            preparedStatement.setString(1, name);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) { // Check if any rows were found
//                return resultSet.getDouble("price");
//            } else {
//                System.out.println("Movie wasn't found!");
//            }
//        }
//        return 0;
//    }
//
//    //for tickets
//    public void addTicket(Ticket ticket) throws SQLException {
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.addTicket(),
//                Statement.RETURN_GENERATED_KEYS)) {
//
//            preparedStatement.setString(1, ticket.getMovieName());
//            preparedStatement.setDouble(2, ticket.getPrice());
//            preparedStatement.setString(3, ticket.getTime());
//            preparedStatement.setInt(4, ticket.getTicketAmount());
//
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    ticket.setMovieId(generatedKeys.getInt(1));
//                }
//                System.out.println("Movie added successfully!");
//            } else {
//                System.out.println("Failed to add movie. Please try again.");
//            }
//        }
//    }
//
//    public boolean getTicket(int id) throws SQLException {
//        boolean isFound = false;
//        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getTicket(id));) {
//            if (resultSet.next()) {
//                isFound = true;
//                ticketInfo(new Ticket(resultSet.getInt("ticket_id"), resultSet.getString("movie_name"), resultSet.getDouble("price"), resultSet.getString("time"), resultSet.getInt("ticket_amount")));
//            } else {
//                System.out.println("Ticket() wasn't found!");
//            }
//        }
//        return isFound;
//    }
////        return "UPDATE tickets SET movie_name = ?, price= ?, time = ? WHERE ticket_id = " + ticket_id;
//
//    public void updateTicket(Ticket ticket) throws SQLException {
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.updateTicket(ticket.getTicketId()));) {
//            preparedStatement.setDouble(1, ticket.getPrice());
//            preparedStatement.setString(2, ticket.getTime());
//            preparedStatement.setInt(3, ticket.getTicketAmount());
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Ticket updated successfully!");
//            } else {
//                System.out.println("Failed to update ticket.");
//            }
//        }
//    }
//
//    public void deleteTicket(int id) throws SQLException {
//        try (Statement statement = getConnection().createStatement();) {
//            int rowsAffected = statement.executeUpdate(Queries.deleteTicket(id));
//            if (rowsAffected > 0) {
//                System.out.println("Deleted successfully");
//            } else {
//                System.out.println("Something  went wrong");
//            }
//        }
//    }
//
//    public void getAllTickets() throws SQLException {
//        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getAllTickets());) {
//            while (resultSet.next()) {
//                System.out.println();
//                ticketInfo(new Ticket(resultSet.getInt("ticket_id"), resultSet.getString("movie_name"), resultSet.getDouble("price"), resultSet.getString("time")));
//            }
//        }
//    }
//    public void updateUserBalance(int id, Ticket ticket, int n) throws SQLException {
//            try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.updateUserBalance(id));) {
//                preparedStatement.setDouble(1, getUserClass(id).getBalance()-(ticket.getPrice()*n));
//                int rowsAffected = preparedStatement.executeUpdate();
//                if(rowsAffected > 0) {
//                    System.out.println("Balance was updated successfully!");
//                } else {
//                    System.out.println("Failed to update user balance");
//                }
//            }
//        }
//}
