package controllers;

import entities.Movie;
import entities.Ticket;
import repositories.Repositories;

import java.sql.SQLException;
import java.util.Scanner;

public class MovieController {
    private TicketController ticketController;
    private Repositories movieRepository;
    public MovieController(Repositories userRepository) {
        this.movieRepository = userRepository;
    }

    public void addMovie(Scanner scanner) throws SQLException {
            System.out.print("Enter movie name: ");
            String movieName = scanner.nextLine();
            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine());
            movieRepository.addMovie(new Movie(movieName, price));
            System.out.println("How many tickets you want to add?");
            int ticketAmount;
            do {
                    ticketAmount = Integer.parseInt(scanner.nextLine());
                    if(ticketAmount>0 && ticketAmount<100){
                        break;
                    }
                System.out.println("You have written incorrect number. (0-100)");
            } while(true);
            System.out.print("Enter movie time xx:xx");
                String time;

                do {
                    time = scanner.nextLine();
                    if(ticketController.isCorrectTime(time)){
                        movieRepository.addTicketToTable(new Ticket(movieName,price,time,ticketAmount));
                    }

                } while(!ticketController.isCorrectTime(time));

    }
    public void updateMovie(Scanner scanner) throws SQLException{
            System.out.println("Enter the id to update");
            int id = Integer.parseInt(scanner.nextLine());
            if(movieRepository.getMovie(id)) {
                System.out.println("Enter the movie name to update: ");
                String movieName = scanner.nextLine();

                System.out.println("Enter the price to update: ");
                double price  = Double.parseDouble(scanner.nextLine());

                movieRepository.updateMovie(new Movie(id, movieName, price ));
            }
    }
    public void deleteMovie(Scanner scanner) throws SQLException{
                System.out.println("Enter the id to delete");
                int id = Integer.parseInt(scanner.nextLine());
                if(movieRepository.getMovie(id)) {
                    movieRepository.deleteMovie(id);
                }
    }
     public void getMovie(Scanner scanner) throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id of movie");
        int id = sc.nextInt();
        movieRepository.getMovie(id);
     }
     public void getMovieByName(Scanner scanner) throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of movie");
        String name = sc.nextLine();
        movieRepository.getMovieByName(name);
     }


    public void getAllMovies() throws SQLException{
        movieRepository.getAllMovies();
    }

}
