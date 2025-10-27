package LLDVendingMachine.entity;

public class Item {
    private String name;
    private double price;
    private String code;
    public Item(String name, double price, String code) {
        this.name = name;
        this.price = price;
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}
