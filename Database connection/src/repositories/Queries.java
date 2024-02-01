package repositories;

public class Queries {
    public static String  createUsersTable(){
          return "CREATE TABLE IF NOT EXISTS users ("
                    + "id SERIAL PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "age INT NOT NULL,"
                    + "balance DOUBLE PRECISION NOT NULL)";
    }
    public static String  addUser(){
          return "INSERT INTO users (username, age, balance) VALUES (?, ?, ?)";
    }
    public static String  deleteUser(int id){
          return "DELETE FROM users WHERE id = "+id;
    }
    public static String  updateUser(int id){
          return "UPDATE users SET username = ?, age = ?, balance = ? WHERE id = "+id;
    }
    public static String  getAllUsers(){
          return "SELECT * FROM users";
    }public static String  getUser(int id){
          return "SELECT * FROM users WHERE id = "+id;
    }
}
