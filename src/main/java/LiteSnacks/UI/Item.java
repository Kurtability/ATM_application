package LiteSnacks.UI;

public class Item {
    public int qty;
    public int price;
    public String product_name;
    public String path;

    Item(int qty){
        this.qty = qty;
        this.price = 100;
        this.product_name = "coca";
        this.path = "coca.jpeg";

    }
    
    Item(String product, int price, int qty){
        this.qty = qty;
        this.price = price;
        this.product_name = product;
        this.path = "coca.jpeg";

    }

    public int getQty() {return this.qty;}
    public int getPrice() {return this.price;}
    public String getProduct_name() {return this.product_name;}
    public void modifyqty( int gap){
        qty = qty+(gap);
    }
}
