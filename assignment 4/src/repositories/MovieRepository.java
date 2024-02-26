package repositories;

import Queries.MovieQueries;
import data.IDB;
import entities.movies.Comedy;
import entities.movies.Melodrama;
import entities.movies.Movie;
import repositories.interfaces.IMovieRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRepository implements IMovieRepository {

    private final IDB db;
    private final MovieQueries query;

    public MovieRepository(IDB db) {
        this.db = db;
        this.query = new MovieQueries();
    }

    @Override
    public void addMovie(Movie movie) throws SQLException {
        String addQuery = query.add();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(addQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, movie.getMovieName());
            stmt.setString(2, movie.getMovieGenre());
            if (movie.getMovieGenre().equals("melodrama")) {
                stmt.setDouble(3, 30);
            } else if (movie.getMovieGenre().equals("comedy")) {
                stmt.setDouble(3, 50);
            }

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
    public boolean getMovie(int id) throws SQLException {
        String getQuery = query.getById();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(getQuery)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Movie movie;
                    if (rs.getString("movie_genre").equals("melodrama")) {
                        movie = new Melodrama(rs.getInt("movie_id"), rs.getString("movie_name"), rs.getString("movie_genre"), rs.getDouble("movie_promotion_percent"));
                    } else {
                        movie = new Comedy(rs.getInt("movie_id"), rs.getString("movie_name"), rs.getString("movie_genre"), rs.getDouble("movie_promotion_percent"));
                    }
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
    public Movie getMovieClass(int id) throws SQLException {
        String getQuery = query.getById();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(getQuery)) {
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
    public void updateMovie(Movie movie) throws SQLException {
        String updateQuery = query.update();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setString(1, movie.getMovieName());
            stmt.setString(2, movie.getMovieGenre());
            stmt.setInt(4, movie.getMovieId());
            int promotionPercent = (movie.getMovieGenre().equals("melodrama")) ? 30 : 50;
            stmt.setInt(3, promotionPercent);

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
        String deleteQuery = query.delete();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
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
        String getAllQuery = query.getAll();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(getAllQuery);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Movie movie;
                if (rs.getString("movie_genre").equals("melodrama")) {
                    movie = new Melodrama(rs.getInt("movie_id"), rs.getString("movie_name"), rs.getString("movie_genre"), rs.getDouble("movie_promotion_percent"));
                } else {
                    movie = new Comedy(rs.getInt("movie_id"), rs.getString("movie_name"), rs.getString("movie_genre"), rs.getDouble("movie_promotion_percent"));
                }
                System.out.println(movie.toString());
            }
        }
    }

    public void getMoviesWithPromotion(String genre) throws SQLException {
        String getPromotionQuery = query.getAllMoviesWithPromotionByGenre();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(getPromotionQuery)) {
            stmt.setString(1, genre);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Movie movie;
                    if (genre.equalsIgnoreCase("melodrama")) {
                        movie = new Melodrama(rs.getInt("movie_id"), rs.getString("movie_name"), rs.getString("movie_genre"), rs.getDouble("movie_promotion_percent"));
                    } else if (genre.equalsIgnoreCase("comedy")) {
                        movie = new Comedy(rs.getInt("movie_id"), rs.getString("movie_name"), rs.getString("movie_genre"), rs.getDouble("movie_promotion_percent"));
                    } else {
                        movie = new Movie(rs.getInt("movie_id"), rs.getString("movie_name"), rs.getString("movie_genre"));
                    }
                    System.out.println(movie.toString());
                }
            }
        }
    }
}
