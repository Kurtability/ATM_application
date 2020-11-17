package LiteSnacks.UI.Seller;

import LiteSnacks.UI.Cashier.CashierMainScene;
import LiteSnacks.UI.Style;
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

public class SellerReportsScene {
    Scene scene;
    Stage stage;

    public SellerReportsScene(double width, double height, Stage stage) {
        this.stage = stage;

        Pane root = new Pane();
        Pane loginpane = new Pane();

        // back
        Button back = new Button(">back");
        back.setStyle("-fx-background-color: transparent;");
        back.setFont(new Font(20));
        back.setOnAction(e -> {
            new SellerMainScene(width, height, stage).setScene();
        });
        setXY(back,500,10);


        // lable
        Text label = new Text("What do you want");
        setXY(label,21,30);
        label.setFont(new Font("Arial", 30));

        // Get cashes reports button
        Button cashesReports = new Button("Products Report");
        setXY(cashesReports,70,150);
        setHW(cashesReports,40,150);
        cashesReports.setStyle("-fx-background-color: #000000");
        cashesReports.setTextFill(Color.WHITE);
        cashesReports.setOnAction(event -> {

            /***
             * please add a function to write report in a file
             ***/
        });

        // item summary
        Button itemSummary = new Button("Products Summary");
        setXY(itemSummary,70,100);
        setHW(itemSummary,40,150);
        itemSummary.setStyle("-fx-background-color: #000000");
        itemSummary.setTextFill(Color.WHITE);
        itemSummary.setOnAction(event -> {

            /***
             * please add a function to write report in a file
             ***/
        });

        loginpane.getChildren().addAll(label, itemSummary, cashesReports);
        setXY(loginpane,156,51);
        setHW(loginpane,299,289);
        loginpane.setStyle("-fx-background-color: #d9d9d9");

        // back

        root.getChildren().addAll(loginpane, back);

        scene = new Scene(root, width, height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
