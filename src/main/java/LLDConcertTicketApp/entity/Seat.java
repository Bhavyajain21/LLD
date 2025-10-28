package LLDConcertTicketApp.entity;

import LLDConcertTicketApp.enums.*;
import LLDConcertTicketApp.exception.SeatNotAvailableException;

public class Seat {
    private String id;
    private String seatNumber;
    private SeatType seatType;
    private double price;
    private SeatStatus seatStatus;

    public Seat(String id, String seatNumber, SeatType seatType, double price) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price = price;
        this.seatStatus = SeatStatus.AVAILABLE;
    }

    public synchronized void book(){
        if(seatStatus == SeatStatus.AVAILABLE){
            seatStatus = SeatStatus.BOOKED;
        } else {
            throw new SeatNotAvailableException("Seat is already booked or reserved.");
        }
    }

    public synchronized void release(){
        if (seatStatus == SeatStatus.BOOKED || seatStatus == SeatStatus.RESERVED) {
            seatStatus = SeatStatus.AVAILABLE;
        }
    }

    public double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public SeatStatus getStatus() {
        return seatStatus;
    }
}
