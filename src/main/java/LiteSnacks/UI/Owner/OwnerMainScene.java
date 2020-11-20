package LiteSnacks.UI.Owner;

import LiteSnacks.UI.Cashier.CashierReportsScene;
import LiteSnacks.UI.Cashier.EditCashes;
import LiteSnacks.UI.LoginScene;
import LiteSnacks.backend.UserLoginHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static LiteSnacks.UI.Style.setHW;
import static LiteSnacks.UI.Style.setXY;

public class OwnerMainScene {
    Scene scene;
    Stage stage;

    public OwnerMainScene(double width, double height, Stage stage) {
        this.stage = stage;

        Pane root = new Pane();
        Pane pane = new Pane();
        // lable
        Label label = new Label("Welcome Owner");
        label.setLayoutX(41);
        label.setLayoutY(30);
        label.setFont(new Font("Arial", 30));

        // reports
        Button reportsButton = new Button("Get Reports");
        setXY(reportsButton,55,150);
        setHW(reportsButton,40,180);
        reportsButton.setStyle("-fx-background-color: #000000");
        reportsButton.setTextFill(Color.WHITE);
        reportsButton.setOnAction(event -> {
            new OwnerReportsScene(width, height, stage).setScene();
        });

        // edit cashes/products/roles
        Button editButton = new Button("Edit Cashes/Products/Users");
        setXY(editButton,55,100);
        setHW(editButton,40,180);
        editButton.setStyle("-fx-background-color: #000000");
        editButton.setTextFill(Color.WHITE);
        editButton.setOnAction(event -> {
            new OwnerEditScene(width, height, stage).setScene();
        });

        // log out button
        Button logoutButton = new Button("Log Out");
        setXY(logoutButton,55,200);
        setHW(logoutButton,40,180);
        logoutButton.setStyle("-fx-background-color: #000000");
        logoutButton.setTextFill(Color.WHITE);
        logoutButton.setOnAction(event -> {
            UserLoginHandler.setCurrentUser(null);
            new LoginScene(width, height, stage).setScene();
        });

        pane.getChildren().addAll(reportsButton, label, editButton, logoutButton);
        setXY(pane,156,61);
        setHW(pane,289,299);
        pane.setStyle("-fx-background-color: #d9d9d9");

        root.getChildren().add(pane);

        scene = new Scene(root, width, height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}

