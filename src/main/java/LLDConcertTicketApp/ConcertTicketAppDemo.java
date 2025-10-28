package LLDConcertTicketApp;

import LLDConcertTicketApp.entity.Booking;
import LLDConcertTicketApp.entity.Concert;
import LLDConcertTicketApp.entity.Seat;
import LLDConcertTicketApp.entity.User;
import LLDConcertTicketApp.enums.SeatStatus;
import LLDConcertTicketApp.enums.SeatType;
import LLDConcertTicketApp.factory.SeatFactory;
import LLDConcertTicketApp.observer.EmailNotificationObserver;
import LLDConcertTicketApp.service.ConcertTicketBookingSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConcertTicketAppDemo {
    public static void main(String[] args) {
        // Create concert ticket booking system instance (Singleton)
        ConcertTicketBookingSystem concertTicketBookingSystem = ConcertTicketBookingSystem.getInstance();

        // Add observer for notifications
        concertTicketBookingSystem.addObserver(new EmailNotificationObserver());

        // Create concerts using Factory pattern for seats
        List<Seat> concert1Seats = generateSeatsUsingFactory(100);
        Concert concert1 = new Concert("C001", "Artist 1", "Venue 1", LocalDateTime.now().plusDays(30), concert1Seats);
        concertTicketBookingSystem.addConcert(concert1);

        List<Seat> concert2Seats = generateSeatsUsingFactory(50);
        Concert concert2 = new Concert("C002", "Artist 2", "Venue 2", LocalDateTime.now().plusDays(60), concert2Seats);
        concertTicketBookingSystem.addConcert(concert2);

        // Create users
        User user1 = new User("U001", "John Doe", "john@example.com");
        User user2 = new User("U002", "Jane Smith", "jane@example.com");

        // Search concerts
        List<Concert> searchResults = concertTicketBookingSystem.searchConcerts("Artist 1", "Venue 1", LocalDateTime.now().plusDays(30));
        System.out.println("Search Results:");
        for (Concert concert : searchResults) {
            System.out.println("Concert: " + concert.getArtist() + " at " + concert.getVenue());
        }

        // Book tickets (Observer pattern will trigger notifications)
        List<Seat> selectedSeats1 = selectSeats(concert1, 3);
        Booking booking1 = concertTicketBookingSystem.bookTickets(user1, concert1, selectedSeats1);

        List<Seat> selectedSeats2 = selectSeats(concert2, 2);
        Booking booking2 = concertTicketBookingSystem.bookTickets(user2, concert2, selectedSeats2);

        // Cancel booking (Observer pattern will trigger cancellation notification)
        concertTicketBookingSystem.cancelBooking(booking1.getId());

        // Book tickets again
        List<Seat> selectedSeats3 = selectSeats(concert1, 2);
        Booking booking3 = concertTicketBookingSystem.bookTickets(user2, concert1, selectedSeats3);
    }

    private static List<Seat> generateSeatsUsingFactory(int numberOfSeats) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            String seatNumber = "S" + i;
            SeatType seatType = (i <= 10) ? SeatType.VIP : (i <= 30) ? SeatType.PREMIUM : SeatType.REGULAR;
            seats.add(SeatFactory.createSeat(seatNumber, seatType));
        }
        return seats;
    }

    private static List<Seat> selectSeats(Concert concert, int numberOfSeats) {
        List<Seat> selectedSeats = new ArrayList<>();
        List<Seat> availableSeats = concert.getSeats().stream()
                .filter(seat -> seat.getStatus() == SeatStatus.AVAILABLE)
                .limit(numberOfSeats)
                .toList();
        selectedSeats.addAll(availableSeats);
        return selectedSeats;
    }
}
