package entities.movies;

public class Melodrama extends Movie{
     Double  promotionProcent;

    public Double getPromotionProcent() {
        return promotionProcent;
    }

    public void setPromotionProcent(Double promotionProcent) {
        this.promotionProcent = promotionProcent;
    }

    public Melodrama(Double promotionProcent) {
        this.promotionProcent = promotionProcent;
    }

    public Melodrama(int MovieId, String movieName, String movieGenre, Double promotionProcent) {
        super(MovieId, movieName, movieGenre);
        this.promotionProcent = promotionProcent;
    }
    public Melodrama(int MovieId, String movieName, String movieGenre) {
        super(MovieId, movieName, movieGenre);
    }

    public Melodrama(String movieName, String movieGenre, Double promotionProcent) {
        super(movieName, movieGenre);
        this.promotionProcent = promotionProcent;
    }

    public Melodrama( String movieName, String movieGenre) {
        super( movieName, movieGenre);
    }



    @Override
    public String toString() {
        return "Movie ID: " + getMovieName() + "\nMovie name: " + getMovieName() + "\nMovie genre: " + getMovieGenre()+"\nMovie promotion price: " + getPromotionProcent();
    }
}
