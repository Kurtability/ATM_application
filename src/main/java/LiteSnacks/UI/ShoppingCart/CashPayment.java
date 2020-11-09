package LiteSnacks.UI.ShoppingCart;

import LiteSnacks.UI.Cash;
import LiteSnacks.UI.Cashier.EditCashPane;
import LiteSnacks.UI.Seller.SellerMainScene;
import LiteSnacks.backend.CashHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CashPayment {
    Scene scene;
    Stage stage;
    List<CashPane> CashPanes;
    List<Cash> cashes;

    public CashPayment(double width, double height, Stage stage) {
        Pane root = new Pane();
        this.stage = stage;

        /***
         * retrieve cash from file
         ***/
        CashHandler ch = new CashHandler();
        cashes = ch.getcashes();
        this.CashPanes = getCashPanes(cashes);
        Pane cashWholePane = getPaneForCashes(this.CashPanes);

        // Pane for cashes
        Pane CashPane = new Pane();
        cashWholePane.setPrefHeight(304);
        cashWholePane.setPrefWidth(580);
        cashWholePane.setLayoutX(10);
        cashWholePane.setLayoutY(25);

        // label
        Text label = new Text("Edit Cashes");
        label.setLayoutX(10);
        label.setLayoutY(20);
        label.setFont(new Font(20));
        label.setFill(Color.rgb(0, 66, 127));

        // SUBMIT BUTTON
        Button submit1 = createButton("submit", 500, 360, 27, 81);
        submit1.setOnAction(e -> {
            submit();
        });

        // back
        Button back = new Button(">back");
        back.setStyle("-fx-background-color: transparent;");
        back.setFont(new Font(20));
        back.setOnAction(e -> {
            new SellerMainScene(width, height, stage).setScene();
        });
        back.setLayoutX(500);
        back.setLayoutY(1);

        root.getChildren().addAll(CashPane, cashWholePane, label, back);
        root.getChildren().add(submit1);
        this.scene = new Scene(root, width, height);
    }

    public Button createButton(String text, int x, int y, int h, int w) {
        Button button = new Button(text);
        this.setXY(button, x, y);
        this.setHW(button, h, w);
        button.setStyle("-fx-background-color: #000000;");
        button.setTextFill(Color.WHITE);
        return button;

    }

    public void submit() {
        System.out.println(1);
        CashHandler ch = new CashHandler();
        ch.Submit(this.cashes);
    }

    public List<CashPane> getCashPanes(List<Cash> cashes) {
        List<CashPane> panes = new ArrayList<CashPane>();
        for (Cash each : cashes) {
            CashPane pane = new CashPane(each);
            panes.add(pane);
        }
        return panes;
    }

    public Pane getPaneForCashes(List<CashPane> panes) {
        Pane box = new Pane();
        int row = 0;
        int column = 0;

        for (CashPane cashPane : panes) {
            Pane each = cashPane.getPane();
            each.setLayoutX(120 * row);
            each.setLayoutY(130 * column);
            box.getChildren().add(each);
            row = row + 1;
            if (row == 5) {
                column = column + 1;
                row = 0;
            }

        }
        return box;
    }

    public void setXY(Control node, double x, double y) {
        node.setLayoutX(x);
        node.setLayoutY(y);
    }

    public void setHW(Control node, double h, double w) {
        node.setPrefHeight(h);
        node.setPrefWidth(w);
    }

    public void setScene() {
        stage.setScene(scene);
    }
    public Scene getScene(){
        return this.scene;
    }
    public List<Cash> getCashes(){return this.cashes;}
}
