package LiteSnacks.UI.Seller;

import LiteSnacks.UI.Cashier.CashierMainScene;
import LiteSnacks.UI.Style;
import LiteSnacks.backend.ProductsHandler;
import LiteSnacks.backend.Transaction;
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
        Pane pane = new Pane();

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

        //
        Text pathToReport = new Text("");
        setXY(pathToReport, 100, 400);

        // Get product reports button
        Button productsReports = new Button("Products Report");
        setXY(productsReports,70,150);
        setHW(productsReports,40,150);
        productsReports.setStyle("-fx-background-color: #000000");
        productsReports.setTextFill(Color.WHITE);
        productsReports.setOnAction(event -> {
            ProductsHandler ph = new ProductsHandler();
            pathToReport.setText(ph.generateReport(ph.listOfItems()));
        });

        // item summary
        Button itemSummary = new Button("Products Summary");
        setXY(itemSummary,70,100);
        setHW(itemSummary,40,150);
        itemSummary.setStyle("-fx-background-color: #000000");
        itemSummary.setTextFill(Color.WHITE);
        itemSummary.setOnAction(event -> {
            pathToReport.setText(Transaction.generateReport());
        });

        pane.getChildren().addAll(label, itemSummary, productsReports);
        setXY(pane,156,51);
        setHW(pane,299,289);
        pane.setStyle("-fx-background-color: #d9d9d9");

        // back


        root.getChildren().addAll(pane, back, pathToReport);


        scene = new Scene(root, width, height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
