package repositories;

public class Queries {
    public static String  createUsersTable(){
          return "CREATE TABLE IF NOT EXISTS users ("
                    + "user_id SERIAL PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "age INT NOT NULL,"
                    + "balance DOUBLE PRECISION NOT NULL,"
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
                    + "time VARCHAR(5) NOT NULL,"
                    + "ticket_amount INT NOT NULL)";
    }

    public static String  addUser(){
          return "INSERT INTO users (username, age, balance, ticket_amount) VALUES (?, ?, ?, ?)";
    }
    public static String  deleteUser(int id){
          return "DELETE FROM users WHERE user_id = "+id;
    }
    public static String  updateUser(int id){
          return "UPDATE users SET username = ?, age = ?, balance = ?, ticket_amount = ? WHERE user_id = "+id;
    }
    public static String  getAllUsers(){
          return "SELECT * FROM users";
    }
    public static String  getUser(int id){
          return "SELECT * FROM users WHERE user_id = "+id;
    }
    public static String  addTicket(){
          return "INSERT INTO tickets (movie_name,price,time,ticket_amount) VALUES (?, ?, ?, ?)";
    }
    public static String  addTicketToUser(){
          return "INSERT INTO tickets (movie_name,price,time) VALUES (?, ?, ?)";
    }
    public static String  deleteTicket(int ticket_id){
          return "DELETE FROM tickets WHERE ticket_id ="+ticket_id;
    }
    public static String  updateTicket(int ticket_id){
          return "UPDATE SET movie_name = ?, price= ?, time = ? WHERE ticket_id = "+ticket_id;
    }
    public static String  getAllTickets(){
          return "SELECT * FROM tickets";
    }
    public static String  getTicket(int ticket_id){
          return "SELECT * FROM tickets WHERE ticket_id = "+ticket_id;
    }
    public static String  updateTicketAmount(int id){
          return "UPDATE users SET ticket_amount = ? WHERE user_id = " +id;
    }
    public static String  getUserTicketAmount(int id){
          return "SELECT ticket_amount FROM users WHERE user_id = " +id;
    }
    public static String  updateTicketAmountForAll(String time){
          return "UPDATE tickets SET ticket_amount = ? WHERE time LIKE " + time;
    }
    public static String  getTicketAmountForAll(int id){
          return "SELECT ticket_amount FROM tickets WHERE ticket_id = " +id;
    }
    public static String  addMovie(){
          return "INSERT INTO movies (movie_name,price) VALUES (?, ?)";
    }
    public static String  getMovie(int id){
          return "SELECT * FROM movies WHERE movie_id = "+id;
    }

    public static String  getMovieByName(){
          return "SELECT * FROM movies WHERE movie_name LIKE ?";
    }
    public static String  getMovieByTime(){
          return "SELECT * FROM tickets WHERE time LIKE ?";
    }

    public static String  updateMovie(int id){
          return "UPDATE movies SET movie_name = ?, price = ? WHERE movie_id = "+id;
    }

    public static String  deleteMovie(int id){
          return "DELETE FROM movies WHERE movie_id = "+id;
    }
     public static String  getAllMovies(){
          return "SELECT * FROM movies";
    }
     public static String  getAllTimes(String movieName){
          return "SELECT time FROM tickets WHERE movie_name LIKE ?";
    }

    public static String getMoviePrice () {
        return "SELECT price FROM tickets WHERE movie_name LIKE ?";
    }
}