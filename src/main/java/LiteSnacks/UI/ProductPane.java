package LiteSnacks.UI;

import LiteSnacks.UI.ShoppingCart.Cart;
import LiteSnacks.backend.Item;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ProductPane {
    private Pane pane;

    ProductPane(Item productDisplayItem, Cart cart) {
        this.pane = new Pane();
        this.pane.setPrefWidth(127);
        this.pane.setPrefHeight(143);

        this.pane.setStyle( "-fx-background-color: #d9d9d9;");

        //quantity modifier
//        Pane qtymdfier = new QuantityModifier(productDisplayItem).getPane();
//        qtymdfier.setLayoutX(52);
//        qtymdfier.setLayoutY(110);

        //price
        Text price = new Text("$ "+String.valueOf(productDisplayItem.getUnitPrice()));
        price.setLayoutX(4);
        price.setLayoutY(128);

        //product name
        Text name = new Text(productDisplayItem.name);
        name.setLayoutX(31);
        name.setLayoutY(105);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            cart.addProduct(productDisplayItem.name, productDisplayItem.getUnitPrice());
        });
        
        addButton.setLayoutX(80);
        addButton.setLayoutY(112);

        this.pane.getChildren().addAll(name, price, addButton);
    }

	public Pane getPane() {
        return this.pane;
    }
}