package entities.userFunc;

import entities.Ticket;

interface Purchasable {
    void topUpBalance(double amount);
    boolean buyTicket(double ticketPrice);
}