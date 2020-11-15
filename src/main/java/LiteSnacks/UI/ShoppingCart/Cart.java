package LiteSnacks.UI.ShoppingCart;

import java.util.HashMap;
import java.util.Map;

import LiteSnacks.backend.ProductsHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Cart {

    private Pane pane;
    private VBox cartPane;
    private Map<String, CartItem> selectedProducts;// <coke, maps-to the cart-item>
    private Label total;
    private double total_cost;

    public Cart() {
        Label shoppingCart = new Label("Shopping Cart \n");
        shoppingCart.setFont(new Font(20));
        shoppingCart.setAlignment(Pos.CENTER);
        shoppingCart.setLayoutX(100);

        total = new Label("Total cost: $ 0.0");
        total.setFont(new Font(20));
        total.setLayoutX(100);
        total.setLayoutY(400);
        
        cartPane = new VBox(shoppingCart, total);
        cartPane.setStyle("-fx-background-color: #d9d9d9;");
        cartPane.setMinWidth(190);
        cartPane.setMinHeight(480 - 40);

        pane = new BorderPane(cartPane, null, null, total, null);
        pane.setLayoutX(440);
        pane.setLayoutY(10);

        selectedProducts = new HashMap<>();
    }

    public Pane getPane() {
        return pane;
    }

    public void addProduct(String product, String category, int id, double cost) {
        if (this.selectedProducts.containsKey(product)) {
            this.selectedProducts.get(product).add();
        } else {
            CartItem newItem = new CartItem(product, category, id, cost, 1, this);

            this.selectedProducts.put(product, newItem);
            this.cartPane.getChildren().add(newItem.cartItemPane);
        }
        this.total_cost += cost;
        this.updateTotal();
    }

    public double calculateCartTotalPrice() {
        double cartTotalPrice = 0.0;
        for (String name : selectedProducts.keySet()) {

            String key = name;
            double value = selectedProducts.get(key).getUnitPrice();
            double quantity = selectedProducts.get(key).getQuantity();

            double itemTotalPrice = quantity * value;
            cartTotalPrice += itemTotalPrice;
        }
        return cartTotalPrice;

    }

    public void decreaseQuantityOfProduct(String product) {
        if (this.selectedProducts.containsKey(product)) {
            this.selectedProducts.get(product).decrease();
            this.total_cost -= this.selectedProducts.get(product).getUnitPrice();
            this.updateTotal();
        }
    }

    public void removeProduct(String product) {
        CartItem removedItem = this.selectedProducts.remove(product);
        this.cartPane.getChildren().remove(removedItem.cartItemPane);
        if(this.total_cost - removedItem.getUnitPrice() * removedItem.getQuantity() >= 0.0) {
            this.total_cost -= removedItem.getUnitPrice() * removedItem.getQuantity();
            this.updateTotal();
        }
    }

    private void updateTotal() {
        this.total.setText("Total cost: $ " + (Math.round(this.total_cost*100)/100.0));
    }

    public double getTotal() {
        return this.total_cost;
    }

    public Map<String, CartItem> getItems() {
        return this.selectedProducts;
    }

    // update product list
    public void updateProductFile() {
        Map<String, CartItem> items = getItems();
        ProductsHandler ph = new ProductsHandler();
        items.forEach((k, v) ->
            {
                ph.editQuantity(k, (-1) * v.getQuantity());
            }
        );
    }


}