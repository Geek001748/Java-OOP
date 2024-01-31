package entities;

public class User {
    private int id;
    private String username;
    private int age;
    private double balance;
    public User() {
        this.id = id;
        this.username = username;
        this.age = age;
        this.balance = balance;
    }
    public User(int id, String username, int age, double balance) {
        this.id = id;
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
