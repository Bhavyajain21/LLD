package ObserverPattern.Observer;

import ObserverPattern.Observable.StocksObservable;

public class EmailAlertObserverImpl implements NotificationAlertObserver{
    String emailId;
    StocksObservable stocksObservable;

    public EmailAlertObserverImpl(String emailId, StocksObservable stocksObservable) {
        this.emailId = emailId;
        this.stocksObservable = stocksObservable;
    }
    @Override
    public void update() {
        sendMail(emailId, "product is in stock, hurrrryyyyy up!!!!!");
    }

    private void sendMail(String emailId, String message) {
        System.out.println("Sending mail to " + emailId);
    }
}
