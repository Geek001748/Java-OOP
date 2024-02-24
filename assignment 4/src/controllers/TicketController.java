package controllers;

import entities.Ticket;
import repositories.TicketRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class TicketController {
    private final TicketRepository ticketRepository;
    private final Scanner scanner;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Ticket Management System");
            System.out.println("0. Exit");
            System.out.println("1. Add Ticket");
            System.out.println("2. Update Ticket");
            System.out.println("3. Delete Ticket");
            System.out.println("4. Get Ticket by ID");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    case 1:
                        addTicket();
                        break;
                    case 2:
                        updateTicket();
                        break;
                    case 3:
                        deleteTicket();
                        break;
                    case 4:
                        getTicketById();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addTicket() throws SQLException {
        System.out.println("Enter ticket price:");
        double price = scanner.nextDouble();

        Ticket ticket = new Ticket(price);
        ticketRepository.addTicket(ticket);
    }

    private void updateTicket() throws SQLException {
        System.out.println("Enter ticket ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter new ticket price:");
        double price = scanner.nextDouble();

        Ticket ticket = new Ticket(id, price);
        ticketRepository.updateTicket(ticket);
    }

    private void deleteTicket() throws SQLException {
        System.out.println("Enter ticket ID:");
        int id = scanner.nextInt();
        ticketRepository.deleteTicket(id);
    }

    private void getTicketById() throws SQLException {
        System.out.println("Enter ticket ID:");
        int id = scanner.nextInt();
        Ticket ticket = ticketRepository.getTicketClass(id);
        if (ticket != null) {
            System.out.println(ticket.toString());
        } else {
            System.out.println("Ticket not found!");
        }
    }
}
