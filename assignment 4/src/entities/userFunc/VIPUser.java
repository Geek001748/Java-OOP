package entities.userFunc;

import entities.Ticket;
import repositories.TicketRepository;

import java.sql.SQLException;

public class VIPUser extends User {
    private static final double DISCOUNT_PERCENTAGE = 0.3;

    public VIPUser(int id, String username, int age, double balance) {
        super(id, username, age, balance, 0); // VIP users start with 0 tickets
    }
    @Override
    public boolean buyTicket(double ticketPrice) {
        double discountedPrice = ticketPrice * (1 - DISCOUNT_PERCENTAGE);
        if (getBalance() >= discountedPrice) {
            setBalance(getBalance() - discountedPrice);
            setTicketAmount(getTicketAmount() + 1);
            System.out.println("Ticket purchased successfully with VIP discount!");
            return true;
        } else {
            System.out.println("Insufficient balance to buy the ticket.");
            return false;
        }
    }
}
