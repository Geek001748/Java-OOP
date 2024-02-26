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
            if (movie.getMovieGenre().equals("melodrama")){
            stmt.setDouble(3,30);
            }
            if (movie.getMovieGenre().equals("comedy")){
            stmt.setDouble(3,50);
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
                    if(rs.getString("movie_genre").equals("melodrama")){
                        Movie movie = new Melodrama(rs.getInt("movie_id"), rs.getString("movie_name"), rs.getString("movie_genre"),rs.getDouble("movie_promotion_percent"));

                    System.out.println(movie.toString());
                    }else{
                        Movie movie = new Comedy(rs.getInt("movie_id"), rs.getString("movie_name"), rs.getString("movie_genre"),rs.getDouble("movie_promotion_percent"));

                    System.out.println(movie.toString());
                    }
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
            stmt.setInt(4, movie.getMovieId());
            if(movie.getMovieGenre().equals("melodrama")){
               stmt.setInt(3, 30);
            }
             if(movie.getMovieGenre().equals("comedy")){
               stmt.setInt(3, 50);
            }


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

    public boolean getUserByIdInTable(int id) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(q.getById())) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                   return true;
                } else {
                    return false;
                }
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
                if(rs.getString("movie_genre").equals("melodrama")){
                    Movie movie = new Melodrama(rs.getInt("movie_id"), rs.getString("movie_name"), rs.getString("movie_genre"),rs.getDouble("movie_promotion_percent"));
                    System.out.println(movie.toString());
                }
                if(rs.getString("movie_genre").equals("comedy")){
                    Movie movie = new Comedy(rs.getInt("movie_id"), rs.getString("movie_name"), rs.getString("movie_genre"),rs.getDouble("movie_promotion_percent"));
                    System.out.println(movie.toString());
                }

            }
        }
    }

       public void getAllMovies2() throws SQLException {
        MovieFactory movieFactory = new MovieFactory();
        String query = q.getAll();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
               int id= rs.getInt("movie_id");
               String name = rs.getString("movie_name");
               String genre = rs.getString("movie_genre");
               double percent = rs.getDouble("movie_promotion_percent");
               Movie movie = movieFactory.createMovie(id,name,genre,percent);
               movie.toString();
            }
        }
    }
}
