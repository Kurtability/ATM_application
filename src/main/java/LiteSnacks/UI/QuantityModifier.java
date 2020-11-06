package LiteSnacks.UI;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class QuantityModifier {
    Pane pane;

    public QuantityModifier(Item item) {
        // item is object that store qty refrence
        Text qtylabel = new Text(String.valueOf(item.qty));

        Button addQty = new Button("+");
        addQty.setTextFill(Color.WHITE);
        addQty.setOnAction(event -> {
            modifyQty(item, qtylabel, 1);
        });
        Button subQty = new Button("-");
        subQty.setTextFill(Color.WHITE);
        subQty.setOnAction(event -> {
            modifyQty(item, qtylabel, -1);
        });

        this.pane = new Pane();
        this.pane.setPrefHeight(28);
        this.pane.setPrefWidth(75);

        addQty.setPrefWidth(27);
        addQty.setPrefHeight(27);
        subQty.setPrefHeight(27);
        subQty.setPrefWidth(27);

        addQty.setLayoutX(48);
        qtylabel.setLayoutX(30);
        qtylabel.setLayoutY(18);
        subQty.setLayoutX(0);

        addQty.setStyle("-fx-background-color: #000000;");
        subQty.setStyle("-fx-background-color: #000000;");

        this.pane.setStyle("-fx-background-color: #ffffff;");
        this.pane.getChildren().addAll(addQty, qtylabel, subQty);
    }

    public QuantityModifier(Cash item) {
        // item is object that store qty refrence
        Text qtylabel = new Text(String.valueOf(item.qty));

        Button addQty = new Button("+");
        addQty.setTextFill(Color.WHITE);
        addQty.setOnAction(event -> {
            modifyQty(item, qtylabel, 1);
        });
        Button subQty = new Button("-");
        subQty.setTextFill(Color.WHITE);
        subQty.setOnAction(event -> {
            modifyQty(item, qtylabel, -1);
        });

        this.pane = new Pane();
        this.pane.setPrefHeight(28);
        this.pane.setPrefWidth(75);

        addQty.setPrefWidth(27);
        addQty.setPrefHeight(27);
        subQty.setPrefHeight(27);
        subQty.setPrefWidth(27);

        addQty.setLayoutX(48);
        qtylabel.setLayoutX(30);
        qtylabel.setLayoutY(18);
        subQty.setLayoutX(0);

        addQty.setStyle("-fx-background-color: #000000;");
        subQty.setStyle("-fx-background-color: #000000;");

        this.pane.setStyle("-fx-background-color: #ffffff;");
        this.pane.getChildren().addAll(addQty, qtylabel, subQty);
    }

    public Pane getPane() {
        return this.pane;
    }

    public void modifyQty(Item item, Text label, int gap) {
        item.modifyqty(gap);
        label.setText(String.valueOf(item.qty));
    }

    public void modifyQty(Cash item, Text label, int gap) {

        item.modifyqty(gap);
        label.setText(String.valueOf(item.qty));
    }
}
