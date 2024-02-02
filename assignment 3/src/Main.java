import controllers.TicketController;
import controllers.UserController;
import repositories.Repositories;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Repositories repositories = new Repositories();

            UserController userController = new UserController(repositories);
            TicketController ticketController = new TicketController(repositories);
            Scanner scanner = new Scanner(System.in);
            repositories.createUsersTable();
            repositories.createMoviesTable();
            repositories.createTicketsTable();

            runCinemaApp(scanner);
        } catch (Exception e) {
            throw new RuntimeException("Error." + e);
        }
    }

    private static void runCinemaApp(Scanner scanner) throws SQLException {
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
                    printMenuForUsersOptions();
                    break;

                case 2:
                    printMenuForTicketOptions();
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
        System.out.println("2.Show all tickets options");
        System.out.println("3.Show all movies options");
    }

    public static void printMenuForUsersOptions() {
        System.out.println("0.Exit");
        System.out.println("1.Add new user");
        System.out.println("2.Delete user");
        System.out.println("3.Update user");
        System.out.println("4.Show all users");
        System.out.println("5.Show user information");
    }

    static public void swichCaseForUsers(Scanner scanner) {
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

    static public void swichCaseForMovies(Scanner scanner) {
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

    static public void switchCaseForTickets(Scanner scanner) {
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

            default:
                System.out.println("Invalid choice");
        }
    }
}
