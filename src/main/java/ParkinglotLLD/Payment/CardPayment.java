package ParkinglotLLD.Payment;

public class CardPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
        // Logic for credit card payment processing
    }
}
