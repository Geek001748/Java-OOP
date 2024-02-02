package entities;

public class Movie {
    private int MovieId;
    private String movieName;
    private double price;

    public Movie(int MovieId, String movieName, double price) {
        this.MovieId = MovieId;
        this.movieName = movieName;
        this.price = price;
    }
    public Movie(String movieName, double price) {
        this.movieName = movieName;
        this.price = price;
    }
    public Movie(){
        System.out.println("Name of movie: " + getMovieName());
        System.out.println("Price of movie: " + getPrice());
    }
    public int getMovieId() {
        return MovieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public double getPrice() {
        return price;
    }

    public void setGetMovieId(int MovieId) {
        this.MovieId = MovieId;
    }

    public void setMovie_name(String movieName) {
        this.movieName = movieName;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
