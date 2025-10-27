package ParkinglotLLD.Gates;

import ParkinglotLLD.ParkingLot.ParkingLot;
import ParkinglotLLD.ParkingLot.ParkingSpot;
import ParkinglotLLD.Vehicles.Vehicle;
import ParkinglotLLD.Vehicles.VehicleFactory;

import java.util.Scanner;

public class EntryGate {

    private final ParkingLot parkingLot;

    public EntryGate(ParkingLot parkinglot) {
        this.parkingLot =  parkinglot;
    }

    public void processEntrance(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the vehicle license plate: ");
        String licensePlate = scanner.next();
        System.out.println("Enter the vehicle type (Car or Bike): ");
        String vehicleType = scanner.next();

        Vehicle vehicle = VehicleFactory.createVehicle(licensePlate, vehicleType);
        if(vehicle == null){
            System.out.println("Invalid vehicle type! Only Car and Bike are allowed.");
            return;
        }

        ParkingSpot spot = parkingLot.parkVehicle(vehicle);
        if (spot != null) {
            System.out.println("Vehicle parked successfully in spot: " + spot.getSpotNumber());
        } else {
            System.out.println("No available spots for the vehicle type.");
        }
    }
}
