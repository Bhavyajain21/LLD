package SplitwiseLLD.Controllers;

import SplitwiseLLD.Expense.Expense;
import SplitwiseLLD.Expense.ExpenseSplitType;
import SplitwiseLLD.Split.ExpenseSplitStrategy;
import SplitwiseLLD.Split.Split;
import SplitwiseLLD.Split.SplitStrategies.EqualExpenseSplit;
import SplitwiseLLD.Split.SplitStrategies.PercentageExpenseSplit;
import SplitwiseLLD.Split.SplitStrategies.UnequalExpenseSplit;
import SplitwiseLLD.User.User;

import java.util.List;

public class ExpenseController {
    BalanceSheetController balanceSheetController;
    public ExpenseController(){
        balanceSheetController = new BalanceSheetController();
    }

    public Expense createExpense(String expenseId, String description, double expenseAmount, List<Split> splitDetails, ExpenseSplitType splitType, User paidByUser){
        ExpenseSplitStrategy expenseSplit = switch (splitType){
            case PERCENTAGE -> new PercentageExpenseSplit();
            case EQUAL ->  new EqualExpenseSplit();
            case UNEQUAL ->   new UnequalExpenseSplit();
            default -> throw new IllegalStateException("Invalid Split Type: " + splitType);
        };

        expenseSplit.validateSplitRequest(splitDetails, expenseAmount);
        Expense expense = new Expense(expenseId, expenseAmount, description, paidByUser, splitType, splitDetails);
        balanceSheetController.updateUserExpenseBalanceSheet(paidByUser, splitDetails, expenseAmount);
        return expense;
    }
}
