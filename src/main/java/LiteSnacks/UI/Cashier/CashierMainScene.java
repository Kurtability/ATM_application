package LiteSnacks.UI.Cashier;

import LiteSnacks.UI.LoginScene;
import LiteSnacks.UI.Style;
import LiteSnacks.backend.UserLoginHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static LiteSnacks.UI.Style.setHW;
import static LiteSnacks.UI.Style.setXY;

public class CashierMainScene {
    Scene scene;
    Stage stage;

    public CashierMainScene(double width, double height, Stage stage) {
        this.stage = stage;

        Pane root = new Pane();
        Pane loginpane = new Pane();
        // lable
        Label label = new Label("Welcome Cashier");
        label.setLayoutX(41);
        label.setLayoutY(30);
        label.setFont(new Font("Arial", 30));

        // reports
        Button reportsButton = new Button("Get Reports");
        setXY(reportsButton,70,150);
        setHW(reportsButton,40,150);
        reportsButton.setStyle("-fx-background-color: #000000");
        reportsButton.setTextFill(Color.WHITE);
        reportsButton.setOnAction(event -> {
            new CashierReportsScene(width, height, stage).setScene();

        });

        // edit cashes
        Button editButton = new Button("Edit Cashes");
        setXY(editButton,70,100);
        setHW(editButton,40,150);
        editButton.setStyle("-fx-background-color: #000000");
        editButton.setTextFill(Color.WHITE);
        editButton.setOnAction(event -> {
            new EditCashes(width, height, stage,false).setScene();
        });

        // log out button
        Button logoutButton = new Button("Log Out");
        setXY(logoutButton,70,200);
        setHW(logoutButton,40,150);
        logoutButton.setStyle("-fx-background-color: #000000");
        logoutButton.setTextFill(Color.WHITE);
        logoutButton.setOnAction(event -> {
            UserLoginHandler.setCurrentUser(null);
            new LoginScene(width, height, stage).setScene();
        });

        loginpane.getChildren().addAll(reportsButton, label, editButton, logoutButton);
        setXY(loginpane,156,61);
        setHW(loginpane,289,299);
        loginpane.setStyle("-fx-background-color: #d9d9d9");

        root.getChildren().add(loginpane);

        scene = new Scene(root, width, height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
