package repositories;

import entities.movies.Comedy;
import entities.movies.Melodrama;
import entities.movies.Movie;

public class MovieFactory {
    public Movie createMovie(int id, String genre, String name, double percent) {
        switch (genre.toLowerCase()) {
            case "comedy":
                return new Comedy(id, name, genre, percent);
            case "drama":
                return new Melodrama(id, name, genre, percent);
            default:
                return new Movie(id, genre, name);
        }
    }
}