package LiteSnacks.UI.Cashier;

import LiteSnacks.UI.Products;
import LiteSnacks.UI.RegisterScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CashierReportsScene {
    Scene scene;
    Stage stage;

    public CashierReportsScene(double width, double height,Stage stage){
        this.stage = stage;

        Pane root = new Pane();
        Pane loginpane = new Pane();

        //lable
        Label label = new Label("What do you want");
        label.setLayoutX(21);
        label.setLayoutY(30);
        label.setFont(new Font("Arial", 30));




        //Get cashes reports button
        Button cashesReports = new Button("Cashes Report");
        cashesReports.setLayoutX(70);
        cashesReports.setLayoutY(150);
        cashesReports.setPrefHeight(40);
        cashesReports.setPrefWidth(150);
        cashesReports.setStyle("-fx-background-color: #000000");
        cashesReports.setTextFill(Color.WHITE);
        cashesReports.setOnAction(event -> {

            /*** please add a function to write report in a file
             * ***/
        });


        //item summary
        Button itemSummary = new Button("Transactions Summary");
        itemSummary.setLayoutX(70);
        itemSummary.setLayoutY(100);
        itemSummary.setPrefHeight(40);
        itemSummary.setPrefWidth(150);
        itemSummary.setStyle("-fx-background-color: #000000");
        itemSummary.setTextFill(Color.WHITE);
        itemSummary.setOnAction(event -> {

            /*** please add a function to write report in a file
             * ***/
        });

        loginpane.getChildren().addAll(label,itemSummary,cashesReports);
        loginpane.setLayoutX(156);
        loginpane.setLayoutY(51);
        loginpane.setPrefHeight(299);
        loginpane.setPrefWidth(289);
        loginpane.setStyle("-fx-background-color: #d9d9d9");

        // back


        root.getChildren().add(loginpane);

        scene = new Scene(root,width,height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
