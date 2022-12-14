package LiteSnacks.UI;

import LiteSnacks.backend.UserLoginHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegisterScene {
    Scene scene;
    Stage stage;

    RegisterScene(double width, double height, Stage stage) {

        this.stage = stage;
        Pane root = new Pane();
        Pane loginpane = new Pane();
        // lable
        Label label = new Label("Register");
        label.setLayoutX(41);
        label.setLayoutY(30);
        label.setFont(new Font("Arial", 30));

        // username and password
        TextField username = new TextField("");
        username.setPromptText("username");
        username.setLayoutX(41);
        username.setLayoutY(101);
        username.setPrefHeight(38);
        username.setPrefWidth(207);

        TextField password = new TextField("");
        password.setPromptText("password");
        password.setLayoutX(41);
        password.setLayoutY(156);
        password.setPrefHeight(38);
        password.setPrefWidth(207);

        // log in button
        Button loginButton = new Button("Log In");
        loginButton.setLayoutX(180);
        loginButton.setLayoutY(222);
        loginButton.setStyle("-fx-background-color: #000000");
        loginButton.setTextFill(Color.WHITE);
        loginButton.setOnAction(event -> {
            new LoginScene(width, height, stage).setScene();
        });

        // register button
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e ->{
            new UserLoginHandler().addCustomer(username.getText(),password.getText());
        });
        registerButton.setLayoutX(70);
        registerButton.setLayoutY(222);
        registerButton.setStyle("-fx-background-color: #000000");
        registerButton.setTextFill(Color.WHITE);

        // back
        Button back = new Button(">back to products");
        back.setLayoutY(20);
        back.setLayoutX(440);
        back.setStyle("-fx-background-color: #000000");
        back.setTextFill(Color.WHITE);
        back.setOnAction(e -> {
            new Products(width, height, stage).setScene();
        });

        loginpane.getChildren().addAll(username, password, registerButton, loginButton, label);
        loginpane.setLayoutX(156);
        loginpane.setLayoutY(51);
        loginpane.setPrefHeight(299);
        loginpane.setPrefWidth(289);
        loginpane.setStyle("-fx-background-color: #d9d9d9");

        root.getChildren().add(loginpane);
        root.getChildren().add(back);

        scene = new Scene(root, width, height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
