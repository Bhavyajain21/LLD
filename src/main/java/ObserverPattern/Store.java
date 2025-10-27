package ObserverPattern;

import ObserverPattern.Observable.HoodieObservableImpl;
import ObserverPattern.Observable.IphoneObservableImpl;
import ObserverPattern.Observable.StocksObservable;
import ObserverPattern.Observer.EmailAlertObserverImpl;
import ObserverPattern.Observer.NotificationAlertObserver;

public class Store {
    public static void main(String[] args){
        StocksObservable iphoneObservable = new IphoneObservableImpl();
        StocksObservable hoodieObservable = new HoodieObservableImpl();
        NotificationAlertObserver observer1 = new EmailAlertObserverImpl("rishikabhavya@gmail.com", iphoneObservable);
        NotificationAlertObserver observer2 = new EmailAlertObserverImpl("xyz@gmail.com", iphoneObservable);
        NotificationAlertObserver observer3 = new EmailAlertObserverImpl("velu@gmail.com", hoodieObservable);
        iphoneObservable.add(observer1);
        iphoneObservable.add(observer2);
        hoodieObservable.add(observer3);
        iphoneObservable.setStockCount(10);
        hoodieObservable.setStockCount(10);
    }
}