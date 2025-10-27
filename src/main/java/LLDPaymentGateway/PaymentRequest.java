package LLDPaymentGateway;

public class PaymentRequest{
    String sender;
    String receiver;
    double amount;
    String currency;

    PaymentRequest(String sender, String receiver, double amount, String currency) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.currency = currency;
    }
}
