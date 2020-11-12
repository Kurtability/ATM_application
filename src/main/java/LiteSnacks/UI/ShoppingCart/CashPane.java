package LiteSnacks.UI.ShoppingCart;

import LiteSnacks.UI.Style;
import LiteSnacks.backend.Cash;
import LiteSnacks.UI.QuantityModifier;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static LiteSnacks.UI.Style.setXY;

public class CashPane {
    Pane pane;
    Cash cash;

    public CashPane(Cash cash,CashPayment cashPayment) {
        this.cash = cash;
        this.pane = new Pane();
        this.pane.setPrefWidth(100);
        this.pane.setPrefHeight(120);
        this.pane.setStyle("-fx-background-color: #d9d9d9;");

        Pane qtymdfier = new QuantityModifier(cash,cashPayment).getPane();
        setXY(qtymdfier,13,90);

        // value
        Text value = new Text();
        if (cash.getValue() >= 1){
            value.setText("$ " + (int)cash.getValue());
        }else{
            value.setText((int)(cash.getValue()*100)+ "c");
        }
        value.setFont(new Font(28));
        setXY(value,15,65);




        this.pane.getChildren().addAll(value, qtymdfier);

    }

    public Pane getPane() {
        return this.pane;
    }
    public Cash getCash() {return this.cash;}


}
