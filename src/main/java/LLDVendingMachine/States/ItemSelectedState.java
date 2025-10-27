package LLDVendingMachine.States;

import LLDVendingMachine.States.*;
import LLDVendingMachine.enums.Coin;
import LLDVendingMachine.service.VendingMachine;

public class ItemSelectedState extends VendingMachineState {
    public ItemSelectedState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertCoin(Coin coin) {
        vendingMachine.addBalance(coin.getValue());
        System.out.println("Coin Inserted: " + coin.getValue());
        double price = vendingMachine.getSelectedItem().getPrice();
        if (vendingMachine.getBalance() >= price) {
            System.out.println("Sufficient money received.");
            vendingMachine.setState(new HasMoneyState(vendingMachine));
        }
    }

    @Override
    public void selectItem(String code) {
        System.out.println("Item already selected.");
    }

    @Override
    public void dispense() {
        System.out.println("Please insert sufficient money.");
    }

    @Override
    public void refund() {
        vendingMachine.reset();
        vendingMachine.setState(new IdleState(vendingMachine));
    }
}
