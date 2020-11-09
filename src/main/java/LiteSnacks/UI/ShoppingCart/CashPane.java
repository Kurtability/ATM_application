package LiteSnacks.UI.ShoppingCart;

import LiteSnacks.UI.Cash;
import LiteSnacks.UI.QuantityModifier;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class CashPane {
    Pane pane;
    Cash cash;

    public CashPane(Cash cash) {
        this.cash = cash;
        this.pane = new Pane();
        this.pane.setPrefWidth(100);
        this.pane.setPrefHeight(120);
        this.pane.setStyle("-fx-background-color: #d9d9d9;");

        Pane qtymdfier = new QuantityModifier(cash,false).getPane();
        qtymdfier.setLayoutX(13);
        qtymdfier.setLayoutY(90);

        // price
        Text value = new Text("$ " + cash.getValue());
        value.setLayoutX(31);
        value.setLayoutY(85);

        ImageView image = new ImageView(cash.getImg());

        image.setFitHeight(85);
        image.setFitWidth(85);
        image.setLayoutX(21);
        image.setLayoutY(4);

        this.pane.getChildren().addAll(value, image, qtymdfier);

    }

    public Pane getPane() {
        return this.pane;
    }
    public Cash getCash() {return this.cash;}
}
