package LiteSnacks.UI.Cashier;

import LiteSnacks.UI.LoginScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CashierMainScene {
    Scene scene;
    Stage stage;

    public CashierMainScene(double width, double height, Stage stage) {
        this.stage = stage;

        Pane root = new Pane();
        Pane loginpane = new Pane();
        // lable
        Label label = new Label("Welcome Seller");
        label.setLayoutX(41);
        label.setLayoutY(30);
        label.setFont(new Font("Arial", 30));

        //
        Button reportsButton = new Button("Get Reports");
        reportsButton.setLayoutX(70);
        reportsButton.setLayoutY(150);
        reportsButton.setPrefHeight(40);
        reportsButton.setPrefWidth(150);
        reportsButton.setStyle("-fx-background-color: #000000");
        reportsButton.setTextFill(Color.WHITE);
        reportsButton.setOnAction(event -> {
            new CashierReportsScene(width, height, stage).setScene();
        });

        // register button
        Button editButton = new Button("Edit Cashes");
        editButton.setLayoutX(70);
        editButton.setLayoutY(100);
        editButton.setPrefHeight(40);
        editButton.setPrefWidth(150);
        editButton.setStyle("-fx-background-color: #000000");
        editButton.setTextFill(Color.WHITE);
        editButton.setOnAction(event -> {
            new EditCashes(width, height, stage).setScene();
        });

        // log out button
        Button logoutButton = new Button("Log Out");
        logoutButton.setLayoutX(70);
        logoutButton.setLayoutY(200);
        logoutButton.setPrefHeight(40);
        logoutButton.setPrefWidth(150);
        logoutButton.setStyle("-fx-background-color: #000000");
        logoutButton.setTextFill(Color.WHITE);
        logoutButton.setOnAction(event -> {
            new LoginScene(width, height, stage).setScene();
        });

        loginpane.getChildren().addAll(reportsButton, label, editButton, logoutButton);
        loginpane.setLayoutX(156);
        loginpane.setLayoutY(51);
        loginpane.setPrefHeight(299);
        loginpane.setPrefWidth(289);
        loginpane.setStyle("-fx-background-color: #d9d9d9");

        root.getChildren().add(loginpane);

        scene = new Scene(root, width, height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
