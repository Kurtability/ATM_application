package LiteSnacks.UI.ShoppingCart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Cart {

    public Pane cartPane;
    private Map<String, CartItem> selectedProducts;// <coke, maps-to the cart-item>
    private double cartTotalPrice ;
    private List<Double> cartitemprice;

    public Cart() {
        Label shoppingCart = new Label("Shopping Cart \n");
        shoppingCart.setFont(new Font(20));
        shoppingCart.setAlignment(Pos.CENTER);

        cartPane = new VBox(shoppingCart);
        cartPane.setStyle("-fx-background-color: #d9d9d9;");
        cartPane.setMinWidth(190);
        cartPane.setMinHeight(480-20);
        cartPane.setLayoutX(440);
        cartPane.setLayoutY(10);

        selectedProducts = new HashMap<>();
    }

    public Pane getPane() {
        return cartPane;
    }

    public void addProduct(String product, double cost) { //
        if (this.selectedProducts.containsKey(product)) {
            this.selectedProducts.get(product).add();
        } else {
            CartItem newItem = new CartItem(product, cost, 1, this);
            this.selectedProducts.put(product, newItem);
            this.cartPane.getChildren().add(newItem.cartItemPane);
        }
    }


    public double CalculateCartTotalPrice(){
        cartTotalPrice = 0;
        for (String name: selectedProducts.keySet()){

            String key = name;
            double value = selectedProducts.get(key).cost;
            double quantity = selectedProducts.get(key).quantity;

            double itemTotalPrice = quantity *value;
            cartTotalPrice += itemTotalPrice;
        }
        return cartTotalPrice;

    }

    public void decreaseQuantityOfProduct(String product, double cost) {
        if (this.selectedProducts.containsKey(product)) {
            this.selectedProducts.get(product).decrease();
        }
    }

    public void removeProduct(String product) {
        this.cartPane.getChildren().remove(this.selectedProducts.remove(product).cartItemPane);
    }

}