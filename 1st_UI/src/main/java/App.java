import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

public class App extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Vending machine");
        LoginScene loginScene = new LoginScene(600,400,primaryStage);
        loginScene.setScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
