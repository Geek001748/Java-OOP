package entities.userFunc;

import entities.Ticket;

public class User implements Purchasable {
    private int id;
    private String username;
    private int age;
    private double balance;
    private int ticketAmount;
    private boolean premium;

    User(UserBuilder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.age = builder.age;
        this.balance = builder.balance;
        this.ticketAmount = builder.ticketAmount;
        this.premium = builder.premium;
    }

    // Getter methods...

    public static class UserBuilder {
        private int id;
        private String username = "Unknown";
        private int age = 0;
        private double balance = 0.0;
        private int ticketAmount = 0;
        private boolean premium = false;

        public UserBuilder(int id) {
            this.id = id;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder balance(double balance) {
            this.balance = balance;
            return this;
        }

        public UserBuilder ticketAmount(int ticketAmount) {
            this.ticketAmount = ticketAmount;
            return this;
        }

        public UserBuilder premium(boolean premium) {
            this.premium = premium;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public User() {
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

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public void upgradeToPremium() {
        if (!premium) {
            this.premium = true;
            System.out.println("Congratulations! You are now a premium user.");
            System.out.println("Enjoy exclusive benefits and discounts.");
        }
    }


    public String getPremium() {
        if (this.premium) {
            return "This is VIP user";
        } else {
            return "This is not VIP user";
        }
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

    public boolean isPremium() {
        return premium;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername()
                + "\nAge : " + getAge()
                + "\nBalance: " + getBalance()
                + "\nPremium: " + getPremium();
    }
}