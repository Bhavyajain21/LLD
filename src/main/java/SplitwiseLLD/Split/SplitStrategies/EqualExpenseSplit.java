package SplitwiseLLD.Split.SplitStrategies;

import SplitwiseLLD.Split.ExpenseSplitStrategy;
import SplitwiseLLD.Split.Split;

import java.util.List;

public class EqualExpenseSplit implements ExpenseSplitStrategy {
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount){
        double amountShouldBePresent = totalAmount / splitList.size();
        for (Split split : splitList) {
            if (split.getAmountOwe() != amountShouldBePresent) {
                throw new IllegalArgumentException("Each person should have an equal split");
            }
        }
    }
}
