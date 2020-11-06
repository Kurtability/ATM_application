package LiteSnacks.UI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.geometry.*;
import LiteSnacks.UI.ShoppingCart.Cart;





public class Checkout {
    Scene scene;
    Stage stage;
    
    public Checkout(double width, double height,Stage stage){
        this.stage = stage;

        Pane root = new Pane();
        //lable
        Cart cart = new Cart();
        Label label = new Label("Total Price: $" + Double.toString(cart.CalculateCartTotalPrice()));
        label.setLayoutX(400);
        label.setLayoutY(150);
        label.setFont(new Font("Arial", 20));


        

        Label label2 = new Label("ChEcKouT");
        label2.setLayoutX(400);
        label2.setLayoutY(30);
        label2.setFont(new Font("Arial", 30));

        // username and password
        TableView items = new TableView();
        TableColumn<Item, String> column1 = new TableColumn<>("Product");
        column1.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        
        TableColumn<Item, String> column2 = new TableColumn<>("Quantity");
        column2.setCellValueFactory(new PropertyValueFactory<>("qty"));

        TableColumn<Item, String> column3 = new TableColumn<>("Price");
        column3.setCellValueFactory(new PropertyValueFactory<>("price"));

        items.getColumns().add(column1);
        items.getColumns().add(column2);
        items.getColumns().add(column3);

        items.getItems().add(new Item("Coke", 4, 2));
        items.getItems().add(new Item("Snickers", 3, 1));
        items.getItems().add(new Item("Water", 2, 1));
        
        items.setLayoutX(50);
        items.setLayoutY(50);

        items.setPrefWidth(350);
        items.setPrefHeight(325);
        items.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox box = new VBox(items);
        box.setPadding(new Insets(40, 25, 25, 25));;
        
        
        


        


        Button card = new Button("Card");
        card.setLayoutX(475);
        card.setLayoutY(200);
        card.setStyle("-fx-background-color: #000000");
        card.setTextFill(Color.WHITE);

        Button cash = new Button("Cash");
        cash.setLayoutX(400);
        cash.setLayoutY(200);
        cash.setStyle("-fx-background-color: #000000");
        cash.setTextFill(Color.WHITE);

       

        Pane specs = new Pane();
        specs.getChildren().addAll(card, cash, label, label2);
        root.getChildren().add(box);
        root.getChildren().add(specs);
        root.setStyle("-fx-background-color: #d9d9d9");

        scene = new Scene(root,width,height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}