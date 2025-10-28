package LLDConcertTicketApp.factory;

import LLDConcertTicketApp.entity.Seat;
import LLDConcertTicketApp.enums.SeatType;
import LLDConcertTicketApp.enums.SeatStatus;

public class SeatFactory {
    public static Seat createSeat(String seatNumber, SeatType seatType) {
        double price = calculatePrice(seatType);
        return new Seat(seatNumber, seatNumber, seatType, price);
    }

    private static double calculatePrice(SeatType seatType) {
        return switch (seatType) {
            case VIP -> 100.0;
            case PREMIUM -> 75.0;
            case REGULAR -> 50.0;
        };
    }
}
