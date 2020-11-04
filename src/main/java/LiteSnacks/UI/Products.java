package LiteSnacks.UI;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Products {
    Scene scene;
    Stage stage;
    List<Button> menu;
    static ScrollPane productsPane;
    List<Pane> menuPane;

    Products(double width, double height,Stage stage){
        this.stage = stage;
        Pane root = new Pane();


        Pane cart = new Pane();



        //label
        Label label = new Label("Cart");
        label.setLayoutX(14);
        label.setLayoutY(14);
        label.setFont(new Font(25));

        //cancel button
        Button cancelButton = createButton("Cancel",93,14,25,70);


        //checkout button
        Button checkoutButton = createButton("Check Out",18,329,25,127);
        checkoutButton.setOnAction(event -> {new Checkout(width,height,stage).setScene();});


        //Log out button

        Button logoutButton = createButton("Log Out",18,359,25,127);
        logoutButton.setOnAction(event -> {new LoginScene(width,height,stage).setScene();});

        // total price
        Text totalPrice = new Text();
        totalPrice.setText("Total Price : "); // please fill text here
        totalPrice.setLayoutX(14);
        totalPrice.setLayoutY(319);

        cart.getChildren().addAll(label,cancelButton,checkoutButton,logoutButton,totalPrice);
        cart.setLayoutX(436);
        cart.setPrefWidth(164);
        cart.setPrefHeight(400);

        cart.setStyle("-fx-background-color: #d9d9d9;");


        // menu
        List<String> stringForbutton = new ArrayList<>();
        stringForbutton.add("Last Five");
        stringForbutton.add("Drinks");
        stringForbutton.add("Chocolates");
        stringForbutton.add("Chips");
        stringForbutton.add("Candies");

        List<Button> buttons = new ArrayList<Button>();
        menu  = buttons;
        int x = 10;
        for (int i=0; i<5 ; i++){
            Button each  = createButton(stringForbutton.get(i),x,10,27,81);
            int finalI = i;
            each.setOnAction(event -> {menuHandler(stringForbutton.get(finalI));});
            buttons.add(each);
            x=x+81;
        }
        menu.get(0).setStyle("-fx-background-color: red");



        //products pane (scroll)
        // Item item = new Item(0);
        //for test     itegrate area
        List<Item> lastfive = new ArrayList<Item>(Arrays.asList(new Item(0),new Item(1)));
        List<Item> drinks = new ArrayList<Item>(Arrays.asList(new Item(0),new Item(1),new Item(1),new Item(8),new Item(1),new Item(10),new Item(1)));
        List<Item> chocolates = new ArrayList<Item>(Arrays.asList(new Item(0),new Item(1),new Item(1),new Item(8),new Item(1)));
        List<Item> chips = new ArrayList<Item>(Arrays.asList(new Item(0),new Item(1),new Item(1),new Item(8),new Item(1),new Item(10),new Item(1)));
        List<Item> candies = new ArrayList<Item>(Arrays.asList(new Item(0),new Item(1),new Item(1),new Item(8),new Item(1)));

        menuPane = new ArrayList<Pane>(Arrays.asList(
                getProductsPane(lastfive),
                getProductsPane(drinks),
                getProductsPane(chocolates),
                getProductsPane(chips),
                getProductsPane(candies)


        ));



        ScrollPane products = new ScrollPane();
        products.setPrefHeight(304);
        products.setPrefWidth(418);
        products.setLayoutX(4);
        products.setLayoutY(77);

        productsPane = products;
        products.setContent(menuPane.get(0));



        root.getChildren().addAll(cart,products);
        root.getChildren().addAll(buttons);
        scene = new Scene(root,width,height);


        //
    }

    public void setScene() {
        stage.setScene(scene);
    }

    public Button createButton(String text, int x, int y, int h, int w){
        Button button = new Button(text);
        this.setXY(button,x,y);
        this.setHW(button,h,w);
        button.setStyle( "-fx-background-color: #000000;");
        button.setTextFill(Color.WHITE);
        return button;


    }
    public void setXY(Control node,double x,double y){
        node.setLayoutX(x);
        node.setLayoutY(y);
    }
    public void setHW(Control node,double h,double w){
        node.setPrefHeight(h);
        node.setPrefWidth(w);
    }

    public  Pane getProductsPane(List<Item> items){
        Pane box = new Pane();
        int row = 0;
        int column = 0;

        for (Item item : items){
            Pane each = new ProductPane(item).getPane();
            each.setLayoutX(135*row);
            each.setLayoutY(150*column);
            box.getChildren().add(each);
            row = row +1;
            if (row == 3){
                column = column+1;
                row = 0;
            }

        }

        return box;
        //return ;


    }

    public void menuHandler(String menu){
        int index = 0;
        if (menu.equals("Last Five")){

        }else if (menu.equals("Drinks")){
            index = 1;
        }else if (menu.equals("Chocolates")){
            index = 2;
        }else if(menu.equals("Chips")){
            index = 3;
        }else if(menu.equals("Candies")){
            index = 4;
        }
        for (Button b: this.menu){
            b.setStyle("-fx-background-color: black");
        }
        productsPane.setContent(menuPane.get(index));
        this.menu.get(index).setStyle("-fx-background-color: red;");
    }


}
