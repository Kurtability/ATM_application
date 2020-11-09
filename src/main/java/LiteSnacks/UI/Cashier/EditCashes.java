package LiteSnacks.UI.Cashier;

import LiteSnacks.UI.Cash;
import LiteSnacks.UI.Seller.SellerMainScene;
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

public class EditCashes {
    Scene scene;
    Stage stage;
    List<EditCashPane> editCashPanes;
    List<Cash> cashes;

    public EditCashes(double width, double height, Stage stage) {
        Pane root = new Pane();
        this.stage = stage;

        /***
         * retrieve cash from file
         ***/
        CashHandler ch = new CashHandler();
        cashes = ch.getcashes();
        this.editCashPanes = getEditCashPanes(cashes);
        Pane cashWholePane = getPaneForCashes(this.editCashPanes);

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

    public List<EditCashPane> getEditCashPanes(List<Cash> cashes) {
        List<EditCashPane> panes = new ArrayList<EditCashPane>();
        for (Cash each : cashes) {
            EditCashPane pane = new EditCashPane(each);
            panes.add(pane);
        }
        return panes;
    }

    public Pane getPaneForCashes(List<EditCashPane> panes) {
        Pane box = new Pane();
        int row = 0;
        int column = 0;

        for (EditCashPane editProductPane : panes) {
            Pane each = editProductPane.getPane();
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
}
