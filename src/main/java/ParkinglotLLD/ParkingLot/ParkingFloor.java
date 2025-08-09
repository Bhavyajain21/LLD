package ParkinglotLLD.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, int numOfCarSpots, int numOfBikeSpots){
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();

        for(int i=0;i<numOfCarSpots;i++){
            this.spots.add(new CarParkingSpot(i+1));  // Add car spots (using CarParkingSpot)
        }

        // Add spots for bikes
        for (int i = numOfCarSpots; i < numOfCarSpots + numOfBikeSpots; i++) {
            this.spots.add(new BikeParkingSpot(i + 1));  // Add bike spots (using BikeParkingSpot)
        }
    }
    public ParkingSpot findAvailableSpot(String vehicleType) {
        for(ParkingSpot spot : spots){
            if(!spot.isOccupied() && spot.getSpotType().equals(vehicleType)){
                return spot; // Return the first available spot for the vehicle type
            }
        }
        return null; // No available spot found for the given vehicle type
    }

    // Method to return all parking spots on this floor
    public List<ParkingSpot> getParkingSpots() {
        return spots;
    }
}
