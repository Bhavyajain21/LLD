package LLDSnakeNLadderGame.observer;

public class ConsoleNotifierObserver implements IObserver {
    @Override
    public void update(String message) {
        System.out.println("[NOTIFICATION] " + message);
    }
}
