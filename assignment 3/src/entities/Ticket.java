package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Ticket extends Movie {
    private String time = "";
    private int ticketId;
    private int ticketAmount = 0;
    public Ticket(int getMovieId, String movieName, double price, String time, int ticketId) {
        super(getMovieId, movieName, price);
        this.time = time;
        this.ticketId = ticketId;
    }
    public Ticket(String movieName, double price, String time, int ticketAmount){
        super(movieName,price);
        this.time = time;
        this.ticketAmount = ticketAmount;
    }
     public Ticket(int ticketId,String movieName, double price, String time) {
        super(movieName,price);
        this.time = time;
        this.ticketId = ticketId;
    }
    public Ticket(String movieName, double price, String time) {
        super(movieName,price);
        this.time = time;
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

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
//    public String isCorrectTime(String time){
//        for (char c: time){
//            if (c!=)
//        }
//    }

    @Override
    public String toString() {
        return  "Movie name: " + getMovieName() + "\nPrice : " + getPrice()+ "\nTime: " + getTime();
    }
}
