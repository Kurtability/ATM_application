package LiteSnacks.UI.Owner;

import LiteSnacks.UI.LoginScene;
import LiteSnacks.backend.UserLoginHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OwnerEditUsers {
    
    Scene scene;
    Stage stage;

    public OwnerEditUsers(double width, double height, Stage stage) {
        this.stage = stage;
        Pane root = new Pane();
        Pane loginpane = new Pane();
        // lable
        Label label = new Label("Add/Remove Users");
        label.setLayoutX(30);
        label.setLayoutY(30);
        label.setFont(new Font("Arial", 26));

        TextField username = new TextField("");
        username.setPromptText("Enter Username");
        username.setLayoutX(41);
        username.setLayoutY(101);
        username.setPrefHeight(38);
        username.setPrefWidth(207);

        TextField role = new TextField("");
        role.setPromptText("Enter Role");
        role.setLayoutX(41);
        role.setLayoutY(156);
        role.setPrefHeight(38);
        role.setPrefWidth(207);

        TextField pass = new TextField("");
        pass.setPromptText("Enter password if creating user");
        pass.setLayoutX(41);
        pass.setLayoutY(211);
        pass.setPrefHeight(38);
        pass.setPrefWidth(207);

        // delete user button
        Label message = new Label("");
        
        Button delete = new Button("Delete User");
        delete.setLayoutX(171);
        delete.setLayoutY(259);
        delete.setStyle("-fx-background-color: #000000");
        delete.setTextFill(Color.WHITE);
        delete.setOnAction(event -> {
            if (new UserLoginHandler().removeUser(username.getText())) {
                message.setText("Successfully Removed User!");
            } else {
                message.setText("Please Enter a Valid Username");
            }
        });

        // add user button
        Button add = new Button("Add User");
        add.setOnAction(e ->{
            if (username.getText().equals("") || pass.getText().equals("") || role.getText().equals("")) {
                message.setText("Error Please input valid credentials");
            } else if (!role.getText().equals("owner") && !role.getText().equals("seller") && !role.getText().equals("owner")) {
                message.setText("Error Please input a valid role");
            } else {
                message.setText("New User Added!");
                new UserLoginHandler().addUser(username.getText(), pass.getText() ,role.getText());
            }
            
        });
        add.setLayoutX(40);
        add.setLayoutY(259);
        add.setStyle("-fx-background-color: #000000");
        add.setTextFill(Color.WHITE);

        // back
        Button back = new Button("back");
        back.setLayoutY(20);
        back.setLayoutX(550);
        back.setStyle("-fx-background-color: #000000");
        back.setTextFill(Color.WHITE);
        back.setOnAction(e -> {
            new OwnerEditScene(width, height, stage).setScene();
        });

        // success or not scene

        // select role drop down
        /*MenuButton menuButton = new MenuButton("Select Role");
        MenuItem cashier = new MenuItem("Cashier");
        MenuItem seller = new MenuItem("Seller");
        MenuItem owner = new MenuItem("Owner");
        menuButton.getItems().addAll(cashier, seller, owner);*/


        loginpane.getChildren().addAll(username, pass, role, add, delete, label);
        loginpane.setLayoutX(156);
        loginpane.setLayoutY(51);
        loginpane.setPrefHeight(299);
        loginpane.setPrefWidth(289);
        loginpane.setStyle("-fx-background-color: #d9d9d9");

        root.getChildren().add(loginpane);
        root.getChildren().add(back);

        message.setLayoutX(210);
        message.setLayoutY(375);
        root.getChildren().add(message);

        scene = new Scene(root, width, height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
