package LiteSnacks.backend;

public class Item {
    public String name;
    protected int quantity;
    protected double unitPrice;
    protected String category;
    protected int id;

    public Item(String name, String category, int id, int quantity, double unitPrice){
        this.name = name;
        this.category = category;
        this.id = id;
        this.quantity = quantity;
        this.unitPrice= unitPrice;
    }

    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int modifyQty(int gap) {
        this.quantity += gap;
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
}
