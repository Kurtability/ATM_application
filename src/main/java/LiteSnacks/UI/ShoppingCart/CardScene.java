package LiteSnacks.UI.ShoppingCart;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.geometry.*;

import LiteSnacks.backend.*;
import LiteSnacks.UI.Products;

public class CardScene {
    Scene scene;
    Stage stage;

    public CardScene(double width, double height, Stage stage, Cart cart) {
        this.stage = stage;
        Pane root = new Pane();

        Label price = new Label("Total Price: $" + Double.toString(cart.getTotal()));
        price.setLayoutX(200);
        price.setLayoutY(335);
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
        cardLabel.setLayoutX(75);
        cardLabel.setLayoutY(225);
        cardLabel.setFont(new Font("Arial", 15));

//        Label details = new Label("Enter Expiry Date:");
//        details.setLayoutX(75);
//        details.setLayoutY(275);
//        details.setFont(new Font("Arial", 15));

        Label givenName = new Label("Enter Full Name:");
        givenName.setLayoutX(150);
        givenName.setLayoutY(175);
        givenName.setFont(new Font("Arial", 15));

        TextField name = new TextField();
        name.setPromptText("Please Enter Full Name");
        name.setLayoutX(275);
        name.setLayoutY(175);
        
        TextField number = new TextField();
        number.setPromptText("Enter Card Number");
        number.setLayoutX(220);
        number.setLayoutY(225);

//        TextField expDate = new TextField();
//        expDate.setPromptText("Enter Expiry Date");
//        expDate.setLayoutX(220);
//        expDate.setLayoutY(275);
//
//        TextField cvv = new TextField();
//        cvv.setPromptText("Enter Card CVV");
//        cvv.setLayoutX(375);
//        cvv.setLayoutY(275);

        Button pay = new Button("Pay Now");
        pay.setLayoutX(350);
        pay.setLayoutY(335);
        pay.setTextFill(Color.WHITE);
        pay.setStyle("-fx-background-color: #000000");
        pay.setOnAction(event -> {if(CreditCardHandler.checkCard(name.getText(), number.getText())) {
                                    new Products(width,height,stage).setScene();}});

        Button cancel = new Button("Cancel Transaction");
        cancel.setLayoutX(25);
        cancel.setLayoutY(430);
        cancel.setStyle("-fx-background-color: #DC143C");
        cancel.setTextFill(Color.WHITE);
        cancel.setOnAction(event -> {new Products(width,height,stage).setScene();});

        root.getChildren().addAll(name, givenName, cardLabel, cancel, title2, pay, price, title, number);

        scene = new Scene(root, width, height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
