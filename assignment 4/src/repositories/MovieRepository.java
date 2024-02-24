package repositories;

import entities.Movie;
import repositories.interfaces.IMovieRepository;

import java.sql.SQLException;
import java.util.List;

public class MovieRepository implements IMovieRepository {

    @Override
    public void addMovie(Movie movie) throws SQLException {

    }

    @Override
    public boolean getMovie(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean getMovieByName(String name) throws SQLException {
        return false;
    }

    @Override
    public Movie getMovieClass(int id) throws SQLException {
        return null;
    }

    @Override
    public Movie getMovieClassByName(String name) throws SQLException {
        return null;
    }

    @Override
    public void updateMovie(Movie movie) throws SQLException {

    }

    @Override
    public void deleteMovie(int id) throws SQLException {

    }

    @Override
    public void getAllMovies() throws SQLException {
        return ;
    }
}
