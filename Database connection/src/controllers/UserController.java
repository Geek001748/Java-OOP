package controllers;
import entities.User;
import repositories.UserRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        System.out.print("Enter balance: ");
        double balance = scanner.nextDouble();

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setAge(age);
        newUser.setBalance(balance);

        userRepository.addUser(newUser);
    }
    public void updateUser() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the id to update");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter the surname to update");
            String username = scanner.nextLine();

            System.out.println("Enter the age to update");
            int age = scanner.nextInt();

            System.out.println("Enter the balance to update");
            double balance = scanner.nextDouble();

            userRepository.updateUser(id, username, age, balance);

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
    public void deleteUser() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the id to delete");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                userRepository.deleteUser(id);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("SQLException: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
            }

        }
    }
     public void getUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id of username");
        int id = sc.nextInt();

        User user = userRepository.getUser(id);
         System.out.println("Username: "+user.getUsername());
         System.out.println("Age: "+user.getAge());
         System.out.println("Balance: "+user.getBalance());
     }


    public void getAllUsers() {
        userRepository.getAllUsers();
    }
}
