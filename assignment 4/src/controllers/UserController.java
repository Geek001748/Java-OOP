package controllers;

import entities.Ticket;
import entities.userFunc.User;
import repositories.TicketRepository;
import repositories.UserRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class UserController {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository = new TicketRepository();
    private final Scanner scanner;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("0. Exit");
            System.out.println("User Management System");
            System.out.println("1. Add User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. Get User by ID");
            System.out.println("5. Top up balance");
            System.out.println("6. Buy Ticket");
            System.out.println("7. Get All Users with Tickets");
            System.out.println("8. Get User by ID with Tickets");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    case 1:
                        addUser();
                        break;
                    case 2:
                        updateUser();
                        break;
                    case 3:
                        deleteUser();
                        break;
                    case 4:
                        getUserById();
                        break;
                    case 5:
                        topUpBalance();
                        break;
                    case 6:
                        buyTicket();
                        break;
                    case 7:
                        getAllUsersWithTickets();
                        break;
                    case 8:
                        getUserByIdWithTickets();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addUser() throws SQLException {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter age:");
        int age = scanner.nextInt();

        System.out.println("Enter balance:");
        double balance = scanner.nextDouble();

        User user = new User(username, age, balance);
        userRepository.addUser(user);
    }

    private void updateUser() throws SQLException {
        System.out.println("Enter user ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter new username:");
        String username = scanner.nextLine();

        System.out.println("Enter new age:");
        int age = scanner.nextInt();

        System.out.println("Enter new balance:");
        double balance = scanner.nextDouble();

        User user = new User(id, username, age, balance);
        userRepository.updateUser(user);
    }

    private void deleteUser() throws SQLException {
        System.out.println("Enter user ID:");
        int id = scanner.nextInt();
        userRepository.deleteUser(id);
    }

    private void getUserById() throws SQLException {
        System.out.println("Enter user ID:");
        int id = scanner.nextInt();
        User user = userRepository.getUserClass(id);
        if (user != null) {
            System.out.println(user.toString());
        } else {
            System.out.println("User not found!");
        }
    }

    private void topUpBalance() throws SQLException {
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        System.out.println("Enter amount to top up:");
        double amount = scanner.nextDouble();
        userRepository.topUpBalance(amount, userId);
    }

    private void buyTicket() throws SQLException {
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        System.out.println("Enter ticket ID:");
        int ticketId = scanner.nextInt();

        if(ticketRepository.getTicket(ticketId)) {

            scanner.nextLine(); // Consume newline

            System.out.println("Enter ticket amount:");
            int amount = scanner.nextInt();
            Ticket ticketInTable = ticketRepository.getTicketClass(ticketId);
            Ticket ticketForUser = new Ticket(ticketInTable.getTicketPrice(), userId, 0);
            userRepository.buyTicket(amount, ticketForUser);
        } else {
            System.out.println("Ticket wasn't found!");
        }
    }

    private void getAllUsersWithTickets() throws SQLException {
        userRepository.getAllUsersInTable();
    }

    private void getUserByIdWithTickets() throws SQLException {
        System.out.println("Enter user ID:");
        int id = scanner.nextInt();
        userRepository.getUserByIdInTable(id);
    }

}
