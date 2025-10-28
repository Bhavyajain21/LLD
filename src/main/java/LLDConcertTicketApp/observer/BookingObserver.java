package LLDConcertTicketApp.observer;

import LLDConcertTicketApp.entity.Booking;

public interface BookingObserver {
    void onBookingStatusChanged(Booking booking);
}
