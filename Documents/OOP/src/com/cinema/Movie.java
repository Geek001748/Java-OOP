package com.cinema;

public class Movie {
    String title;
    String year;
    String duration;
    String budget;
    String cost;
    public Movie(String title, String year, String duration, String budget, String cost) {
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.budget = budget;
        this.cost = cost;
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

    public String getCost() {
        return cost;
    }

    @Override
    public String toString(){
        return "Movie { " + "title= " + title + ", year= " + year
                +", duration= " + duration + ", budget= " + budget + ", cost=" + cost +" }";
    }
}
