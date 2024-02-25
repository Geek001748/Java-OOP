package entities.userFunc;

import entities.Ticket;
public class User implements Purchasable {
    private int id;
    private String username = "Unknown";
    private int age = 0;
    private double balance = 0.0;
    private int ticketAmount = 0;

    public User() {}

    public User(int id, String username, int age, double balance, int ticketAmount) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.balance = balance;
        this.ticketAmount = ticketAmount;
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

    public int getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
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

    public void topUpBalance(double amount) {
        balance += amount;
    }

    @Override
    public boolean buyTicket(double ticketPrice) {
        if (balance >= ticketPrice) {
            balance -= ticketPrice;
            ticketAmount++;
            return true;
        } else {
            System.out.println("Insufficient balance to buy the ticket.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Username: " + getUsername() + "\nAge : " + getAge() + "\nBalance: " + getBalance();
    }
}