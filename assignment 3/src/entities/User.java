package entities;

import entities.Ticket;

import java.util.ArrayList;

public class User {
    private int id;
    private String username = "Unknown";
    private int age = 0;
    private double balance =0.0;
    private ArrayList<Ticket> userTickets = new ArrayList<>();
    private int ticketAmount = 0;
    public User(Ticket ticket){
        userTickets.add(ticket);
    }
<<<<<<< Updated upstream
=======
    Repositories repositories;
    private int ticketAmount = 0;

    public void  addToUser(User user,Ticket ticket) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many tickets you want to buy ? We have " +repositories.getTicketAmountForAll(ticket.getTicketId()) +" tickets.");
        int n = scanner.nextInt();

        if (n<=repositories.getTicketAmountForAll(ticket.getTicketId())){
                if(user.getBalance()>=n*ticket.getPrice()) {
                    for (int i = 1; i <= n;i++){
                        getUserTickets().add(ticket);
                    }
                    user.setBalance(user.getBalance()-n*ticket.getPrice());
                    ticketAmount += n;
                }
                else {
                    System.out.println("You have not got enough money. Please, top up your balance.");
                    System.out.println("Your current balance: "+user.getBalance());
                    System.out.println("Ticket price: " + ticket.getPrice() * n);
                }
        } else{
            System.out.println("We do not have so many tickets. Enter valid number");
        }

    }
>>>>>>> Stashed changes

    public void getAllUserTickets() {
        for (Ticket ticket: userTickets){
            System.out.println(ticket.toString());
        }
    }
    public ArrayList<Ticket> getUserTickets() {
        return userTickets;
    }

    public int getTicketArraySize(){
        this.ticketAmount = getUserTickets().size();
        return getUserTickets().size();
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public User() {
        this.id = id;
        this.username = username;
        this.age = age;
        this.balance = balance;
        this.userTickets = userTickets;
    }

    public User(int id, String username, int age, double balance, ArrayList userTickets) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.balance = balance;
        this.userTickets = userTickets;
    }
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
    public User(String username, int age, double balance, int ticketAmount) {
        this.username = username;
        this.age = age;
        this.balance = balance;
        this.ticketAmount = ticketAmount;
    }
    public User(String username, int age, double balance) {
        this.username = username;
        this.age = age;
        this.balance = balance;
        this.ticketAmount = 0;
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

