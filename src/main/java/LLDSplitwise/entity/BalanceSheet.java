package LLDSplitwise.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheet {
    private User owner;

    // A map where:
    // Key: The user to whom the balance is related.
    // Value: The net amount.
    // - Positive value: The key-user owes the owner of this balance sheet money.
    // - Negative value: The owner owes the key-user money.
    private final Map<User, Double> balances = new ConcurrentHashMap<>();

    public BalanceSheet(User owner) {
        this.owner = owner;
    }

    public Map<User, Double> getBalances() {
        return balances;
    }

    public synchronized void adjustBalance(User otherUser, double amount) {
        if (owner.equals(otherUser)) {
            return; // Cannot owe yourself
        }
        Double currentBalance = balances.get(otherUser);

        if (currentBalance == null) {
            // If the user doesn’t exist in the map yet, add them
            balances.put(otherUser, amount);
        } else {
            // Otherwise, update the existing balance
            double newBalance = currentBalance + amount;
            balances.put(otherUser, newBalance);
        }
    }

    public void showBalances() {
        System.out.println("--- Balance Sheet for " + owner.getName() + " ---");
        if(balances.isEmpty()) {
            System.out.println("All settled up!!");
            return;
        }
        double totalOwedToMe = 0;
        double totalIOwe = 0;
        for(Map.Entry<User,Double> entry : balances.entrySet()) {
            User otherUser = entry.getKey();
            double amount = entry.getValue();
            if (amount > 0) {
                System.out.printf("%s owes %s: %.2f\n", otherUser.getName(), owner.getName(), amount);
                totalOwedToMe += amount;
            } else if (amount < 0) {
                System.out.printf("%s owes %s: %.2f\n", owner.getName(), otherUser.getName(), -amount);
                totalIOwe += -amount;
            }
        }
        System.out.println("Total Owed to " + owner.getName() + ": $" + String.format("%.2f", totalOwedToMe));
        System.out.println("Total " + owner.getName() + " Owes: $" + String.format("%.2f", totalIOwe));
        System.out.println("---------------------------------");
    }
}
