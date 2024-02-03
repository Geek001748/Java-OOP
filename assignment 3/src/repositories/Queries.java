package repositories;

public class Queries {
    public static String  createUsersTable(){
          return "CREATE TABLE IF NOT EXISTS users ("
                    + "user_id SERIAL PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "age INT NOT NULL,"
                    + "balance DOUBLE PRECISION NOT NULL"
                    + "ticket_amount INT NOT NULL )";
    }

        public static String  createMoviesTable(){
          return "CREATE TABLE IF NOT EXISTS movies ("
                    + "movie_id SERIAL PRIMARY KEY,"
                    + "movie_name VARCHAR(50) NOT NULL,"
                    + "price DOUBLE PRECISION  NOT NULL)";
    }

    public static String  createTicketTable(){
          return "CREATE TABLE IF NOT EXISTS tickets ("
                    + "ticket_id SERIAL PRIMARY KEY,"
                    + "movie_name VARCHAR(50) NOT NULL,"
                    + "price DOUBLE PRECISION  NOT NULL,"
                    +" time VARCHAR(5) NOT NULL"
                    + "ticketAmount INT NOT NULL)";
    }

    public static String  addUser(){
          return "INSERT INTO users (username, age, balance, ticketAmount) VALUES (?, ?, ?, ?)";
    }
    public static String  deleteUser(int id){
          return "DELETE FROM users WHERE id = "+id;
    }
    public static String  updateUser(int id){
          return "UPDATE users SET username = ?, age = ?, balance = ?, ticketAmount = ? WHERE id = "+id;
    }
    public static String  getAllUsers(){
          return "SELECT * FROM users";
    }
    public static String  getUser(int id){
          return "SELECT * FROM users WHERE id = "+id;
    }
    public static String  addTicket(){
          return "INSERT INTO tickets (movieName,price,time,ticketAmount) VALUES (?, ?, ?, ?)";
    }
    public static String  addTicketToUser(){
          return "INSERT INTO tickets (movieName,price,time) VALUES (?, ?, ?)";
    }
    public static String  deleteTicket(int ticketId){
          return "DELETE FROM tickets WHERE ticketsId ="+ticketId;
    }
    public static String  updateTicket(int ticketId){
          return "UPDATE SET movieName = ?, price= ?, time = ? WHERE ticketId = "+ticketId;
    }
    public static String  getAllTickets(){
          return "SELECT * FROM tickets";
    }
    public static String  getTicket(int ticketId){
          return "SELECT * FROM tickets WHERE id = "+ticketId;
    }
    public static String  updateTicketAmount(int id){
          return "UPDATE users SET ticketAmount = ?,  WHERE id = " +id;
    }
    public static String  updateTicketAmountForAll(int id){
          return "UPDATE tickets SET ticketAmount = ?,  WHERE id = " +id;
    }
    public static String  getTicketAmountForAll(int id){
          return "SELECT ticketAmount FROM tickets WHERE id = " +id;
    }
    public static String  addMovie(){
          return "INSERT INTO movies (movieName,price) VALUES (?, ?)";
    }
    public static String  getMovie(int id){
          return "SELECT * FROM movies WHERE id = "+id;
    }

    public static String  getMovieByName(String name ){
          return "SELECT * FROM movies WHERE movieName  = "+name;
    }
    public static String  getMovieByTime(String time ){
          return "SELECT * FROM movies WHERE time = "+time;
    }

    public static String  updateMovie(int id){
          return "UPDATE movies SET movieName = ?, price = ? WHERE id = "+id;
    }

    public static String  deleteMovie(int id){
          return "DELETE FROM movies WHERE id = "+id;
    }
     public static String  getAllMovies(){
          return "SELECT * FROM movies";
    }

    public static String getMoviePrice (String name) {
        return "SELECT price FROM movies WHERE movie_name = " +name;
    }
}