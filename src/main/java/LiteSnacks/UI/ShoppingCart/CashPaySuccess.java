package LiteSnacks.UI.ShoppingCart;

import LiteSnacks.UI.Products;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static LiteSnacks.UI.Style.createButton;
import static LiteSnacks.UI.Style.setXY;

public class CashPaySuccess {
    Scene scene;
    Stage stage;
    Text userChange;

    public CashPaySuccess(double width, double height, Stage stage, String change) {
        Pane root = new Pane();
        this.stage = stage;

        Text welcomeText = new Text("Here's your change. Enjoy your goods!");
        setXY(welcomeText,150,200);
        welcomeText.setFont(new Font(20));
        welcomeText.setFill(Color.rgb(0, 66, 127));

        userChange = new Text(change);
        setXY(userChange,170,240);
        userChange.setFont(new Font(20));
        userChange.setFill(Color.rgb(0, 66, 127));

        Button submit1 = createButton("Finish", 500, 400, 27, 81);
        submit1.setOnAction(e -> {
            new Products(width, height, stage).setScene();
        });

        root.getChildren().addAll(welcomeText, userChange);
        root.getChildren().add(submit1);
        this.scene = new Scene(root, width, height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
    public Scene getScene() {
        return this.scene;
    }
}
