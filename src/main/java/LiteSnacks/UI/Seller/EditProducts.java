package LiteSnacks.UI.Seller;

import LiteSnacks.UI.Owner.OwnerEditScene;
import LiteSnacks.UI.Style;
import LiteSnacks.backend.Item;
import LiteSnacks.backend.ProductsHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static LiteSnacks.UI.Style.*;

public class EditProducts {
    Scene scene;
    Stage stage;
    List<EditProductPane> editProductPanes;
    Text invalid = new Text("Invalid");
    Text valid = new Text("Successfully submitted");

    public EditProducts(double width, double height, Stage stage,boolean isOwner) {

        this.stage = stage;
        Pane root = new Pane();

        /**
         * retrieve a list of 'Item'(whatever) from file
         *
         *
         *
         **/
        ProductsHandler ph = new ProductsHandler();
        List<Item> items = ph.listOfItems();

        this.editProductPanes = getEditsProductsPanes(items);
        Pane ProductWholePane = getPaneForProducts(this.editProductPanes);

        // scrollPane for showing products block
        ScrollPane productsPane = new ScrollPane();
        setXY(productsPane,10,55);
        setHW(productsPane,280,580);
        productsPane.setContent(ProductWholePane);

        // Text for product(code,name,qty,category)
        Text label = new Text("Edit Products");
        setXY(label, 10, 20);
        label.setFont(new Font(20));
        Text comments = new Text("Please make sure each row should not be null ,\n"  +
                                "and also price should be integer or double.");
        comments.setFill(Color.rgb(160, 0, 0));

        setXY(comments, 3, 370);
        label.setFill(Color.rgb(0, 66, 127));
        Text codetext = new Text("Code");
        setXY(codetext, 60, 50);
        Text nametext = new Text("Name");
        setXY(nametext, 130, 50);
        Text pricetext = new Text("Price");
        setXY(pricetext, 280, 50);
        Text categorytext = new Text("Category");
        setXY(categorytext, 380, 50);
        Text qtytext = new Text("Qty");
        setXY(qtytext, 510, 50);

        // back
        Button back = new Button(">back");
        back.setStyle("-fx-background-color: transparent;");
        back.setFont(new Font(20));
        back.setOnAction(e -> {
            if(isOwner){
                new OwnerEditScene(width,height,stage).setScene();
            }else{
                new SellerMainScene(width, height, stage).setScene();
            }

        });
        back.setLayoutX(500);
        back.setLayoutY(2);

        // SUBMIT BUTTON
        Button submit = createButton("submit", 500, 370, 27, 81);
        submit.setOnAction(event -> {
            if (submit()){
                List<Item> newItems = Item.newItems(editProductPanes);
                if(ph.checkValid(newItems).equals("Success")) {
                    ProductsHandler.writeToFile(newItems);
                }
                else {
                    comments.setText(ph.checkValid(newItems));
                }
            }

        });

        // invalid text to indicate invalid save

        setXY(invalid, 520, 360);
        setXY(valid, 470, 360);

        invalid.setFill(Color.rgb(160, 0, 0));
        valid.setFill(Color.rgb(160, 0, 0));
        invalid.setVisible(false);
        valid.setVisible(false);

        root.getChildren().addAll(productsPane, invalid, valid, codetext, nametext, pricetext, categorytext, qtytext,
                label, comments);
        root.getChildren().addAll(submit, back);
        scene = new Scene(root, width, height);
    }

    public List<EditProductPane> getEditsProductsPanes(List<Item> items) {
        List<EditProductPane> panes = new ArrayList<>();
        for (Item each : items) {
            EditProductPane pane = new EditProductPane(each);
            panes.add(pane);
        }
        return panes;
    }

    public Pane getPaneForProducts(List<EditProductPane> panes) {
        Pane box = new Pane();

        int column = 0;
        int y = 0;

        for (EditProductPane editProductPane : panes) {
            Pane each = editProductPane.getPane();
            each.setLayoutX(15);
            y = (110 * column);
            each.setLayoutY(y);
            box.getChildren().add(each);
            column += 1;

        }
        return box;
    }
    public boolean submit() {
        boolean is_valid = true;
        for (EditProductPane each : this.editProductPanes) {
            if (each.update() == false) {
                System.out.println(1);
                is_valid = false;
            }
        }

        if (is_valid == false) {
            invalid.setVisible(true);
        } else {
            invalid.setVisible(false);
            valid.setVisible(true);
            System.out.println("suc");
            // handler to save all item into files
        }
        return is_valid;
    }

    public void setScene() {
        stage.setScene(scene);
    }

}
