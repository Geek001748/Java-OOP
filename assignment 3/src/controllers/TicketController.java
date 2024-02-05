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
    public void addTicketToUser(Scanner scanner) throws SQLException, NumberFormatException {
        System.out.println("Please write id of user to add ticket");
        int userId = Integer.parseInt(scanner.nextLine());
        if (ticketRepository.getUser(userId)) {
            User user = ticketRepository.getUserClass(userId);
            System.out.println("Please write name of movie to add ticket");
            String movieName = scanner.nextLine();
            if (ticketRepository.getMovieByName(movieName.toLowerCase())) {
                ticketRepository.getAllTimes(movieName.toLowerCase());
                System.out.println("Enter movie time (xx:xx):");
                String time;
                do {
                    time = scanner.nextLine();
                    if(isCorrectTime(time)){
                        break;
                    }
                } while(true);
                if(ticketRepository.getMovieByTime(time)) {
                    Ticket ticket = new Ticket(movieName, ticketRepository.getMoviePrice(movieName.toLowerCase()), time);
                    System.out.println("How many tickets you want to buy ? We have " +ticketRepository.getTicketAmountForAll(ticket.getTime()) +" tickets.");
                    int n = scanner.nextInt();

                    if (n<=ticketRepository.getTicketAmountForAll(ticket.getTime())){
                        if(user.getBalance()>=ticket.getPrice() * n) {
                            for (int i = 0; i < n; i++) {
                                user.getUserTickets().add(ticket);
                            }
                            user.setBalance(user.getBalance()-ticket.getPrice() * n);
                            ticketRepository.ticketAmount(user,n);
                            ticketRepository.updateTicketAmountForAll(ticket.getTime(),n);
                        }
                        else {
                            System.out.println("You have not got enough money. Please, top up your balance.");
                            System.out.println("Your current balance: "+user.getBalance());
                            System.out.println("Ticket price: " + ticket.getPrice());
                        }
                    } else{
                        System.out.println("We do not have so many tickets. Enter valid number");
                    }
                }
            }
        }
    }
}
