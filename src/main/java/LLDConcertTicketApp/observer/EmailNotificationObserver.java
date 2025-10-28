package LLDConcertTicketApp.observer;

import LLDConcertTicketApp.entity.Booking;

public class EmailNotificationObserver implements BookingObserver {
    @Override
    public void onBookingStatusChanged(Booking booking) {
        // In a real application, this would send an email
        System.out.println("Sending email notification to " + booking.getUser().getEmail() +
                         " for booking " + booking.getId() +
                         " Status: " + booking.getStatus());
    }
}
