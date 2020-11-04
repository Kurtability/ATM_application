package LiteSnacks.UI;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ProductPane {
    Pane pane;
    ProductPane(ProductDisplayItem productDisplayItem){
        this.pane = new Pane();
        this.pane.setPrefWidth(127);
        this.pane.setPrefHeight(143);

        this.pane.setStyle( "-fx-background-color: #d9d9d9;");

        //quantity modifier
//        Pane qtymdfier = new QuantityModifier(productDisplayItem).getPane();
//        qtymdfier.setLayoutX(52);
//        qtymdfier.setLayoutY(110);

        //price
//        Text price = new Text("$ "+String.valueOf(productDisplayItem.price));
//        price.setLayoutX(4);
//        price.setLayoutY(128);

        //product name
        Text name = new Text(productDisplayItem.product_name);
        name.setLayoutX(31);
        name.setLayoutY(105);

        //image
        ImageView image = new ImageView(productDisplayItem.path);

        image.setFitHeight(85);
        image.setFitWidth(85);
        image.setLayoutX(21);
        image.setLayoutY(4);

        this.pane.getChildren().addAll(name,image);
    }
    public Pane getPane(){
        return this.pane;
    }
}
