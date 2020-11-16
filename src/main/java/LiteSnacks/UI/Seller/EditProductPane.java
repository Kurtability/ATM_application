package LiteSnacks.UI.Seller;

import LiteSnacks.UI.QuantityModifier;
import LiteSnacks.UI.Style;
import LiteSnacks.backend.Item;
import LiteSnacks.backend.ProductsHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import static LiteSnacks.UI.Style.setXY;

public class EditProductPane {
    Pane pane;
    Item item;
    TextField code;
    TextField price;
    TextField name;
    ChoiceBox<String> category;
    QuantityModifier qtymdfier;

    public EditProductPane(Item item) {
        this.item = item;
        this.pane = new Pane();
        this.pane.setPrefWidth(540);
        this.pane.setPrefHeight(100);

        this.pane.setStyle("-fx-background-color: #d9d9d9;");

        // category
        ProductsHandler ph = new ProductsHandler();
        category = new ChoiceBox<String>();
        category.setValue(item.getCategory()); // String.valueOf(item.getCategory()));
        category.getItems().addAll(ph.getCategories());
        setXY(category,320,40);


        // quantity modifier
        qtymdfier = new QuantityModifier(item);
        Pane qtymdfierPane = qtymdfier.getPane();
        setXY(qtymdfierPane,460,40);

        // code
        code = new TextField(Integer.toString(item.getId()));
        NoramlTextField(code);
        code.setLayoutX(30);
        code.setLayoutY(40);
        code.setPrefWidth(50);
        code.setPrefHeight(20);

        // price
        Text symbol = new Text("$");
        setXY(symbol,240,60);
        price = new TextField(String.valueOf(item.getUnitPrice()));
        NoramlTextField(price);
        price.setLayoutX(250);
        price.setLayoutY(40);
        price.setPrefWidth(50);
        price.setPrefHeight(20);

        // product name
        name = new TextField(item.getName());
        NoramlTextField(name);
        name.setLayoutX(100);
        name.setLayoutY(40);
        name.setPrefWidth(120);
        name.setPrefHeight(20);

        // image
        // ImageView image = new ImageView(item.);

        // image.setFitHeight(85);
        // image.setFitWidth(85);
        // image.setLayoutX(21);
        // image.setLayoutY(4);

        this.pane.getChildren().addAll(code, name, symbol,price, category, qtymdfierPane);
    }

    public Pane getPane() {
        return this.pane;
    }

    public boolean update() {
        if (checkValid(price.getText(), name.getText(), code.getText())) {
            item = new Item(name.getText(), category.getValue(), Integer.parseInt(code.getText()), item.getQuantity(),
                    Double.parseDouble(price.getText()));
            return true;
        } else {
            return false;
        }
        //

        // backend
    }

    public void RedTextField(TextField field) {
        field.setStyle("-fx-text-box-border: #B22222;");
    }

    public void NoramlTextField(TextField field) {
        field.setStyle("-fx-text-box-border: #ffffff;");
    }

    public boolean checkValid(String price, String name, String code) {
        boolean is_valid = true;
        if (price.length() == 0) {
            System.out.println("Price can not be null");
            is_valid = false;
            RedTextField(this.price);
        } else {
            NoramlTextField(this.price);
        }

        double doubleprice;
        try {
            doubleprice = Double.valueOf(price);
            NoramlTextField(this.price);
        } catch (Exception e) {
            is_valid = false;
            System.out.println("price should be double or integer ");
            RedTextField(this.price);
        }

        if (name.length() == 0) {
            System.out.println("Name can not be null");
            is_valid = false;
            RedTextField(this.name);
        } else {
            NoramlTextField(this.name);
        }

        if (code.length() == 0) {
            System.out.println("Code can not be null");
            is_valid = false;
            RedTextField(this.code);
        } else {
            NoramlTextField(this.code);
        }
        return is_valid;

    }

    public String getName() {
        return name.getText();
    }

    public String getCategory() {
        return category.getValue();
    }

    public double getPrice() {
        return Double.parseDouble(price.getText());
    }

    public int getCode() {
        return Integer.parseInt(code.getText());
    }

    public int getQuant() {
        return qtymdfier.getQuant();
    }
}
