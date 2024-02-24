package entities;

public class Movie {
    private int MovieId;
    private String movieName;
    private String movieGenre;
    public Movie(int MovieId, String movieName, String movieGenre) {
        this.MovieId = MovieId;
        this.movieName = movieName;
        this.movieGenre = movieGenre;
    }
    public Movie(){
        System.out.println("Name of movie: " + getMovieName());
        System.out.println("Genre of movie: " + getMovieGenre());
    }
    public int getMovieId() {
        return MovieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieId(int MovieId) {
        this.MovieId = MovieId;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }
    @Override
    public String toString() {
        return "Movie ID: " + getMovieName() + "\nMovie name: " + getMovieName() + "\nMovie genre: " + getMovieGenre();
    }
}