package entities;

import java.util.Scanner;

public class Ticket extends Movie {
    private int ticketId = 0;
    private double ticketPrice = 0;

    public Ticket(int getMovieId, String movieName, String movieGenre, double ticketPrice, int ticketId) {
        super(getMovieId, movieName, movieGenre);
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
