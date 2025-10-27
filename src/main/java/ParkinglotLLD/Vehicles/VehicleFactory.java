package ParkinglotLLD.Vehicles;

public class VehicleFactory {
    public static Vehicle createVehicle(String licensePlate, String vehicleType) {
        if(vehicleType.equalsIgnoreCase("Car")) {
            return new CarVehicle(licensePlate);
        }
        else if(vehicleType.equalsIgnoreCase("Bike")) {
            return new BikeVehicle(licensePlate);
        }
        return null; // For unsupported vehicle types
    }
}
