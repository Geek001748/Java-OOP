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
    public boolean isCorrectTime(String time){
        try {
            if (time.length() > 5) {
                System.out.println("Too many words");
                return false;
            }
            if (time.charAt(2) != '-') {
                System.out.println("Don't forget about '-'");
                return false;
            }
            int hours = Integer.parseInt(time.substring(0, 2));
            int minutes = Integer.parseInt(time.substring(3));
            if (hours < 0 && hours > 23 ||minutes<0||minutes > 59) {
                System.out.println("Wrong Input");
            }
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

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
                if(isCorrectTime(time) && ticketRepository.getMovieByTime(time)) {
                    user.addToUser(user,new Ticket(movieName, ticketRepository.getMoviePrice(movieName), time));
                    ticketRepository.ticketAmount(user);
                }
            }
        }
    }
}
