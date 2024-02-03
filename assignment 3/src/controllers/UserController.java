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

    public void addUser(Scanner scanner) throws SQLException{
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter balance: ");
        double balance = Double.parseDouble(scanner.nextLine());

        userRepository.addToTable(new User(username, age, balance));
    }
    public void updateUser(Scanner scanner) throws SQLException{
            System.out.println("Enter the id to update");
            int id = Integer.parseInt(scanner.nextLine());
            if(userRepository.getObjectFromTable(id)) {
                System.out.println("Enter the surname to update");
                String username = scanner.nextLine();

                System.out.println("Enter the age to update");
                int age = Integer.parseInt(scanner.nextLine());

                System.out.println("Enter the balance to update");
                double balance = Double.parseDouble(scanner.nextLine());

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


    public void getAllUsers() throws SQLException{
        userRepository.getAllObjectsFromTable();
    }
}