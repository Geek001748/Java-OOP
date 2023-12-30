package com.cinema;

import java.util.ArrayList;

public class User {
    String name;
    int age;
    double cash;
    ArrayList<User>films;
    public User(String name,int age, double cash, ArrayList<User>films) {
        this.name = name;
        this.age = age;
        this.cash = cash;
        this.films = films;
    }
    public User(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public ArrayList<User> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<User> films) {
        this.films = films;
    }
}
