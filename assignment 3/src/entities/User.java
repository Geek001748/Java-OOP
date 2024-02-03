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
    Repositories repositories;

    public void addToUser(User user,Ticket ticket) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many tickets you want to buy ? We have " +repositories.getTicketAmountForAll(ticket.getTicketId()) +" tickets.");
        int n = scanner.nextInt();

        if (n<=repositories.getTicketAmountForAll(ticket.getTicketId())){
            for (int i =1; i<=n;i++){
                if(user.getBalance()>=ticket.getPrice()) {
                    getUserTickets().add(ticket);
                    user.setBalance(user.getBalance()-ticket.getPrice());
                }
                else {
                    System.out.println("You have not got enough money. Please, top up your balance.");
                    System.out.println("Your current balance: "+user.getBalance());
                    System.out.println("Ticket price: " + ticket.getPrice());
                    break;
                }
            }

        } else{
            System.out.println("We do not have so many tickets. Enter valid number");
        }

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

