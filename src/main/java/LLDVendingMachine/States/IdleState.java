package LLDVendingMachine.States;

import LLDVendingMachine.States.*;
import LLDVendingMachine.enums.Coin;
import LLDVendingMachine.service.VendingMachine;

public class IdleState extends VendingMachineState{
    public IdleState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please select an item before inserting money.");
    }

    @Override
    public void selectItem(String code) {
        if (!vendingMachine.getInventory().isAvailable(code)) {
            System.out.println("Item not available.");
            return;
        }
        vendingMachine.setSelectedItemCode(code);
        vendingMachine.setState(new ItemSelectedState(vendingMachine));
        System.out.println("Item selected: " + code);
    }

    @Override
    public void dispense() {
        System.out.println("No item selected.");
    }

    @Override
    public void refund() {
        System.out.println("No money to refund.");
    }
}
