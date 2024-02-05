import controllers.MovieController;
import controllers.TicketController;
import controllers.UserController;
import repositories.Repositories;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            runCinemaApp(scanner);
        } catch (Exception e) {
            throw new RuntimeException("Error." + e);
        }
    }


    private static void runCinemaApp(Scanner scanner) throws SQLException, NumberFormatException {
        Repositories repositories = new Repositories();
        UserController userController = new UserController(repositories);
        MovieController movieController = new MovieController(repositories);
        TicketController ticketController = new TicketController(repositories);
        repositories.createUsersTable();
        repositories.createMoviesTable();
        repositories.createTicketsTable();

        boolean st = true;
        while (st) {
            printMenuForAdmin();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 0:
                    st = false;
                    System.out.println("Adios!");
                    break;

                case 1:
                    swichCaseForUsers(scanner,userController);
                    break;

                case 2:
                    swichCaseForMovies(scanner, movieController);
                    break;

                case 3:
                    switchCaseForTickets(scanner, ticketController);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    static public void printMenuForAdmin() {
        System.out.println("0.Exit");
        System.out.println("1.Show all users options");
        System.out.println("2.Show all movies options");
        System.out.println("3.Show all tickets options");

    }

    public static void printMenuForUsersOptions() {
        System.out.println("0.Exit");
        System.out.println("1.Add new user");
        System.out.println("2.Delete user");
        System.out.println("3.Update user");
        System.out.println("4.Show all users");
        System.out.println("5.Show user information");

    }

    static public void swichCaseForUsers(Scanner scanner,UserController userController) throws SQLException{
        printMenuForUsersOptions();
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 0:
                runCinemaApp(scanner);
                break;

            case 1: userController.addUser(scanner);
                break;

            case 2: userController.deleteUser(scanner);
                break;

            case 3: userController.updateUser(scanner);
                break;
            case 4: userController.getAllUsers();
            break;
            case 5 : userController.getUser(scanner);
            break;

        }
    }

    static public void printMenuForMovieOptions() {
        System.out.println("0.Exit");
        System.out.println("1.Add new movie");
        System.out.println("2.Delete movie");
        System.out.println("3.Update movie");
        System.out.println("4.Show all movies");
        System.out.println("5.Show movie information");
    }

    static public void swichCaseForMovies(Scanner scanner, MovieController movieController) throws SQLException {
        printMenuForMovieOptions();
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 0: runCinemaApp(scanner);
                break;

            case 1: movieController.addMovie(scanner);
                break;

            case 2: movieController.deleteMovie(scanner);
                break;

            case 3: movieController.updateMovie(scanner);
                break;
            case 4: movieController.getAllMovies();
            break;
            case 5: movieController.getMovie(scanner);
                break;
        }
    }

    static public void printMenuForTicketOptions() {
        System.out.println("0.Exit");
        System.out.println("1.Add ticket to user");
    }

    static public void switchCaseForTickets(Scanner scanner, TicketController ticketController) throws  SQLException, NumberFormatException {
        printMenuForTicketOptions();
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 0: runCinemaApp(scanner);
                break;
            case 1: ticketController.addTicketToUser(scanner);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}
