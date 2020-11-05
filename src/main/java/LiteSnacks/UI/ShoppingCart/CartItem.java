package LiteSnacks.UI.ShoppingCart;

import LiteSnacks.UI.Products;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class CartItem {

    private String name;
    public double cost;
    public int quantity;
    private Label qty;

    Pane cartItemPane;

    private Cart cart;

    CartItem(String name, double cost, int quantity, Cart cart) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.cart = cart;
        makePane();
    }

    private void makePane() {
        cartItemPane = new GridPane();
        cartItemPane.setStyle("-fx-border-style: solid;");
        cartItemPane.setMinWidth(190);
        cartItemPane.autosize();

        Label product = new Label(this.name);
        product.setMinWidth(140);
        GridPane.setConstraints(product, 0, 0);

        qty = new Label(Integer.toString(this.quantity));
        GridPane.setConstraints(qty, 3, 0);

        Label cost = new Label(Double.toString(this.cost));
        GridPane.setConstraints(cost, 0, 1);

        Button remove = new Button("Remove");
        remove.setOnAction(e -> {
            this.cart.removeProduct(this.name);

            Text displayTotalPrice = new Text("Pls click 'See Total Price' button again \n");
            displayTotalPrice.setLayoutX(4);
            displayTotalPrice.setLayoutY(128);
            cart.getPane().getChildren().add(displayTotalPrice);
        });
        GridPane.setConstraints(remove, 3, 1);

        cartItemPane.getChildren().addAll(product, qty, cost, remove);
    }

    void add() {
        this.quantity++;
        int index = this.cartItemPane.getChildren().indexOf(this.qty);
        this.qty = new Label(Integer.toString(this.quantity));
        GridPane.setConstraints(qty, 3, 0);
        this.cartItemPane.getChildren().set(index, this.qty);
    }

    void decrease() {
        this.quantity--;
        int index = this.cartItemPane.getChildren().indexOf(this.qty);
        this.qty = new Label(Integer.toString(this.quantity));
        GridPane.setConstraints(qty, 3, 0);
        this.cartItemPane.getChildren().set(index, this.qty);
    }
}