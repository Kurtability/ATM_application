package LiteSnacks.UI.Owner;

import LiteSnacks.UI.Cashier.CashesReport;
import LiteSnacks.UI.Cashier.CashierMainScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static LiteSnacks.UI.Style.setHW;
import static LiteSnacks.UI.Style.setXY;

public class OwnerReportsScene {
    Scene scene;
    Stage stage;

    public OwnerReportsScene(double width, double height, Stage stage){
        this.stage = stage;

        Pane root = new Pane();
        Pane pane = new Pane();

        //lable
        Label label = new Label("What do you want");
        label.setLayoutX(21);
        label.setLayoutY(30);
        label.setFont(new Font("Arial", 30));

        //back
        Button back = new Button(">back");
        back.setStyle("-fx-background-color: transparent;");
        back.setFont(new Font(20));
        back.setOnAction(e -> {new OwnerMainScene(width,height,stage).setScene();});
        setXY(back,500,10);


        //Get cashes reports button
        Button cashesReports = new Button("Cashes Report");
        setXY(cashesReports,10,150);
        setHW(cashesReports,40,150);
        cashesReports.setStyle("-fx-background-color: #000000");
        cashesReports.setTextFill(Color.WHITE);
        cashesReports.setOnAction(event -> {
            new CashesReport(width,height,stage,true).setScene();

        });


        //transactions summary
        Button transactionsSummary = new Button("Transactions Summary");
        setXY(transactionsSummary,10,100);
        setHW(transactionsSummary,40,150);
        transactionsSummary.setStyle("-fx-background-color: #000000");
        transactionsSummary.setTextFill(Color.WHITE);
        transactionsSummary.setOnAction(event -> {

            /*** please add a function to write report in a file
             * ***/
        });



        // Get products reports button
        Button productsReports = new Button("Owner Report");
        setXY(productsReports,170,150);
        setHW(productsReports,40,150);
        productsReports.setStyle("-fx-background-color: #000000");
        productsReports.setTextFill(Color.WHITE);
        productsReports.setOnAction(event -> {

            /***
             * please add a function to write report in a file
             ***/
        });

        // item summary
        Button itemSummary = new Button("Products Summary");
        setXY(itemSummary,170,100);
        setHW(itemSummary,40,150);
        itemSummary.setStyle("-fx-background-color: #000000");
        itemSummary.setTextFill(Color.WHITE);
        itemSummary.setOnAction(event -> {

            /***
             * please add a function to write report in a file
             ***/
        });


        // Get user reports button
        Button userReports = new Button("Users Report");
        setXY(userReports,330,150);
        setHW(userReports,40,150);
        userReports.setStyle("-fx-background-color: #000000");
        userReports.setTextFill(Color.WHITE);
        userReports.setOnAction(event -> {


        });

        // Cancel Transactions
        Button cancelTransactions = new Button("Canceled Transactions");
        setXY(cancelTransactions,330,100);
        setHW(cancelTransactions,40,150);
        cancelTransactions.setStyle("-fx-background-color: #000000");
        cancelTransactions.setTextFill(Color.WHITE);
        cancelTransactions.setOnAction(event -> {

            /***
             * please add a function to write report in a file
             ***/
        });





        pane.getChildren().addAll(label,transactionsSummary,cashesReports,
                productsReports,itemSummary,
                userReports,cancelTransactions);
        setXY(pane,70,51);
        setHW(pane,280,500);
        pane.setStyle("-fx-background-color: #d9d9d9");

        // back


        root.getChildren().addAll(pane,back);

        scene = new Scene(root,width,height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}