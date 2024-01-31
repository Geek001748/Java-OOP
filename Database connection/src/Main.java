import controllers.UserController;
import repositories.UserRepository;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);


            createUsersTable(connection);

            UserRepository userRepository = new UserRepository(connection);
            UserController userController = new UserController(userRepository);

            runUserManagementApp(userController);

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createUsersTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users ("
                    + "id SERIAL PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "age INT NOT NULL,"
                    + "balance DOUBLE PRECISION NOT NULL)";

            statement.executeUpdate(createTableQuery);
        }
    }

    private static void runUserManagementApp(UserController userController) {
        Scanner scanner = new Scanner(System.in);

        boolean st=true;
        try{
            while (st) {
                System.out.println("1. Add User");
                System.out.println("2. Get All Users");
                System.out.println("3. Update user by id");
                System.out.println("4. Delete by id");
                System.out.println("5. To get user info");


                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");


                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        userController.addUser(scanner);
                        break;
                    case 2:
                        userController.getAllUsers();
                        break;
                    case 3:
                        userController.updateUser();
                        break;
                    case 4:
                        userController.deleteUser();
                        break;
                    case 5:
                        userController.getUser();
                        break;
                    case 0:
                        System.out.println("Exiting the application. Goodbye!");
                        st = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }
            catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();  // Print the complete stack trace for debugging
                st = false;  // Set st to false to exit the loop
        }

    }
}
