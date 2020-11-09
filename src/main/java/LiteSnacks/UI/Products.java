package LiteSnacks.UI;

import LiteSnacks.UI.ShoppingCart.Cart;
import LiteSnacks.UI.ShoppingCart.Checkout;
import LiteSnacks.backend.Item;
import LiteSnacks.backend.ProductsHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Products {
    Scene scene;
    Stage stage;
    List<Button> menu;
    static ScrollPane productsPane;
    List<Pane> menuPane;
    List<String> stringForButton;
    private Cart cart;

    public Products(double width, double height, Stage stage) {
        this.stage = stage;
        Pane root = new Pane();

        cart = new Cart();
        stringForButton = new ArrayList<>();

        // products pane (scroll)
        // Item item = new Item(0);
        // for test itegrate area
        // List<Item> lastfive = new ArrayList<Item>(Arrays.asList(new Item("generic
        // puhtaytoe chips", 4, 1.25), new Item("cheet toes", 1, 2.0)));

        ProductsHandler productsHandler = new ProductsHandler();
        Map<String, List<Item>> items = productsHandler.getAllItems();

        List<Pane> productPanes = new ArrayList<>();
        menu = new ArrayList<>();
        productsHandler.getCategories().forEach(cat -> {
            stringForButton.add(cat);
            productPanes.add(getProductsPane(items.get(cat)));
        });
        menuPane = productPanes;

        ScrollPane products = new ScrollPane();
        products.setPrefHeight(304);
        products.setPrefWidth(418);
        products.setLayoutX(4);
        products.setLayoutY(77);

        productsPane = products;
        products.setContent(menuPane.get(0));

        // menu
        List<Button> buttons = new ArrayList<Button>();
        menu = buttons;
        int x = 10;
        for (int i = 0; i < 5; i++) {
            Button each = createButton(stringForButton.get(i), x, 10, 27, 81);
            int finalI = i;
            each.setOnAction(event -> {
                menuHandler(stringForButton.get(finalI));
            });
            buttons.add(each);
            x = x + 81;
        }
        menu.get(0).setStyle("-fx-background-color: red");

        Button logoutButton = createButton("Log Out", 18, 390, 25, 100);
        logoutButton.setOnAction(event -> {
            new LoginScene(width, height, stage).setScene();
        });

        // checkout button
        Button checkoutButton = createButton("Check Out", 150, 390, 25, 100);
        checkoutButton.setOnAction(event -> {
            new Checkout(width, height, stage, cart).setScene();
        });

        root.getChildren().addAll(checkoutButton, logoutButton, products, cart.getPane());
        root.getChildren().addAll(buttons);
        scene = new Scene(root, width, height);

    }

    public void setScene() {
        stage.setScene(scene);
    }

    public Button createButton(String text, int x, int y, int h, int w) {
        Button button = new Button(text);
        this.setXY(button, x, y);
        this.setHW(button, h, w);
        button.setStyle("-fx-background-color: #000000;");
        button.setTextFill(Color.WHITE);
        return button;

    }

    public void setXY(Control node, double x, double y) {
        node.setLayoutX(x);
        node.setLayoutY(y);
    }

    public void setHW(Control node, double h, double w) {
        node.setPrefHeight(h);
        node.setPrefWidth(w);
    }

    public Pane getProductsPane(List<Item> productDisplayItems) {
        Pane box = new Pane();
        int row = 0;
        int column = 0;

        for (Item productDisplayItem : productDisplayItems) {
            Pane each = new ProductPane(productDisplayItem, cart).getPane();
            each.setLayoutX(135 * row);
            each.setLayoutY(150 * column);
            box.getChildren().add(each);
            row = row + 1;
            if (row == 3) {
                column = column + 1;
                row = 0;
            }

        }

        return box;
    }

    public void menuHandler(String menu) {
        int index = 0;
        index = stringForButton.indexOf(menu);
        for (Button b : this.menu) {
            b.setStyle("-fx-background-color: black");
        }
        productsPane.setContent(menuPane.get(index));
        this.menu.get(index).setStyle("-fx-background-color: red;");
    }

}
