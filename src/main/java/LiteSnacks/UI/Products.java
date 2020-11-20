package LiteSnacks.UI;

import LiteSnacks.UI.ShoppingCart.Cart;
import LiteSnacks.UI.ShoppingCart.Checkout;
import LiteSnacks.backend.*;
import LiteSnacks.UI.Style;
import LiteSnacks.backend.UserAccount.UserAccount;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static LiteSnacks.UI.Style.createButton;
import static LiteSnacks.UI.Style.setXY;

public class Products {
    Scene scene;
    Stage stage;
    List<Button> menu;
    static ScrollPane productsPane;
    List<Pane> menuPane;
    List<String> stringForButton;
    private Cart cart;
    Text time = new Text();
    Timer timer;
    double width;
    double height;

    public Products(double width, double height, Stage stage) {
        this.stage = stage;
        this.width = width;
        this.height = height;
        Pane root = new Pane();

        // timer
        setXY(time, 10, 70);
        timer = new Timer(time, width, height, stage, this);
        timer.restart();

        cart = new Cart(timer);
        stringForButton = new ArrayList<>();

        timer.setCart(cart);

        ProductsHandler productsHandler = new ProductsHandler();
        Map<String, List<Item>> items = productsHandler.getAllItems();

        List<Pane> productPanes = new ArrayList<>();
        menu = new ArrayList<>();
        productsHandler.getCategories().forEach(cat -> {
            stringForButton.add(cat);
            if(cat.equals("Last Five")) {
                String username;
                UserAccount user = UserLoginHandler.getCurrentUser();
                if(user == null) {
                    username = "anon";
                }
                else {
                    username = user.getUserName();
                }
                List<String> lastFive = LastFive.getLastFive(username);
                items.put("Last Five", LastFive.convertNamesToItems(lastFive));
            }
            productPanes.add(getProductsPane(items.get(cat), timer));
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

        Text logout = new Text("Login");
        if (UserLoginHandler.getCurrentUser() != null) {
            logout.setText("Log Out");
        }
        Button logoutButton = createButton(logout.getText(), 18, 390, 25, 100);
        logoutButton.setOnAction(event -> {
            System.out.println(UserLoginHandler.getCurrentUser());
            UserLoginHandler.setCurrentUser(null);
            new LoginScene(width, height, stage).setScene();
            timer.stop();
        });

        // checkout button
        Button checkoutButton = createButton("Check Out", 150, 390, 25, 100);
        checkoutButton.setOnAction(event -> {
            timer.stop();
            if (!cart.getItems().isEmpty()) {
                new Checkout(width, height, stage, cart).setScene();
            }
        });

        Button cancel = new Button("Cancel");
        cancel.setLayoutX(290);
        cancel.setLayoutY(390);
        cancel.setMinSize(100, 25);
        cancel.setStyle("-fx-background-color: red");
        cancel.setTextFill(Color.WHITE);
        cancel.setOnAction(event -> {
            timer.stop();
            Transaction.cancelTransaction("User cancelled");
            new Products(width, height, stage).setScene();
        });

        root.getChildren().addAll(checkoutButton, logoutButton, products, cart.getPane(), cancel, time);
        root.getChildren().addAll(buttons);
        scene = new Scene(root, width, height);

    }

    public void setScene() {
        stage.setScene(scene);
    }

    public Pane getProductsPane(List<Item> productDisplayItems, Timer timer) {
        Pane box = new Pane();
        int row = 0;
        int column = 0;

        for (Item productDisplayItem : productDisplayItems) {
            Pane each = new ProductPane(productDisplayItem, cart, timer).getPane();
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

    public void renew() {
        new Products(width, height, stage).setScene();
    }

}
