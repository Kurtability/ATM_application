package LiteSnacks.UI;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ProductPane {
    Pane pane;
    ProductPane(Item item){
        this.pane = new Pane();
        this.pane.setPrefWidth(127);
        this.pane.setPrefHeight(143);

        this.pane.setStyle( "-fx-background-color: #d9d9d9;");

        //quantity modifier
        Pane qtymdfier = new QuantityModifier(item).getPane();
        qtymdfier.setLayoutX(52);
        qtymdfier.setLayoutY(110);

        //price
        Text price = new Text("$ "+String.valueOf(item.price));
        price.setLayoutX(4);
        price.setLayoutY(128);

        //product name
        Text name = new Text(item.product_name);
        name.setLayoutX(31);
        name.setLayoutY(105);

        //image
        ImageView image = new ImageView(item.path);

        image.setFitHeight(85);
        image.setFitWidth(85);
        image.setLayoutX(21);
        image.setLayoutY(4);

        this.pane.getChildren().addAll(qtymdfier,price,name,image);
    }
    public Pane getPane(){
        return this.pane;
    }
}
