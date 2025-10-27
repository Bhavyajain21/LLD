package ParkinglotLLD.Vehicles;

public class CarVehicle extends Vehicle {
    private static final double RATE = 10.0; // $10 per hour for bikes

    public CarVehicle(String licensePlate) {
        super(licensePlate, "Car");
    }

    // Implement calculateFee for Bike
    @Override
    public double calculateFee(int hoursStayed) {
        return hoursStayed * RATE;
    }
}
