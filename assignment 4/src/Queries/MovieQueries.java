package Queries;

public class MovieQueries implements IQueries {
    @Override
    public String createTable() {
        return "CREATE TABLE IF NOT EXISTS movies ("
                + "movie_id SERIAL PRIMARY KEY,"
                + "movie_name VARCHAR(50) NOT NULL,"
                + "movie_genre VARCHAR(50) NOT NULL)";
    }

    @Override
    public String add() {
        return "INSERT INTO movies (movie_name, movie_genre, price) VALUES (?, ?)";
    }

    @Override
    public String update() {
        return "UPDATE movies SET movie_name = ?,movie_genre = ?, price = ? WHERE movie_id = ?";
    }

    @Override
    public String delete() {
        return "DELETE FROM movies WHERE movie_id = ?";
    }

    @Override
    public String getAll() {
        return "SELECT * FROM movies";
    }

    @Override
    public String getById() {
        return "SELECT * FROM movies WHERE movie_id = ?";
    }
}