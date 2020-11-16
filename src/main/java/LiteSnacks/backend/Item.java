package LiteSnacks.backend;

import LiteSnacks.UI.Seller.EditProductPane;

import java.util.ArrayList;
import java.util.List;

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

    public void setName(String newName) {
        // todo: Make sure there are no clashes!
        if(newName != null && !newName.isEmpty()) {
            this.name = newName;
        }
    }

    public void setCategory(String newCategory) {
        ProductsHandler ph = new ProductsHandler();
        List<String> categories = ph.getCategories();
       if(newCategory != null && !newCategory.isEmpty() && categories.contains(newCategory)) {
           this.category = newCategory;
       }
    }

    public void setid(int newID) {
        // todo: Make sure there are no clashes!
        this.id = newID;
    }

    public void setQuantity(int newQuantity) {
        if(newQuantity > 0 && newQuantity <= 15) {
            this.quantity = newQuantity;
        }
    }

    public void setPrice(double newPrice) {
        if(newPrice > 0) {
            this.unitPrice = newPrice;
        }
    }

    public String write() {
        return String.format("%s,%s,%s,%s", name, quantity, unitPrice, id);
    }

    public static List<Item> newItems(List<EditProductPane> productPanes) {
        EditProductPane item;
        String name;
        int quantity;
        int code;
        double price;
        String category;

        List<Item> newListOfItems = new ArrayList<>();
        for(int i=0; i<productPanes.size(); i++) {
            item = productPanes.get(i);
            name = item.getName();
            category = item.getCategory();
            code = item.getCode();
            quantity = item.getQuant();
            price = item.getPrice();

            newListOfItems.add(new Item(name, category, code, quantity, price));
        }
        return newListOfItems;
    }
}
