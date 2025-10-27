package SplitwiseLLD.Split.SplitStrategies;

import SplitwiseLLD.Split.ExpenseSplitStrategy;
import SplitwiseLLD.Split.Split;

import java.util.List;

public class PercentageExpenseSplit implements ExpenseSplitStrategy {
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) {
        double totalPercentage = 0;
        for (Split split : splitList) {
            totalPercentage += split.getAmountOwe();
        }
        if (totalPercentage != totalAmount) {
            throw new IllegalArgumentException("Total percentage must sum up to 100%");
        }
    }
}
