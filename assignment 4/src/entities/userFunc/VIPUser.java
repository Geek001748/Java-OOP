package entities.userFunc;

public class VIPUser extends User {
    private static final double DISCOUNT_PERCENTAGE = 0.3;

    public VIPUser(UserBuilder builder) {
        super(builder);
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
