import entities.Ticket;

import java.sql.*;
import java.util.InputMismatchException;

public class FunctionsForMovies {
     private Connection connection;

     public  void addUser  (Movie movie ){
          String sql = "INSERT INTO users (name,price) VALUES (?,?)";
          try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

               preparedStatement.setString(1, movie.getMovie_name());
                preparedStatement.setDouble(2, movie.getPrice());

                int rows_Affected = preparedStatement.executeUpdate();
//reparedStatement.executeUpdate(); returns amount of changed rows
                if (rows_Affected >0){
                     ResultSet generated_keys =preparedStatement.getGeneratedKeys();
//ResultSet generated_keys =preparedStatement.getGeneratedKeys(); returns all inserted new id s
                     if(generated_keys.next()){
                          movie.setGetMovieId(generated_keys.getInt(1));
                          System.out.println(" Movie was added successfully");
                     }

                }else {
                     System.out.println("Failed to add movie.Try again");

                }


          }catch(InputMismatchException e){
               System.out.println("Invalid input.Enter valid value");
          } catch(SQLException e){
               e.printStackTrace();
          }

     }
     public  void updateMovies (){

     }

     public  void deleteMovies (){

     }

     public  void buyNewTicket (User user, Ticket ticket){

     }
}
