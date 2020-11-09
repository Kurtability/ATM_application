package LiteSnacks.UI.ShoppingCart;

import LiteSnacks.backend.Item;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class CartItem extends Item {

    private Label qty;

    Pane cartItemPane;

    private Cart cart;

    CartItem(String name, String category, int id, double unitPrice, int quantity, Cart cart) {
        super(name, category, id, quantity, unitPrice);
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

        Label cost = new Label(Double.toString(this.unitPrice));
        GridPane.setConstraints(cost, 0, 1);

        Button remove = new Button("Remove");
        remove.setOnAction(e -> {
            this.cart.removeProduct(this.name);
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