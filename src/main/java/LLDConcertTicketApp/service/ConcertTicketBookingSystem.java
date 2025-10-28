package LLDConcertTicketApp.service;

import LLDConcertTicketApp.entity.Booking;
import LLDConcertTicketApp.entity.Concert;
import LLDConcertTicketApp.entity.Seat;
import LLDConcertTicketApp.entity.User;
import LLDConcertTicketApp.enums.SeatStatus;
import LLDConcertTicketApp.exception.SeatNotAvailableException;
import LLDConcertTicketApp.observer.BookingObserver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ConcertTicketBookingSystem {
    private static ConcertTicketBookingSystem instance;
    private final Map<String, Concert> concerts;
    private final Map<String, Booking> bookings;
    private final List<BookingObserver> observers;
    private final Object lock = new Object();

    private ConcertTicketBookingSystem() {
        concerts = new ConcurrentHashMap<>();
        bookings = new ConcurrentHashMap<>();
        observers = new ArrayList<>();
    }

    public static synchronized ConcertTicketBookingSystem getInstance() {
        if (instance == null) {
            instance = new ConcertTicketBookingSystem();
        }
        return instance;
    }

    public void addObserver(BookingObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(BookingObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Booking booking) {
        for (BookingObserver observer : observers) {
            observer.onBookingStatusChanged(booking);
        }
    }

    public void addConcert(Concert concert) {
        concerts.put(concert.getId(), concert);
    }

    public Concert getConcert(String concertId) {
        return concerts.get(concertId);
    }

    public List<Concert> searchConcerts(String artist, String venue, LocalDateTime dateTime){
        return concerts.values().stream()
                .filter(concert->((artist==null || concert.getArtist().equalsIgnoreCase(artist)) &&
                        (venue==null || concert.getVenue().equalsIgnoreCase(venue)) &&
                        (dateTime==null || concert.getDateTime().toLocalDate().equals(dateTime.toLocalDate()))
                ))
                .collect(Collectors.toList());
    }

    public Booking bookTickets(User user, Concert concert, List<Seat> seats) {
        synchronized (lock) {
            // Check seat availability and book
            for (Seat seat : seats) {
                if (seat.getStatus() != SeatStatus.AVAILABLE) {
                    throw new SeatNotAvailableException("Seat " + seat.getSeatNumber() + " is not available.");
                }
            }

            seats.forEach(Seat::book);

            // Create booking
            String bookingId = "BKG-" + System.currentTimeMillis();
            Booking booking = new Booking(bookingId, user, concert, seats);
            bookings.put(bookingId, booking);

            // Process payment
            processPayment(booking);

            // Confirm booking and notify observers
            booking.confirmBooking();
            notifyObservers(booking);

            return booking;
        }
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            booking.cancelBooking();
            notifyObservers(booking);
            bookings.remove(bookingId);
        }
    }

    private void processPayment(Booking booking) {
        // Process payment for the booking
        // ...
    }
}
