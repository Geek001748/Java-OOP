package repositories.interfaces;

import entities.Movie;

import java.sql.SQLException;
import java.util.List;

public interface IMovieRepository {
    void addMovie(Movie movie) throws SQLException;
    boolean getMovie(int id) throws SQLException;
    boolean getMovieByName(String name) throws SQLException;
    Movie getMovieClass(int id) throws SQLException;
    Movie getMovieClassByName(String name) throws SQLException;
    void updateMovie(Movie movie) throws SQLException;
    void deleteMovie(int id) throws SQLException;
    List<Movie> getAllMovies() throws SQLException;
}