package LLDVendingMachine.service;

import LLDVendingMachine.States.*;
import LLDVendingMachine.States.VendingMachineState;
import LLDVendingMachine.entity.Inventory;
import LLDVendingMachine.entity.Item;
import LLDVendingMachine.enums.Coin;

public class VendingMachine {
    private final static VendingMachine instance = new VendingMachine();
    private final Inventory inventory = new Inventory();
    private VendingMachineState currentVendingMachineState;
    private int balance = 0;
    private String selectedItemCode;
    private VendingMachine() {
        currentVendingMachineState = new IdleState(this);
    }

    public static VendingMachine getInstance() {
        return instance;
    }

    public void insertCoin(Coin coin) {
        currentVendingMachineState.insertCoin(coin);
    }

    public Item addItem(String code, String name, double price, int quantity) {
        Item item = new Item(name, price, code);
        inventory.addItem(code, item, quantity);
        return item;
    }

    public void selectItem(String code){
        currentVendingMachineState.selectItem(code);
    }

    public void dispense() {
        currentVendingMachineState.dispense();
    }

    public void dispenseItem() {
        Item item = inventory.getItem(selectedItemCode);
        if (balance >= item.getPrice()) {
            inventory.reduceStock(selectedItemCode);
            balance -= item.getPrice();
            System.out.println("Dispensed: " + item.getName());
            if (balance > 0) {
                System.out.println("Returning change: " + balance);
            }
        }
        reset();
        setState(new IdleState(this));
    }

    public void refundBalance() {
        System.out.println("Refunding: " + balance);
        balance = 0;
    }

    public void reset() {
        selectedItemCode = null;
        balance = 0;
    }

    public void addBalance(int value) {
        balance += value;
    }

    public Item getSelectedItem() {
        return inventory.getItem(selectedItemCode);
    }

    public void setSelectedItemCode(String code) {
        this.selectedItemCode = code;
    }

    public void setState(VendingMachineState vendingMachineState) {
        this.currentVendingMachineState = vendingMachineState;
    }

    // Getters for states and inventory
    public Inventory getInventory() { return inventory; }
    public int getBalance() { return balance; }
}
