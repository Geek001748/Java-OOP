package controllers;

import entities.Ticket;
<<<<<<< Updated upstream
import entities.User;
import repositories.TicketTable;
=======
import repositories.Repositories;
>>>>>>> Stashed changes

import java.sql.SQLException;
import java.util.Scanner;

public class TicketController {
<<<<<<< Updated upstream
    private TicketTable ticketRepository;
    private MovieController movieRepository;
    private ArrayList <Ticket>  ticketArrayList = new ArrayList <>();

    public TicketController(TicketTable userRepository) {
        this.ticketRepository = userRepository;
    }
    public void addTicketToUser(Scanner scanner) throws SQLException {
        System.out.println("Please write id of user to add ticket");
        int userId = Integer.parseInt(scanner.nextLine());
        if (ticketRepository.getObjectFromTable(userId)) {
            User user = (User)ticketRepository.getObjectClass(userId);
            System.out.println("Please write name of movie to add ticket");
            if (movieRepository.getMovieByName(scanner)) {
                System.out.print("Enter movie time xx:xx");
                String time = scanner.nextLine();
                ticketRepository.ticketAmount(user);
                ticketRepository.addTicket(userId, new Ticket(movieName, ticketRepository.getMovieClass(id), time));
            }
        }
    }
    public void addTicket(Scanner scanner) throws SQLException {
        System.out.println("Please write name of movie to create ticket");
            String movieName = scanner.nextLine();
            if (ticketRepository.getMovieByName(movieName)) {
                System.out.print("Enter movie time xx:xx");
                String time = scanner.nextLine();
                ticketRepository.ticketAmount(user);
                ticketRepository.addTicket(userId, new Ticket(movieName, ticketRepository.getMovieClass(id), time));
            }
    }
}
printMovieTimes(){
    System.out.println("1. 8:00 - 10:00");
    System.out.println("2. 6");
    System.out.println("3.");
    System.out.println("4.");
    System.out.println("5.");
}
switch(choice)
=======
    private Repositories ticketRepository;

    public TicketController(Repositories ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void addTicket(Scanner scanner) throws SQLException {
        System.out.print("Enter movie name: ");
        String movieName = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter time: ");
        String time = scanner.nextLine();

        System.out.print("Enter ticket amount: ");
        int ticketAmount = Integer.parseInt(scanner.nextLine());

        ticketRepository.addTicketToTable(new Ticket(movieName, price, time, ticketAmount));
        System.out.println("Ticket added successfully!");
    }

    public void updateTicket(Scanner scanner) throws SQLException {
        System.out.println("Enter the id of the ticket to update");
        int id = Integer.parseInt(scanner.nextLine());
        if (ticketRepository.getTicket(id)) {
            System.out.println("Enter the movie name to update: ");
            String movieName = scanner.nextLine();

            System.out.println("Enter the price to update: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.println("Enter the time to update: ");
            String time = scanner.nextLine();

            System.out.println("Enter the ticket amount to update: ");
            int ticketAmount = Integer.parseInt(scanner.nextLine());

            ticketRepository.updateTicket(new Ticket(id, movieName, price, time, ticketAmount));
            System.out.println("Ticket updated successfully!");
        }
    }

    public void deleteTicket(Scanner scanner) throws SQLException {
        System.out.println("Enter the id of the ticket to delete");
        int id = Integer.parseInt(scanner.nextLine());
        if (ticketRepository.getTicket(id)) {
            ticketRepository.deleteTicket(id);
            System.out.println("Ticket deleted successfully!");
        }
    }

    public void getTicket(Scanner scanner) throws SQLException {
        System.out.println("Enter id of the ticket");
        int id = Integer.parseInt(scanner.nextLine());
        ticketRepository.getTicketClass(id);
    }

    public void getAllTickets() throws SQLException {
        ticketRepository.getAllTickets();
    }
}
>>>>>>> Stashed changes
