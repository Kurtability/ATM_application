package LiteSnacks.UI;

public class ProductDisplayItem {
    public int qty;
    public int price;
    public String product_name;
    public String path;

    ProductDisplayItem(int qty){
        this.qty = qty;
        this.price = 100;
        this.product_name = "coca";
        this.path = "coca.jpeg";

    }
    public void modifyqty( int gap){
        qty = qty+(gap);
    }
}
