package LiteSnacks.UI.ShoppingCart;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.geometry.*;

import LiteSnacks.backend.CreditCardHandler;
import LiteSnacks.backend.Transaction;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import LiteSnacks.UI.Products;

public class CardScene {
    Scene scene;
    Stage stage;

    public CardScene(double width, double height, Stage stage, Cart cart) {
        this.stage = stage;
        Pane root = new Pane();

        Label price = new Label("Total Price: $" + Double.toString(cart.getTotal()));
        price.setLayoutX(200);
        price.setLayoutY(290);
        price.setFont(new Font("Arial", 18));

        Label title = new Label("Payment Method");
        title.setLayoutX(75);
        title.setLayoutY(30);
        title.setFont(new Font("Arial", 30));

        Label title2 = new Label("Credit Card");
        title2.setLayoutX(75);
        title2.setLayoutY(90);
        title2.setFont(new Font("Arial", 20));

        Label cardLabel = new Label("Enter Card Number:");
        cardLabel.setLayoutX(100);
        cardLabel.setLayoutY(227);
        cardLabel.setFont(new Font("Arial", 15));

        Label givenName = new Label("Enter Full Name:");
        givenName.setLayoutX(125);
        givenName.setLayoutY(177);
        givenName.setFont(new Font("Arial", 15));

        Text fail = new Text("");
        fail.setLayoutX(75);
        fail.setLayoutY(150);
        fail.setFont(new Font("Arial", 20));
        fail.setFill(Color.rgb(255, 0, 0));

        TextField name = new TextField();
        name.setPromptText("Please Enter Full Name");
        name.setLayoutX(275);
        name.setLayoutY(175);
        
        TextField number = new TextField();
        number.setPromptText("Enter Card Number");
        number.setLayoutX(275);
        number.setLayoutY(225);

        // back
        Button back = new Button("Back");
        back.setStyle("-fx-background-color: #DC143C");
        back.setTextFill(Color.WHITE);
        back.setOnAction(e -> {
            new Checkout(width,height,stage,cart).setScene();
        });
        back.setLayoutX(500);
        back.setLayoutY(430);

        Label message = new Label("");
        message.setLayoutX(325);
        message.setLayoutY(350);

        Button pay = new Button("Pay Now");
        pay.setLayoutX(240);
        pay.setLayoutY(335);
        pay.setTextFill(Color.WHITE);
        pay.setStyle("-fx-background-color: #000000");
        pay.setOnAction(event -> {
            String result = CreditCardHandler.checkCard(name.getText(), number.getText());
            if(!result.equals("Credit Card details are not valid")) {
                Map<String, List<Double>> purchasedProducts = new HashMap<>();
                Map<String, CartItem> cartstuff = cart.getItems();
                for (String item: cartstuff.keySet()) {
                    List<Double> temp = new ArrayList<>();
                    temp.add(cartstuff.get(item).getUnitPrice());
                    temp.add((double)cartstuff.get(item).getQuantity());
                    purchasedProducts.put(item, temp);
                }
                Transaction.addTransaction(purchasedProducts, cart.getTotal(), "0", true);
                new CardSceneSuccess(width, height, stage, cart).setScene();
            }
            else {
                fail.setText(result);
            }
        });

        Button cancel = new Button("Cancel Transaction");
        cancel.setLayoutX(25);
        cancel.setLayoutY(430);
        cancel.setStyle("-fx-background-color: #DC143C");
        cancel.setTextFill(Color.WHITE);
        cancel.setOnAction(event -> {new Products(width,height,stage).setScene();});

        root.getChildren().addAll(name, givenName, cardLabel, cancel, title2, pay, price, title, number, fail, back);

        scene = new Scene(root, width, height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
