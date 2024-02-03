import controllers.MovieController;
import controllers.TicketController;
import controllers.UserController;
import repositories.MovieTable;
import repositories.SQLConnection;
import repositories.TicketTable;
import repositories.UserTable;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            UserTable userTable = new UserTable();
            MovieTable movieTable = new MovieTable();
            TicketTable ticketTable = new TicketTable();

            UserController userController = new UserController(userTable);
            TicketController ticketController = new TicketController(ticketTable);
            MovieController movieController = new MovieController(movieTable);
            Scanner scanner = new Scanner(System.in);

            userTable.createTable();
            movieTable.createTable();
            ticketTable.createTable();

            runCinemaApp(scanner);
        } catch (Exception e) {
            throw new RuntimeException("Error." + e);
        }
    }

<<<<<<< Updated upstream
    private static void runCinemaApp(Scanner scanner) throws SQLException {
=======

    private static void runCinemaApp(Scanner scanner) throws SQLException,RuntimeException {
        Repositories repositories = new Repositories();
        UserController userController = new UserController(repositories);
        MovieController movieController = new MovieController(repositories);
        TicketController ticketController = new TicketController(repositories);
        repositories.createUsersTable();
        repositories.createMoviesTable();
        repositories.createTicketsTable();

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                    printMenuForUsersOptions();
                    break;

                case 2:
                    printMenuForTicketOptions();
=======
                    switchCaseForUsers(scanner,userController);
                    break;

                case 2:
                    switchCaseForMovies(scanner, movieController);
>>>>>>> Stashed changes
                    break;

                case 3:
                    printMenuForMovieOptions();
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

<<<<<<< Updated upstream
    static public void swichCaseForUsers(Scanner scanner) {
=======
    static public void switchCaseForUsers(Scanner scanner,UserController userController) throws SQLException{
        printMenuForUsersOptions();
>>>>>>> Stashed changes
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 0:
                break;

            case 1:
                break;

            case 2:
                break;

            case 3:
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

<<<<<<< Updated upstream
    static public void swichCaseForMovies(Scanner scanner) {
=======
    static public void switchCaseForMovies(Scanner scanner, MovieController movieController) throws SQLException {
        printMenuForMovieOptions();
>>>>>>> Stashed changes
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 0:
                break;

            case 1:
                break;

            case 2:
                break;

            case 3:
                break;
        }
    }

    static public void printMenuForTicketOptions() {
        System.out.println("0.Exit");
        System.out.println("1.Add new ticket");
        System.out.println("2.Delete ticket");
        System.out.println("3.Update ticket");
        System.out.println("4.Show all tickets");
        System.out.println("5.Show ticket information");
    }

<<<<<<< Updated upstream
    static public void switchCaseForTickets(Scanner scanner) {
=======
    static public void switchCaseForTickets(Scanner scanner, TicketController ticketController) throws  SQLException {
        printMenuForTicketOptions();
>>>>>>> Stashed changes
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 0:
<<<<<<< Updated upstream
                break;

            case 1:

=======
                // You might want to return to the main menu or do other actions.
                break;
            case 1:
                addTicket(scanner);
                switchCaseForTickets(scanner);
                break;
            case 2:
                deleteTicket(scanner);
                switchCaseForTickets(scanner);
                break;
            case 3:
                updateTicket(scanner);
                switchCaseForTickets(scanner);
                break;
            case 4:
                getAllTickets();
                switchCaseForTickets(scanner);
                break;
            case 5:
                getTicket(scanner);
                switchCaseForTickets(scanner);
>>>>>>> Stashed changes
                break;

            case 2:
                break;

            case 3:
                break;

            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                switchCaseForTickets(scanner);
        }
    }
}
