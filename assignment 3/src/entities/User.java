package entities;

import entities.Ticket;
import repositories.Repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private int id;
    private String username = "Unknown";
    private int age = 0;
    private double balance =0.0;
    private ArrayList<Ticket> userTickets = new ArrayList<>();
    public User(Ticket ticket){
        userTickets.add(ticket);
    }
    public void getAllUserTickets() {
        for (Ticket ticket: userTickets){
            System.out.println(ticket.toString());
        }
    }
    public ArrayList<Ticket> getUserTickets() {
        return userTickets;
    }

    public int getTicketArraySize(){
        return getUserTickets().size();
    }
    public User() {
        this.id = id;
        this.username = username;
        this.age = age;
        this.balance = balance;
        this.userTickets = userTickets;
    }
    public User(int id, String username, int age, double balance) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.balance = balance;
    }
    public User(String username, int age, double balance) {
        this.username = username;
        this.age = age;
        this.balance = balance;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

