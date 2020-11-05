package LiteSnacks.UI;

public class Item {
    public int qty;
    public double price;
    public String product_name;
    public String code;
    public String path;
    public Category category;

    public Item(int qty,double price,String name,String category,String code){
        this.qty = qty;
        this.price = 100;
        this.code = code;
        this.product_name = name;
        this.path = "coca.jpeg";
        this.category = Category.valueOf(category);

    }
    enum Category {
        DRINKS,
        CHOCOLATES,
        CHIPS,
        CANDIES
    }
    public boolean modifyqty( int gap){
        if (qty + gap <0){
            return false;
        }else{
            this.qty = qty + (gap);
        }
        return true;
    }
    public int getQty(){return this.qty;}
    public double getPrice(){return this.price;}
    public String getProduct_name(){return this.product_name;}
    public String getCode(){return this.code;}

    public void update(double price,String product_name,String code,String category){
        this.price = price;
        this.product_name = product_name;
        this.code = code;
        this.category = Category.valueOf(category);

    }
}
