package LLDSplitwise.strategy;

import LLDSplitwise.entity.Split;
import LLDSplitwise.entity.User;

import java.util.List;

public interface SplitStrategy {
    List<Split> calculateSplits(double totalAmount, User paidBy, List<User> participants, List<Double> splitValues);
}
