package ParkinglotLLD.Payment;

public class UpiPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing UPI payment of $" + amount);
        // Logic for UPI payment processing
    }
}
