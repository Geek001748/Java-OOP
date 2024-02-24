package repositories;

import Queries.MovieQueries;
import Queries.UserQueries;
import data.IDB;
import entities.Movie;
import repositories.interfaces.IMovieRepository;

import java.sql.*;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class MovieRepository implements IMovieRepository {

    private final IDB db;
    private MovieQueries q;

    public MovieRepository(IDB db) {
        this.db = db;
    }

    @Override
    public void addMovie(Movie movie) throws SQLException {
         try (Connection conn = db.getConnection();
              PreparedStatement stmt = conn.prepareStatement(q.add())) {
            stmt.setString(1, movie.getMovieName());
            stmt.setString(2, movie.getMovieGenre());
            stmt.executeUpdate();
        }


    }

    @Override
    public boolean getMovie(int id) throws SQLException {
         try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getById())) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                 if (rs.next()) {
                Movie movie = new Movie(
                        rs.getInt("Movieid"),
                        rs.getString("movieName"),
                        rs.getString("movieGenre")
                );
                System.out.println(movie.toString()); // Print movie details using toString()
                return true;
            }
                 else{
                     System.out.println("Movie was not found");
                 }
            }
        } return false;
    }

    @Override
    public Movie getMovieByName(String name) throws SQLException {
   try (Statement statement = db.getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(MovieQueries.getMovieByName(name));) {
                if (resultSet.next()) {
                    return new Movie(resultSet.getInt("movie_id"),resultSet.getString("movie_name"),resultSet.getString("movie_genre"));
                }else{
                    System.out.println("Movie wasn't found!");
                }
            }
            return null;


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
