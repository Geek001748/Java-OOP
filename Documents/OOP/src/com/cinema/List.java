package com.cinema;

import java.util.ArrayList;
import java.util.LinkedList;

public class List {
    private String director;
    private String category;

    private ArrayList<Movie> movies;

    public List(String director, String category) {
        this.director = director;
        this.category = category;
        this.movies = new ArrayList<Movie>();
    }
    public List(){

    }

    public Movie findMovie(String title) {
        for(Movie check: movies){
            if(check.getTitle().equals(title)) {
                return check;
            }
        }
        return null;
    }
    public void displayMovies() {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        System.out.println("=========================");
    }

    public boolean addMovie(String title, String year, String duration, String budget, double cost, boolean restriction){
        if(findMovie(title) == null) {
            movies.add(new Movie(title, year, duration, budget, cost, restriction));
            System.out.println(title + " Successfully added to the list");
            return true;
        } else{
            System.out.println(title + " already in the list!");
            return false;
        }
    }
    public boolean addToWatchLater(int trackNum, LinkedList<Movie> WatchLater) {
        int id = trackNum - 1;
        if(id > 1 && id <= this.movies.size()) {
            WatchLater.add(this.movies.get(id));
            return true;
        }
        System.out.println("Incorrect trackNumber:" + trackNum + " please rewrite it");
        return false;
    }
    public boolean addToWatchLater(String title, LinkedList<Movie>WatchLater) {
        for(Movie check: this.movies){
            if(check.getTitle().equals(title)) {
                WatchLater.add(check);
                return true;
            }
        }
        System.out.println(title + "there is no such movie in list");
        return false;
    }
}
