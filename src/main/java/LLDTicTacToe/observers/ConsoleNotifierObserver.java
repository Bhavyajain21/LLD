package LLDTicTacToe.observers;

public class ConsoleNotifierObserver implements IObserver{
    @Override
    public void notify(String message) {
        System.out.println("[Notification] " + message);
    }
}
