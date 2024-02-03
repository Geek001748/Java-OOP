package controllers;

import entities.User;
import repositories.SQLConnection;
import repositories.UserTable;

import java.sql.SQLException;
import java.util.Scanner;

public class UserController {
    private UserTable userRepository;

    public UserController(UserTable userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(Scanner scanner) throws SQLException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter balance: ");
        double balance = Double.parseDouble(scanner.nextLine());

        userRepository.addToTable(new User(username, age, balance));
    }
<<<<<<< Updated upstream
    public void updateUser(Scanner scanner) throws SQLException{
            System.out.println("Enter the id to update");
            int id = Integer.parseInt(scanner.nextLine());
            if(userRepository.getObjectFromTable(id)) {
                System.out.println("Enter the surname to update");
                String username = scanner.nextLine();
=======
>>>>>>> Stashed changes

    public void updateUser(Scanner scanner) throws SQLException {
        System.out.println("Enter the id to update");
        int id = Integer.parseInt(scanner.nextLine());
        if (userRepository.getUser(id)) {
            System.out.println("Enter the username to update");
            String username = scanner.nextLine();

            System.out.println("Enter the age to update");
            int age = Integer.parseInt(scanner.nextLine());

<<<<<<< Updated upstream
                userRepository.updateObjectTable(new User(id, username, age, balance));
            }
    }
    public void deleteUser(Scanner scanner) throws SQLException{
                System.out.println("Enter the id to delete");
                int id = Integer.parseInt(scanner.nextLine());
                if(userRepository.getObjectFromTable(id)) {
                    userRepository.deleteObjectFromTable(id);
                }
    }
     public void getUser(Scanner scanner) throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id of username");
        int id = sc.nextInt();
        userRepository.getObjectFromTable(id);
     }
=======
            System.out.println("Enter the balance to update");
            double balance = Double.parseDouble(scanner.nextLine());

            userRepository.updateUser(new User(id, username, age, balance));
        }
    }

    public void deleteUser(Scanner scanner) throws SQLException {
        System.out.println("Enter the id to delete");
        int id = Integer.parseInt(scanner.nextLine());
        if (userRepository.getUser(id)) {
            userRepository.deleteUser(id);
        }
    }
>>>>>>> Stashed changes

    public void getUser(Scanner scanner) throws SQLException {
        System.out.println("Enter id of user");
        int id = Integer.parseInt(scanner.nextLine());
        userRepository.getUserClass(id);
    }

<<<<<<< Updated upstream
    public void getAllUsers() throws SQLException{
        userRepository.getAllObjectsFromTable();
    }
}
=======
    public void getAllUsers() throws SQLException {
        userRepository.getAllUsers();
    }
}
>>>>>>> Stashed changes
