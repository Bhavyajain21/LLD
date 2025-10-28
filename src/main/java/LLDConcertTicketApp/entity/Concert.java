package LLDConcertTicketApp.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Concert {
    private String id;
    private String artistName;
    private LocalDateTime date;
    private String venue;
    private List<Seat> seats;

    public Concert(String id, String artist, String venue, LocalDateTime dateTime, List<Seat> seats) {
        this.id = id;
        this.artistName = artist;
        this.venue = venue;
        this.date = dateTime;
        this.seats = seats;
    }

    public String getId() {
        return id;
    }

    public String getArtist() {
        return artistName;
    }

    public String getVenue() {
        return venue;
    }

    public LocalDateTime getDateTime() {
        return date;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
