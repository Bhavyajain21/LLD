package SplitwiseLLD.Controllers;

import SplitwiseLLD.Balance.Balance;
import SplitwiseLLD.Balance.UserExpenseBalanceSheet;
import SplitwiseLLD.Split.Split;
import SplitwiseLLD.User.User;

import java.util.List;
import java.util.Map;

public class BalanceSheetController {
    public void updateUserExpenseBalanceSheet(User payer, List<Split> splits, double totalExpense){
        // Get balance sheet of the person who paid (jisne paisa diya)
        UserExpenseBalanceSheet payerSheet = payer.getUserExpenseBalanceSheet();
        payerSheet.setTotalPayment(payerSheet.getTotalPayment() + totalExpense);

        // Go through each person who needs to pay (jo contribute karega)
        for(Split split : splits){
            User personWhoOwes =  split.getUser();  // The person who has to pay back
            UserExpenseBalanceSheet owesSheet = personWhoOwes.getUserExpenseBalanceSheet(); //Jo banda paisa dene wala hai, uski balance sheet nikal lo taaki uske records update kar sakein.
            double amountToPay = split.getAmountOwe(); // Kitna paisa dena hai

            if (payer.getUserId().equals(personWhoOwes.getUserId())) {
                // If payer is also involved in expense, update their expense share
                payerSheet.setTotalYourExpense(payerSheet.getTotalYourExpense() + amountToPay);
            }
            else{
                // Payer ka paisa wapas aana chahiye
                payerSheet.setTotalYouGetBack(payerSheet.getTotalYouGetBack() + amountToPay);

                // Store how much this person has to return to payer
                Balance payerBalance = payerSheet.getUserVsBalance()
                        .computeIfAbsent(personWhoOwes.getUserId(), k -> new Balance());
                payerBalance.setAmountGetBack(payerBalance.getAmountGetBack() + amountToPay);

                // Update the balance of the person who owes money (jisne paisa dena hai)
                owesSheet.setTotalYouOwe(owesSheet.getTotalYouOwe() + amountToPay);
                owesSheet.setTotalYourExpense(owesSheet.getTotalYourExpense() + amountToPay);

                // Store how much this person owes to payer (Jis bande ko paisa dena hai (personWhoOwes), uska record update kar rahe hain ki usne payer ko kitna paisa dena hai.)
                Balance owesBalance = owesSheet.getUserVsBalance()
                        .computeIfAbsent(payer.getUserId(), k -> new Balance());
                owesBalance.setAmountOwe(owesBalance.getAmountOwe() + amountToPay);
            }
        }
    }

    public void showBalanceSheetOfUser(User user){

        System.out.println("---------------------------------------");

        System.out.println("Balance sheet of user : " + user.getUserId());

        UserExpenseBalanceSheet userExpenseBalanceSheet =  user.getUserExpenseBalanceSheet();

        System.out.println("TotalYourExpense: " + userExpenseBalanceSheet.getTotalYourExpense());
        System.out.println("TotalGetBack: " + userExpenseBalanceSheet.getTotalYouGetBack());
        System.out.println("TotalYourOwe: " + userExpenseBalanceSheet.getTotalYouOwe());
        System.out.println("TotalPaymnetMade: " + userExpenseBalanceSheet.getTotalPayment());
        for(Map.Entry<String, Balance> entry : userExpenseBalanceSheet.getUserVsBalance().entrySet()){
            String userID = entry.getKey();
            Balance balance = entry.getValue();

            System.out.println("userID:" + userID + " YouGetBack:" + balance.getAmountGetBack() + " YouOwe:" + balance.getAmountOwe());
        }

        System.out.println("---------------------------------------");

    }
}
