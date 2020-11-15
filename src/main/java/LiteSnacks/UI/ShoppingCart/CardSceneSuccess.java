package LiteSnacks.UI.ShoppingCart;

import LiteSnacks.UI.Products;
import LiteSnacks.backend.CreditCardHandler;
import LiteSnacks.backend.ProductsHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Map;

public class CardSceneSuccess {
    Scene scene;
    Stage stage;

    public CardSceneSuccess(double width, double height, Stage stage, Cart cart) {
        this.stage = stage;
        Pane root = new Pane();

        cart.updateProductFile();

        Label title = new Label(CreditCardHandler.getSuccessMessage());
        title.setLayoutX(100);
        title.setLayoutY(200);
        title.setFont(new Font("Arial", 30));

        Button finish = new Button("Finish");
        finish.setLayoutX(270);
        finish.setLayoutY(335);
        finish.setTextFill(Color.WHITE);
        finish.setStyle("-fx-background-color: #000000");
        finish.setOnAction(event -> {
                new Products(width,height,stage).setScene();
        });

        root.getChildren().addAll(title, finish);

        scene = new Scene(root, width, height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
