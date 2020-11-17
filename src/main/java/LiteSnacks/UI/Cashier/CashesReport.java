package LiteSnacks.UI.Cashier;

import LiteSnacks.UI.Seller.SellerMainScene;
import LiteSnacks.UI.Style;
import LiteSnacks.backend.Cash;
import LiteSnacks.backend.CashHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static LiteSnacks.UI.Style.*;

public class CashesReport {
    Scene scene;
    Stage stage;

    public CashesReport(double width, double height, Stage stage,boolean isOwner) {
        Pane root = new Pane();
        this.stage = stage;


        CashHandler ch = new CashHandler();
        //pane for report
        ScrollPane pane = new ScrollPane();
        Pane box = new Pane();
        Text text = new Text(/**ch.getStringOfCashesReport(ch.readFile())**/);
        box.getChildren().add(text);

        pane.setContent(box);
        setXY(pane,10,55);
        setHW(pane,280,580);


        // label
        Text label = new Text("Cashes Report");
        setXY(label,10,20);
        label.setFont(new Font(20));
        label.setFill(Color.rgb(0, 66, 127));


        // back
        Button back = new Button(">back");
        back.setStyle("-fx-background-color: transparent;");
        back.setFont(new Font(20));
        back.setOnAction(e -> {
            if(isOwner){

            }else{
                new CashierReportsScene(width, height, stage).setScene();
            }

        });
        back.setLayoutX(500);
        back.setLayoutY(1);

        root.getChildren().addAll( pane, label, back);
        this.scene = new Scene(root, width, height);
    }







    public void setScene() {
        stage.setScene(scene);
    }
    public Scene getScene(){
        return this.scene;
    }
}