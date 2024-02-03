package entities;

import java.util.Scanner;

public class Ticket extends Movie {
    private String time;
    private int ticketId;

    public Ticket(int getMovieId, String movieName, double price, String time, int ticketId) {
        super(getMovieId, movieName, price);
        this.time = time;
        this.ticketId = ticketId;
    }
     public Ticket(int ticketId,String movieName, double price, String time) {
        super(movieName,price);
        this.time = time;
        this.ticketId = ticketId;
    }
    public Ticket(int movieId, double price, String time) {
        super(movieId,price);
        this.time = time;
    }
    public Ticket(int movieId, double price) {
        super(movieId,price);
    }
    public String getTime() {
        return time;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    @Override
    public String toString() {
        return  "Movie name: " + getMovieName() + "\nPrice : " + getPrice()+ "\nTime: " + getTime();
    }
}
