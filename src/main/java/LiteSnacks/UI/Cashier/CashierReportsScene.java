package LiteSnacks.UI.Cashier;

import LiteSnacks.UI.Seller.SellerMainScene;
import LiteSnacks.UI.Style;
import LiteSnacks.backend.Cash;
import LiteSnacks.backend.CashHandler;
import LiteSnacks.backend.ReportsHandler;
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

public class CashierReportsScene {
    Scene scene;
    Stage stage;
    Text path = new Text();


    public CashierReportsScene(double width, double height,Stage stage){

        this.stage = stage;

        Pane root = new Pane();
        Pane loginpane = new Pane();

        path.setVisible(false);
        setXY(path,10,250);

        //lable
        Label label = new Label("What do you want");
        label.setLayoutX(21);
        label.setLayoutY(30);
        label.setFont(new Font("Arial", 30));

        //back
        Button back = new Button(">back");
        back.setStyle("-fx-background-color: transparent;");
        back.setFont(new Font(20));
        back.setOnAction(e -> {new CashierMainScene(width,height,stage).setScene();});
        setXY(back,500,10);


        ReportsHandler handler = new ReportsHandler();
        //Get cashes reports button
        Button cashesReports = new Button("Cashes Report");
        setXY(cashesReports,70,150);
        setHW(cashesReports,40,150);
        cashesReports.setStyle("-fx-background-color: #000000");
        cashesReports.setTextFill(Color.WHITE);
        cashesReports.setOnAction(event -> {
            path.setText(handler.getCashesReport());
            path.setVisible(true);


        });


        //item summary
        Button itemSummary = new Button("Transactions Summary");
        setXY(itemSummary,70,100);
        setHW(itemSummary,40,150);
        itemSummary.setStyle("-fx-background-color: #000000");
        itemSummary.setTextFill(Color.WHITE);
        itemSummary.setOnAction(event -> {
            path.setText(handler.getTransactionsReport());
            path.setVisible(true);
        });

        loginpane.getChildren().addAll(label,itemSummary,cashesReports,path);
        setXY(loginpane,156,51);
        setHW(loginpane,299,289);
        loginpane.setStyle("-fx-background-color: #d9d9d9");

        // back


        root.getChildren().addAll(loginpane,back);

        scene = new Scene(root,width,height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
