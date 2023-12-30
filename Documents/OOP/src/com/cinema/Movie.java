package com.cinema;

public class Movie {
    String title;
    String year;
    String duration;
    String budget;
    boolean restriction;
    double cost;
    public Movie(String title, String year, String duration, String budget, double cost, boolean restriction) {
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.budget = budget;
        this.cost = cost;
        this.restriction = restriction;
    }
    public Movie(){
        
    }
    public String getTitle(){
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public String getBudget() {
        return budget;
    }

    public double getCost() {
        return cost;
    }

    public boolean isRestriction() {
        return restriction;
    }

    @Override
    public String toString(){
        return "Movie { " + "title= " + title + ", year= " + year
                +", duration= " + duration + ", budget= " + budget + ", cost=" + cost +" }";
    }
}
