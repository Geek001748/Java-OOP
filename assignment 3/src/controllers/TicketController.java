package controllers;

import entities.Movie;
import entities.Ticket;
import entities.User;
import repositories.Repositories;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketController {
    private Repositories ticketRepository;
    private ArrayList <Ticket>  ticketArrayList = new ArrayList <>();

    public TicketController(Repositories userRepository) {
        this.ticketRepository = userRepository;
    }
    public void addTicketToUser(Scanner scanner) throws SQLException {
        System.out.println("Please write id of user to add ticket");
        int userId = Integer.parseInt(scanner.nextLine());
        if (ticketRepository.getUser(userId)) {
            User user = ticketRepository.getUserClass(userId);
            System.out.println("Please write name of movie to add ticket");
            String movieName = scanner.nextLine();
            if (ticketRepository.getMovieByName(movieName)) {
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