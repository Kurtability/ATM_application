package LiteSnacks.UI;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Style {
    /**this file is to style component for ui**/
    public static Button createButton(String text, int x, int y, int h, int w) {
        Button button = new Button(text);
        setXY(button, x, y);
        setHW(button, h, w);
        button.setStyle("-fx-background-color: #000000;");
        button.setTextFill(Color.WHITE);
        return button;

    }

    public static void setXY(Control node, double x, double y) {
        node.setLayoutX(x);
        node.setLayoutY(y);
    }
    public static void setXY(Text node, double x, double y) {
        node.setLayoutX(x);
        node.setLayoutY(y);
    }
    public static void setXY(Button node, double x, double y) {
        node.setLayoutX(x);
        node.setLayoutY(y);
    }

    public static void setHW(Control node, double h, double w) {
        node.setPrefHeight(h);
        node.setPrefWidth(w);
    }
    public static void setXY(Pane node,double x, double y ){
        node.setLayoutX(x);
        node.setLayoutY(y);
    }
    public static void setHW(Pane node,double h, double w){
        node.setPrefHeight(h);
        node.setPrefWidth(w);
    }
    public static void setHW(Button node,double h, double w){
        node.setPrefHeight(h);
        node.setPrefWidth(w);
    }
}
