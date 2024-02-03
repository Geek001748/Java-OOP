package repositories;

import entities.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieTable extends SQLConnection implements TableManagement{
    @Override
    public void createTable() throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
               statement.executeUpdate(Queries.createTicketTable());
               System.out.println("Table Movies was created");
        }
    }
    @Override
    public void addToTable(Object object) throws SQLException{
        Movie movie = (Movie) object;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.addMovie(),
                    Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, movie.getMovieName());
                preparedStatement.setDouble(2, movie.getPrice());


                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        movie.setGetMovieId(generatedKeys.getInt(1));
                    }
                    System.out.println("Movie added successfully!");
                } else {
                    System.out.println("Failed to add movie. Please try again.");
                }
        }
    }
    @Override
    public boolean getObjectFromTable(int id) throws SQLException{
        boolean isFound = false;
        try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getMovie(id));) {
                if (resultSet.next()) {
                    isFound = true;
                    objectInfo(new Movie(resultSet.getInt("id"),resultSet.getString("movieName"),resultSet.getDouble("price")));
                }else{
                    System.out.println("Movie wasn't found!");
                }
        }
        return isFound;
    }
    @Override
    public void updateObjectTable(Object object) throws SQLException {
        Movie movie = (Movie)object;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(Queries.updateMovie(movie.getMovieId()));) {
                preparedStatement.setString(1, movie.getMovieName());
                preparedStatement.setDouble(2, movie.getPrice());
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected > 0) {
                    System.out.println("Movie updated successfully!");
                } else {
                    System.out.println("Failed to update movie.");
                }
        }
    }
    @Override
    public void deleteObjectFromTable(int id) throws  SQLException{
        try (Statement statement = getConnection().createStatement();){
                int rowsAffected = statement.executeUpdate(Queries.deleteMovie(id));
                if (rowsAffected > 0) {
                    System.out.println("Deleted successfully");
                } else {
                    System.out.println("Something  went wrong");
                }
        }
    }
    @Override
    public void getAllObjectsFromTable() throws SQLException {
        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(Queries.getAllMovies());) {
                while (resultSet.next()) {
                    System.out.println();
                    objectInfo(new Movie(resultSet.getInt("id"),resultSet.getString("movieName"),resultSet.getDouble("price")));
                }
            }
        }
    public boolean getMovieByName(String name) throws SQLException{
        boolean isFound = false;
        try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getMovieByName(name));) {
                if (resultSet.next()) {
                    isFound = true;
                    objectInfo(new Movie(resultSet.getInt("id"),resultSet.getString("movieName"),resultSet.getDouble("price")));
                }else{
                    System.out.println("Movie wasn't found!");
                }
        }
        return isFound;
    }
    @Override
    public Movie getObjectClass(int id) throws SQLException{
        try (Statement statement = getConnection().createStatement();ResultSet resultSet = statement.executeQuery(Queries.getMovie(id));) {
                if (resultSet.next()) {
                    return new Movie(resultSet.getInt("id"),resultSet.getString("movieName"),resultSet.getDouble("price"));
                }else{
                    System.out.println("Movie wasn't found!");
                }
        }
        return null;
    }
    @Override
    public void objectInfo(Object object){
        Movie movie = (Movie)object;
        System.out.println("Movie ID:  " + movie.getMovieId());
        System.out.println("Movie name: " + movie.getMovieName());
        System.out.println("   Price:   " + movie.getPrice());
    }
}
