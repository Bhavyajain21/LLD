package LLDPaymentGateway;

abstract class BankingSystem{
    abstract Boolean processPayment(double amount);
}

class PaytmBankingSystem extends BankingSystem {
    @Override
    Boolean processPayment(double amount) {
        // simulate 20% success
        int rand = (int)(Math.random() * 10);
        return rand < 2;
    }
}

class RazorpayBankingSystem extends BankingSystem {
    @Override
    Boolean processPayment(double amount) {
        // simulate 90% success
        int rand = (int)(Math.random() * 10);
        return rand < 9;
    }
}

abstract class PaymentGateway {
    private BankingSystem bankingSystem;

    // Template Method - defines the skeleton of the algorithm
    Boolean processPayment(PaymentRequest paymentRequest) {
        if(!validateRequest(paymentRequest)){
            System.out.println("Payment request validation failed for " + paymentRequest.sender);
            return false;
        }
        if(!initiateRequest(paymentRequest)){
            System.out.println("Payment request initiated failed for " + paymentRequest.sender);
            return false;
        }
        if(!confirmPayment(paymentRequest)){
            System.out.println("Payment request confirmation failed for " + paymentRequest.sender);
            return false;
        }
        return true;
    }
    protected abstract Boolean validateRequest(PaymentRequest paymentRequest);
    protected abstract Boolean initiateRequest(PaymentRequest paymentRequest);
    protected abstract Boolean confirmPayment(PaymentRequest paymentRequest);
}

class PaytmGateway extends PaymentGateway {
    private BankingSystem bankingSystem;

    PaytmGateway() {
        this.bankingSystem = new PaytmBankingSystem();
    }

    @Override
    protected Boolean validateRequest(PaymentRequest paymentRequest) {
        // Basic validation logic
        System.out.println("Payment request validation for " + paymentRequest.sender);
        if(paymentRequest.amount<=0 && paymentRequest.currency!="INR"){
            return false;
        }
        return true;
    }

    @Override
    protected Boolean initiateRequest(PaymentRequest paymentRequest) {
        // Logic to initiate payment request
        System.out.println("[Paytm] Initiating payment request of " + paymentRequest.amount + " " + paymentRequest.currency + " from " + paymentRequest.sender + " to " + paymentRequest.receiver);
        return bankingSystem.processPayment(paymentRequest.amount);
    }

    @Override
    protected Boolean confirmPayment(PaymentRequest paymentRequest) {
        // Logic to confirm payment via banking system
        System.out.println("[Paytm] Confirming payment for " + paymentRequest.sender);
        return true;
    }
}

class RazorpayGateway extends PaymentGateway {
    private BankingSystem bankingSystem;

    RazorpayGateway() {
        this.bankingSystem = new RazorpayBankingSystem();
    }

    @Override
    protected Boolean validateRequest(PaymentRequest paymentRequest) {
        // Basic validation logic
        System.out.println("Payment request validation for " + paymentRequest.sender);
        if(paymentRequest.amount<=0){
            return false;
        }
        return true;
    }

    @Override
    protected Boolean initiateRequest(PaymentRequest paymentRequest) {
        // Logic to initiate payment request
        System.out.println("[Razorpay] Initiating payment request of " + paymentRequest.amount + " " + paymentRequest.currency + " from " + paymentRequest.sender + " to " + paymentRequest.receiver);
        return bankingSystem.processPayment(paymentRequest.amount);
    }

    @Override
    protected Boolean confirmPayment(PaymentRequest paymentRequest) {
        // Logic to confirm payment via banking system
        System.out.println("[Razorpay] Confirming payment for " + paymentRequest.sender);
        return true;
    }
}

// Proxy class that wraps a LLDPaymentGateway.PaymentGateway to add retries (Proxy Pattern)
class PaymentGatewayProxy extends PaymentGateway {
    private PaymentGateway realGateway;
    int retries;

    PaymentGatewayProxy(PaymentGateway paymentGateway, int maxRetries) {
        this.realGateway = paymentGateway;
        retries = maxRetries;
    }

    @Override
    Boolean processPayment(PaymentRequest paymentRequest) {
        boolean result = false;
        for(int attempt=0; attempt<retries; attempt++) {
            if(attempt>0){
                System.out.println("[Razorpay] Retrying " + attempt + " of " + retries + " for " + paymentRequest.sender);
            }
            result = realGateway.processPayment(paymentRequest);
            if(result){break;}
        }
        if(!result){
            System.out.println("Payment failed after " + (retries+1) + " attempts for " + paymentRequest.sender);
        }
        return result;
    }

    @Override
    protected Boolean validateRequest(PaymentRequest paymentRequest) {
        return realGateway.validateRequest(paymentRequest);
    }

    @Override
    protected Boolean initiateRequest(PaymentRequest paymentRequest) {
        return realGateway.initiateRequest(paymentRequest);
    }

    @Override
    protected Boolean confirmPayment(PaymentRequest paymentRequest) {
        return realGateway.confirmPayment(paymentRequest);
    }
}

enum GatewayType {
    PAYTM,
    RAZORPAY
}

class GatewayFactory {
    public static PaymentGateway getPaymentGateway(GatewayType gatewayType) {
        switch (gatewayType) {
            case PAYTM:
                PaytmGateway paytmGateway = new PaytmGateway();
                return new PaymentGatewayProxy(paytmGateway, 3);
            case RAZORPAY:
                RazorpayGateway razorpayGateway = new RazorpayGateway();
                return new PaymentGatewayProxy(razorpayGateway, 5);
            default:
                throw new IllegalArgumentException("Invalid gateway type");
        }
    }
}

class PaymentService {
    private PaymentGateway gateway;
    void setGateway(PaymentGateway paymentGateway) {
        this.gateway = paymentGateway;
    }
    boolean processPayment(PaymentRequest paymentRequest) {
        if (gateway==null) {
            System.out.println("Payment Gateway not set for " + paymentRequest.sender);
            return false;
        }
        return gateway.processPayment(paymentRequest);
    }
}

class PaymentController {
    private PaymentService paymentService;

    PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean handlePayment(PaymentRequest paymentRequest, GatewayType gatewayType) {
        PaymentGateway gateway = GatewayFactory.getPaymentGateway(gatewayType);
        paymentService.setGateway(gateway);
        return paymentService.processPayment(paymentRequest);
    }
}


public class LLDPaymentGateway {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        PaymentController paymentController = new PaymentController(paymentService);

        PaymentRequest request1 = new PaymentRequest("Alice", "Bob", 1000, "INR");
        paymentController.handlePayment(request1, GatewayType.PAYTM);

        PaymentRequest request2 = new PaymentRequest("Charlie", "Dave", 500, "USD");
        paymentController.handlePayment(request2, GatewayType.RAZORPAY);
    }
}
