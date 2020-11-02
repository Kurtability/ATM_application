<<<<<<< HEAD
package LiteSnacks.UI;

=======
>>>>>>> d2b296169ffd209d0cac0d182b8b39fbd977f87a
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginScene {
    Scene scene;
    Stage stage;
    
    public LoginScene(double width, double height,Stage stage){
        this.stage = stage;

        Pane root = new Pane();
        Pane loginpane = new Pane();
        //lable
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





        //log in button
        Button loginButton = new Button("Login");
        loginButton.setLayoutX(111);
        loginButton.setLayoutY(222);
        loginButton.setStyle("-fx-background-color: #000000");
        loginButton.setTextFill(Color.WHITE);
        loginButton.setOnAction(event -> {new Products(width,height,stage).setScene();});


        //register button
        Button registerButton = new Button("Register");
        registerButton.setLayoutX(180);
        registerButton.setLayoutY(35);
        registerButton.setStyle("-fx-background-color: #000000");
        registerButton.setTextFill(Color.WHITE);
        registerButton.setOnAction(event -> {new RegisterScene(width,height,stage).setScene();});

        loginpane.getChildren().addAll(loginButton,password,username,label,registerButton);
        loginpane.setLayoutX(156);
        loginpane.setLayoutY(51);
        loginpane.setPrefHeight(299);
        loginpane.setPrefWidth(289);
        loginpane.setStyle("-fx-background-color: #d9d9d9");


        root.getChildren().add(loginpane);

        scene = new Scene(root,width,height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
