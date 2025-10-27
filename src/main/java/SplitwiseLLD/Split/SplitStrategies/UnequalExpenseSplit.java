package SplitwiseLLD.Split.SplitStrategies;

import SplitwiseLLD.Split.ExpenseSplitStrategy;
import SplitwiseLLD.Split.Split;

import java.util.List;

public class UnequalExpenseSplit implements ExpenseSplitStrategy {
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) {
        double sum = 0;
        for (Split split : splitList) {
            sum += split.getAmountOwe();
        }
        if (sum != totalAmount) {
            throw new IllegalArgumentException("Split amounts do not match the total amount");
        }
    }
}
