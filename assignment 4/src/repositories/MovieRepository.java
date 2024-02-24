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
             int rowsAffected = stmt.executeUpdate();
             if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    movie.setMovieId(generatedKeys.getInt(1));
                }
                System.out.println("Movie added successfully!");
            } else {
                System.out.println("Failed to add movie. Please try again.");
            }
        }


    }


    @Override
    public Movie getMovieClass(int id) throws SQLException {
         try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getById())) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Movie movie = new Movie (
                            rs.getString("movie_name"),
                            rs.getString("movie_genre"));
                } else {
                    System.out.println("Movie wasn't found!");
                }
            }
        }
        return null;
    }

    @Override
    public boolean getMovie(int id) throws SQLException {
         try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getById())) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                 if (rs.next()) {
                Movie movie = new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("movie_name"),
                        rs.getString("movie_genre")
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
    public void updateMovie(Movie movie) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.update())) {
            stmt.setString(1, movie.getMovieName());
            stmt.setString(2, movie.getMovieGenre());


            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Movie updated successfully!");
            } else {
                System.out.println("Failed to update user.");
            }
        }

    }

    @Override
    public void deleteMovie(int id) throws SQLException {
         try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.delete())) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted successfully");
            } else {
                System.out.println("Something  went wrong");
            }
        }


    }

    @Override
    public void getAllMovies() throws SQLException {
           try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getAll())) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Movie movie = new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("movie_name"),
                        rs.getString("movie_genre")
                );
                System.out.println(movie.toString()); // Print movie details using toString()
                }
            }
        }

    }
}
