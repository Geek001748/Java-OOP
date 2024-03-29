import java.sql.SQLException;
import java.util.Scanner;

import Queries.MovieQueries;
import Queries.TicketQueries;
import Queries.UserQueries;
import data.DB;
import data.IDB;
import repositories.MovieRepository;
import repositories.TicketRepository;
import repositories.UserRepository;
import controllers.MovieController;
import controllers.TicketController;
import controllers.UserController;

public class Main {
    public static void main(String[] args) {
        try {
            IDB db = initializeDatabase();

            if (createTables(db)) {
                manageSystems(db);
            } else {
                System.out.println("Failed to create tables. Exiting...");
            }
        } catch (SQLException e) {
            System.out.println("Failed to initialize application: " + e.getMessage());
        }
    }

    private static IDB initializeDatabase() throws SQLException {
        return DB.getInstance();
    }

    private static boolean createTables(IDB db) {
        try {
            db.executeUpdate(new UserQueries().createTable());
            db.executeUpdate(new MovieQueries().createTable());
            db.executeUpdate(new TicketQueries().createTable());
            System.out.println("Tables created successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to create tables: " + e.getMessage());
            return false;
        }
    }

    private static void manageSystems(IDB db) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose a management system:");
            System.out.println("1. User Management");
            System.out.println("2. Ticket Management");
            System.out.println("3. Movie Management");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    case 1:
                        startUserManagement(db);
                        break;
                    case 2:
                        startTicketManagement(db);
                        break;
                    case 3:
                        startMovieManagement(db);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
            }
        }
    }

    private static void startUserManagement(IDB db) {
        UserRepository userRepository = new UserRepository(db);
        UserController userController = new UserController(userRepository);
        userController.start();
    }

    private static void startTicketManagement(IDB db) {
        TicketRepository ticketRepository = new TicketRepository(db);
        TicketController ticketController = new TicketController(ticketRepository);
        ticketController.start();
    }

    private static void startMovieManagement(IDB db) {
        MovieRepository movieRepository = new MovieRepository(db);
        MovieController movieController = new MovieController(movieRepository);
        movieController.start();
    }
}
