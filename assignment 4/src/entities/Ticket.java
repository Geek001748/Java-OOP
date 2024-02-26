package entities;

import entities.movies.Movie;

public class Ticket extends Movie {
    private int ticketId = 0;
    private int userId = 0;
    private int movieId = 0;
    private double ticketPrice = 0;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public int getMovieId() {
        return movieId;
    }
    public Ticket(int ticketId, double ticketPrice) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
    }
    public Ticket(double ticketPrice, int userId, int movieId) {
        this.ticketPrice = ticketPrice;
        this.userId = userId;
    }

    public Ticket(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Ticket(int MovieId, String movieName, String movieGenre, int ticketId, double ticketPrice) {
        super(MovieId, movieName, movieGenre);
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
    }

    public Ticket(double ticketPrice, String movieName, String movieGenre) {
        super(movieName, movieGenre);
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

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + getTicketId() + "\nTicket price: " + getTicketPrice();
    }
}
