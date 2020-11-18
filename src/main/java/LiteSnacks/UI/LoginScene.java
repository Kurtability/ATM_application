package LiteSnacks.UI;

import LiteSnacks.UI.Cashier.CashierMainScene;
import LiteSnacks.UI.Owner.OwnerMainScene;
import LiteSnacks.UI.Seller.SellerMainScene;
import LiteSnacks.backend.UserAccount.UserAccount;
import LiteSnacks.backend.UserLoginHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class LoginScene {
    Scene scene;
    Stage stage;

    UserLoginHandler handler;
    List<UserAccount> users;

    public LoginScene(double width, double height, Stage stage) {
        this.stage = stage;

        Pane root = new Pane();
        Pane loginpane = new Pane();
        // lable
        Label label = new Label("Log In");
        label.setLayoutX(41);
        label.setLayoutY(30);
        label.setFont(new Font("Arial", 30));

        // username and password
        TextField username = new TextField("username");
        username.setLayoutX(41);
        username.setLayoutY(101);
        username.setPrefHeight(38);
        username.setPrefWidth(207);

        TextField password = new TextField("password");
        password.setLayoutX(41);
        password.setLayoutY(156);
        password.setPrefHeight(38);
        password.setPrefWidth(207);

        // log in button
        Button loginButton = new Button("Login");
        loginButton.setLayoutX(111);
        loginButton.setLayoutY(222);
        loginButton.setStyle("-fx-background-color: #000000");
        loginButton.setTextFill(Color.WHITE);


        handler = new UserLoginHandler();
        users = handler.getUsers();
        loginButton.setOnAction(event -> {
            if (handler.checkUser(username.getText(),password.getText().hashCode())){
                for (UserAccount u: users){
                    //System.out.println(u.getRole());

//                    if (u.getRole().equals("customer")){
//                        new Products(width, height, stage).setScene();
//                        System.out.println("whyyy");
//                    }

                    if (u.getRole().contains("cashier") && u.getUserName().equals(username.getText())) {
                        new CashierMainScene(width, height, stage).setScene();
                        System.out.println("cashier page");
                    }
                    else if (u.getRole().contains("seller") && u.getUserName().equals(username.getText())){
                        new SellerMainScene(width, height, stage).setScene();
                        System.out.println("seller page");
                    }
                    else if (u.getRole().contains("owner") && u.getUserName().equals(username.getText())){
                        new OwnerMainScene(width, height, stage).setScene();
                        System.out.println("owner page");
                    }
                    else if (u.getRole().contains("customer") && u.getUserName().equals(username.getText())){
                        new Products(width, height, stage).setScene();
                    }
                }
            }
            else {
                System.out.println("Login Failed :(");
            }
        });

        // register button
        Button registerButton = new Button("Register");
        registerButton.setLayoutX(180);
        registerButton.setLayoutY(35);
        registerButton.setStyle("-fx-background-color: #000000");
        registerButton.setTextFill(Color.WHITE);
        registerButton.setOnAction(event -> {
            new RegisterScene(width, height, stage).setScene();
        });

        loginpane.getChildren().addAll(loginButton, password, username, label, registerButton);
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
