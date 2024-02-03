package repositories;

public class Queries {
    public static String  createUsersTable(){
          return "CREATE TABLE IF NOT EXISTS users ("
                    + "id SERIAL PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "age INT NOT NULL,"
<<<<<<< Updated upstream
                    + "balance DOUBLE PRECISION NOT NULL"
                    +"ticketAmount INT NOT NULL )";
=======
                    + "balance DOUBLE PRECISION NOT NULL,"
                    + "user_ticket_amount INT NOT NULL)";
>>>>>>> Stashed changes
    }

        public static String  createMoviesTable(){
          return "CREATE TABLE IF NOT EXISTS movies ("
                    + "movieId SERIAL PRIMARY KEY,"
                    + "movieName VARCHAR(50) NOT NULL,"
                    + "price DOUBLE PRECISION  NOT NULL)";
    }

    public static String  createTicketTable(){
          return "CREATE TABLE IF NOT EXISTS tickets ("
                    + "ticketId SERIAL PRIMARY KEY,"
                    + "ticketMovieName VARCHAR(50) NOT NULL,"
                    + "price DOUBLE PRECISION  NOT NULL,"
<<<<<<< Updated upstream
                    +"time VARCHAR(10) NOT NULL)";
=======
                    + "time VARCHAR(5) NOT NULL,"
                    + "movie_ticket_amount INT NOT NULL)";
>>>>>>> Stashed changes
    }



    public static String  addUser(){
          return "INSERT INTO users (username, age, balance, user_ticket_amount) VALUES (?, ?, ?, ?)";
    }
    public static String  deleteUser(int id){
          return "DELETE FROM users WHERE user_id = "+id;
    }
    public static String  updateUser(int id){
          return "UPDATE users SET username = ?, age = ?, balance = ?, user_ticket_amount = ? WHERE user_id = "+id;
    }
    public static String  getAllUsers(){
          return "SELECT * FROM users";
    }
    public static String  getUser(int id){
          return "SELECT * FROM users WHERE user_id = "+id;
    }
    public static String  addTicket(){
<<<<<<< Updated upstream
          return "INSERT INTO tickets (movieName,price,time) VALUES (?, ?, ?)";
=======
          return "INSERT INTO tickets (movie_name,price,time,movie_ticket_amount) VALUES (?, ?, ?, ?)";
    }
    public static String  addTicketToUser(){
          return "INSERT INTO tickets (movie_name,price,time) VALUES (?, ?, ?)";
>>>>>>> Stashed changes
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
          return "UPDATE users SET user_ticket_amount = ? WHERE user_id = " +id;
    }
<<<<<<< Updated upstream
=======
    public static String  updateTicketAmountForAll(int id){
          return "UPDATE tickets SET movie_ticket_amount = ? WHERE ticket_id = " +id;
    }
    public static String  getTicketAmountForAll(int id){
          return "SELECT movie_ticket_amount FROM tickets WHERE ticket_id = " +id;
    }
>>>>>>> Stashed changes
    public static String  addMovie(){
          return "INSERT INTO movies (movie_name, price) VALUES (?, ?)";
    }
    public static String  getMovie(int id){
          return "SELECT * FROM movies WHERE movie_id = "+id;
    }

    public static String  getMovieByName(String name ){
          return "SELECT * FROM movies WHERE movie_name  = "+name;
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
}