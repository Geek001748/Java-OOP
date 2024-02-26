package entities;

public class Ticket {
    private final int ticketId;
    private final int userId;
    private final int movieId;
    private final double ticketPrice;

    private Ticket(int ticketId, int userId, int movieId, double ticketPrice) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.movieId = movieId;
        this.ticketPrice = ticketPrice;
    }

    // Getters for fields
    public int getTicketId() {
        return ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    // Builder pattern
    public static class TicketBuilder {
        private int ticketId;
        private int userId;
        private int movieId;
        private double ticketPrice;

        public TicketBuilder() {
        }

        public TicketBuilder ticketId(int ticketId) {
            this.ticketId = ticketId;
            return this;
        }

        public TicketBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public TicketBuilder movieId(int movieId) {
            this.movieId = movieId;
            return this;
        }

        public TicketBuilder ticketPrice(double ticketPrice) {
            this.ticketPrice = ticketPrice;
            return this;
        }

        public Ticket build() {
            return new Ticket(ticketId, userId, movieId, ticketPrice);
        }
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId + "\nTicket price: " + ticketPrice;
    }
}
