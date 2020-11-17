package LiteSnacks.UI.Owner;

import LiteSnacks.UI.Cashier.CashesReport;
import LiteSnacks.UI.Cashier.CashierMainScene;
import LiteSnacks.UI.Cashier.EditCashes;
import LiteSnacks.UI.Seller.EditProducts;
import LiteSnacks.UI.Seller.SellerMainScene;
import LiteSnacks.UI.Style;
import LiteSnacks.backend.Cash;
import LiteSnacks.backend.CashHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static LiteSnacks.UI.Style.setHW;
import static LiteSnacks.UI.Style.setXY;

public class OwnerEditScene {
    Scene scene;
    Stage stage;

    public OwnerEditScene(double width, double height, Stage stage){
        this.stage = stage;

        Pane root = new Pane();
        Pane pane = new Pane();

        //lable
        Label label = new Label("What will you edit");
        label.setLayoutX(21);
        label.setLayoutY(30);
        label.setFont(new Font("Arial", 30));

        //back
        Button back = new Button(">back");
        back.setStyle("-fx-background-color: transparent;");
        back.setFont(new Font(20));
        back.setOnAction(e -> {new OwnerMainScene(width,height,stage).setScene();});
        setXY(back,500,10);


        //edit cashes button
        Button cashes = new Button("Edit Cashes");
        setXY(cashes,70,150);
        setHW(cashes,40,150);
        cashes.setStyle("-fx-background-color: #000000");
        cashes.setTextFill(Color.WHITE);
        cashes.setOnAction(event -> {
                new EditCashes(width,height,stage,true).setScene();
        });


        //edit products
        Button products = new Button("Edit Products");
        setXY(products,70,100);
        setHW(products,40,150);
        products.setStyle("-fx-background-color: #000000");
        products.setTextFill(Color.WHITE);
        products.setOnAction(event -> {
            new EditProducts(width,height,stage,true).setScene();
        });

        //edit users
        Button user = new Button("Edit Owner");
        setXY(user,70,200);
        setHW(user,40,150);
        user.setStyle("-fx-background-color: #000000");
        user.setTextFill(Color.WHITE);
        user.setOnAction(event -> {

        });

        pane.getChildren().addAll(label,products,cashes,user);
        setXY(pane,156,51);
        setHW(pane,299,289);
        pane.setStyle("-fx-background-color: #d9d9d9");

        // back


        root.getChildren().addAll(pane,back);

        scene = new Scene(root,width,height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}