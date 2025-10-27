package LLDVendingMachine.States;

import LLDVendingMachine.service.VendingMachine;
import LLDVendingMachine.enums.Coin;

public abstract class VendingMachineState {
    protected VendingMachine vendingMachine;
    public VendingMachineState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
    public abstract void insertCoin(Coin coin);
    public abstract void selectItem(String code);
    public abstract void dispense();
    abstract void refund();
}
