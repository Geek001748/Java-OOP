package controllers;

import entities.Movie;
import repositories.MovieRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class MovieController {
    private final MovieRepository movieRepository;
    private final Scanner scanner;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Movie Management System");
            System.out.println("0. Exit");
            System.out.println("1. Add Movie");
            System.out.println("2. Update Movie");
            System.out.println("3. Delete Movie");
            System.out.println("4. Get Movie by ID");
            System.out.println("5. View All Movies");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    case 1:
                        addMovie();
                        break;
                    case 2:
                        updateMovie();
                        break;
                    case 3:
                        deleteMovie();
                        break;
                    case 4:
                        getMovieById();
                        break;
                    case 5:
                        viewAllMovies();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addMovie() throws SQLException {
        System.out.println("Enter movie name:");
        String movieName = scanner.nextLine();

        System.out.println("Enter movie genre:");
        String movieGenre = scanner.nextLine();

        Movie movie = new Movie(movieName, movieGenre);
        movieRepository.addMovie(movie);
    }

    private void updateMovie() throws SQLException {
        System.out.println("Enter movie ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter new movie name:");
        String movieName = scanner.nextLine();

        System.out.println("Enter new movie genre:");
        String movieGenre = scanner.nextLine();

        Movie movie = new Movie(id, movieName, movieGenre);
        movieRepository.updateMovie(movie);
    }

    private void deleteMovie() throws SQLException {
        System.out.println("Enter movie ID:");
        int id = scanner.nextInt();
        movieRepository.deleteMovie(id);
    }

    private void getMovieById() throws SQLException {
        System.out.println("Enter movie ID:");
        int id = scanner.nextInt();
        boolean found = movieRepository.getMovie(id);
        if (!found) {
            System.out.println("Movie not found!");
        }
    }

    private void viewAllMovies() throws SQLException {
        movieRepository.getAllMovies();
    }
}
