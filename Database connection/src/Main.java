import controllers.UserController;
import repositories.UserRepository;

import java.sql.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        try {

            UserRepository userRepository = new UserRepository();
            UserController userController = new UserController(userRepository);

            userRepository.createUsersTable();
            runUserManagementApp(userController);

        } catch (SQLException e) {
            e.printStackTrace();
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
                        userController.updateUser(scanner);
                        break;
                    case 4:
                        userController.deleteUser(scanner);
                        break;
                    case 5:
                        userController.getUser(scanner);
                        break;
                    case 0:
                        System.out.println("Exiting the application. Goodbye!");
                        st = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong" + e);
        }
    }
}