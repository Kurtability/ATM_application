package LiteSnacks.backend;

public class Item {
    public String name;
    protected int quantity;
    protected double unitPrice;

    public Item(String name, int quantity, double unitPrice){
        this.name = name;
        this.quantity = quantity;
        this.unitPrice= unitPrice;
    }

    public String toString(){
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
