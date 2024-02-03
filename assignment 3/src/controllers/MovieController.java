package controllers;

import entities.Movie;
import repositories.MovieTable;

import java.sql.SQLException;
import java.util.Scanner;

public class MovieController {
    private MovieTable movieRepository;
    public MovieController(MovieTable userRepository) {
        this.movieRepository = userRepository;
    }

    public void addMovie(Scanner scanner) throws SQLException {
            System.out.print("Enter movie name: ");
            String movieName = scanner.nextLine();
            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine());
            movieRepository.addToTable(new Movie(movieName, price));
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
