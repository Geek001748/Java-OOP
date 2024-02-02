import entities.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FunctionsForUser {

     ArrayList <Ticket> tickets;

     private Connection connection;

     public  void addUser  (User user ){
          String sql = "INSERT INTO users (username,age,balance) VALUES (?,?,?)";
          try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

               preparedStatement.setString(1, user.getUsername());
                preparedStatement.setInt(2, user.getAge());
                preparedStatement.setDouble(3, user.getBalance());

                int rows_Affected = preparedStatement.executeUpdate();
//reparedStatement.executeUpdate(); returns amount of changed rows
                if (rows_Affected >0){
                     ResultSet generated_keys =preparedStatement.getGeneratedKeys();
//ResultSet generated_keys =preparedStatement.getGeneratedKeys(); returns all inserted new id s
                     if(generated_keys.next()){
                          user.setId(generated_keys.getInt(1));
                          System.out.println(" User was added successfully");
                     }

                }else {
                     System.out.println("Failed to add user.Try again");

                }


          }catch(InputMismatchException e){
               System.out.println("Invalid input.Enter valid value");
          } catch(SQLException e){
               e.printStackTrace();
          }

     }
     public  void updateUser (){

     }

     public  void deleteUser (){

     }

     public void addTicketToUser (User user, Ticket ticket, ArrayList <Ticket> tickets){
          String movie_name;
          System.out.println("Enter movie name");
          Scanner sc = new Scanner(System.in);
          movie_name = sc.nextLine();

          System.out.println("Enter movie time");
          Scanner scanner  = new Scanner(System.in);
          String time = scanner.nextLine();

          if (getTicketByMovieAndTime(tickets,movie_name,time)!=null && user.getBalance()>getTicketByMovieAndTime(tickets,movie_name,time).getPrice()){
               user.tickets_of_user.add(getTicketByMovieAndTime(tickets,movie_name,time));

               System.out.println("You have successfully bought ticket!Thank you!");
          }
          else{
               System.out.println("You could not buy this ticket! Try again.");
          }



     }

     public Ticket getTicketByMovieAndTime (ArrayList <Ticket> tickets, String movie_name, String time){


          for (Ticket ticket : tickets){
               if((ticket.getTime().equals(time)) && ticket.getMovie_name().equals(movie_name)){
                    return ticket;
               }

          }
         System.out.println("There is not such movie");
         return null;
     }
}
