package LiteSnacks.UI;

import LiteSnacks.backend.Item;

public class ProductDisplayItem extends Item {
    private String path;

    ProductDisplayItem(String name, int qty, double unitPrice) {
        super(name, qty, unitPrice);
        this.path = "coca.jpeg";

    }
    public void modifyqty( int gap){
        super.quantity = super.quantity + gap;
    }
}
