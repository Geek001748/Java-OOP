package controllers;

import entities.Movie;
import repositories.MovieTable;

import java.sql.SQLException;
import java.util.Scanner;

public class MovieController {
<<<<<<< Updated upstream
    private MovieTable movieRepository;
    public MovieController(MovieTable userRepository) {
=======
    private TicketController ticketController;
    private Repositories movieRepository;
    private Repositories repositories;  // Rename to repositories for consistency
    private Repositories ticketRepository;  // Assuming you have another repository for tickets

    public MovieController(Repositories repositories, Repositories ticketRepository) {
        this.repositories = repositories;
        this.ticketRepository = ticketRepository;
    }

    public MovieController(Repositories userRepository) {
>>>>>>> Stashed changes
        this.movieRepository = userRepository;
    }

    public void addMovie(Scanner scanner) throws SQLException {
            System.out.print("Enter movie name: ");
            String movieName = scanner.nextLine();
            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine());
<<<<<<< Updated upstream
            movieRepository.addToTable(new Movie(movieName, price));
=======
            movieRepository.addMovie(new Movie(movieName, price));
            System.out.println("How many tickets you want to add?(0-100)");
            int ticketAmount;
            do {
                    ticketAmount = Integer.parseInt(scanner.nextLine());
                    if(ticketAmount>0 && ticketAmount<100){
                        break;
                    }
                System.out.println("You have written incorrect number. (0-100)");
            } while(true);
            System.out.println("Enter movie time (xx:xx):");
            String time;
            do {
                time = scanner.nextLine();
                if(TicketController.isCorrectTime(time)){
                     movieRepository.addTicketToTable(new Ticket(movieName,price,time,ticketAmount));
                     break;
                }
            } while(true);


>>>>>>> Stashed changes
    }
    public void updateMovie(Scanner scanner) throws SQLException{
            System.out.println("Enter the id to update");
            int id = Integer.parseInt(scanner.nextLine());
            if(movieRepository.getObjectFromTable(id)) {
                System.out.println("Enter the movie name to update: ");
                String movieName = scanner.nextLine();

                System.out.println("Enter the price to update: ");
                double price  = Double.parseDouble(scanner.nextLine());

                movieRepository.updateObjectTable(new Movie(id, movieName, price ));
            }
    }
    public void deleteMovie(Scanner scanner) throws SQLException{
                System.out.println("Enter the id to delete");
                int id = Integer.parseInt(scanner.nextLine());
                if(movieRepository.getObjectFromTable(id)) {
                    movieRepository.deleteObjectFromTable(id);
                }
    }
     public void getMovie(Scanner scanner) throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id of movie");
        int id = sc.nextInt();
        movieRepository.getObjectFromTable(id);
     }
     public void getMovieByName(Scanner scanner) throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of movie");
        String name = sc.nextLine();
        movieRepository.getMovieByName(name);
     }


    public void getAllMovies() throws SQLException{
        movieRepository.getAllObjectsFromTable();
    }

}
