package Queries;

public class MovieQueries implements IQueries {
    @Override
    public String createTable() {
        return "CREATE TABLE IF NOT EXISTS movies ("
                + "movie_id SERIAL PRIMARY KEY,"
                + "movie_name VARCHAR(50) NOT NULL,"
                + "movie_genre VARCHAR(50) NOT NULL,"
                + "movie_promotion_percent DOUBLE PRECISION NOT NULL)";
    }

    @Override
    public String add() {
        return "INSERT INTO movies (movie_name, movie_genre, movie_promotion_percent) VALUES ( ?, ?, ?)";
    }

    public String getMovieByName() {
        return "SELECT * FROM movies WHERE movie_name = ?";
    }

    @Override
    public String update() {
        return "UPDATE movies SET movie_name = ?,movie_genre = ?, movie_promotion_percent = ? WHERE movie_id = ?";
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

    public String getAllMoviesWithPromotionByGenre() {
        return "SELECT * FROM movies WHERE movie_genre = ? AND movie_promotion_percent > 0";
    }

    public String getByMovieID() {
        return "SELECT COUNT(*) FROM movies WHERE movie_id = ?";
    }
}