package repositories;

import Queries.MovieQueries;
import data.IDB;
import entities.Movie;
import repositories.interfaces.IMovieRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRepository implements IMovieRepository {

    private final IDB db;
    private final MovieQueries q;

    public MovieRepository(IDB db) {
        this.db = db;
        this.q = new MovieQueries(); // Initialize MovieQueries
    }

    @Override
    public void addMovie(Movie movie) throws SQLException {
        String query = q.add();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, movie.getMovieName());
            stmt.setString(2, movie.getMovieGenre());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        movie.setMovieId(generatedKeys.getInt(1));
                    }
                }
                System.out.println("Movie added successfully!");
            } else {
                System.out.println("Failed to add movie. Please try again.");
            }
        }
    }

    @Override
    public Movie getMovieClass(int id) throws SQLException {
        String query = q.getById();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Movie(rs.getString("movie_name"), rs.getString("movie_genre"));
                } else {
                    System.out.println("Movie wasn't found!");
                }
            }
        }
        return null;
    }

    @Override
    public boolean getMovie(int id) throws SQLException {
        String query = q.getById();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Movie movie = new Movie(rs.getInt("movie_id"), rs.getString("movie_name"), rs.getString("movie_genre"));
                    System.out.println(movie.toString());
                    return true;
                } else {
                    System.out.println("Movie was not found");
                }
            }
        }
        return false;
    }

    @Override
    public void updateMovie(Movie movie) throws SQLException {
        String query = q.update();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, movie.getMovieName());
            stmt.setString(2, movie.getMovieGenre());
            stmt.setInt(3, movie.getMovieId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Movie updated successfully!");
            } else {
                System.out.println("Failed to update movie.");
            }
        }
    }

    @Override
    public void deleteMovie(int id) throws SQLException {
        String query = q.delete();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted successfully");
            } else {
                System.out.println("Something went wrong");
            }
        }
    }

    @Override
    public void getAllMovies() throws SQLException {
        String query = q.getAll();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Movie movie = new Movie(rs.getInt("movie_id"), rs.getString("movie_name"), rs.getString("movie_genre"));
                System.out.println(movie.toString());
            }
        }
    }
}
