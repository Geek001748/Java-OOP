package entities;

import java.util.Scanner;

public class Ticket extends Movie {
    private int ticketId = 0;
    private double ticketPrice = 0;

    public Ticket(int ticketId, double ticketPrice) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
    }
    public Ticket(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Ticket(int MovieId, String movieName, String movieGenre, int ticketId, double ticketPrice) {
        super(MovieId, movieName, movieGenre);
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
    }


    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTicketId() {
        return ticketId;
    }
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + getMovieName() + "\nTicket price: " + getTicketPrice();
    }
}
