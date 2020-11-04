package LiteSnacks.UI;

import LiteSnacks.backend.Item;
import LiteSnacks.backend.ProductsHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Products {
    public static Label purchasedUnitsValueLabel;
    public static Slider quantitySlider;
    public static Label unitValueLabel;
    public static Label unitPriceValueLabel;
    Scene scene;
    Stage stage;
    List<Button> menu;
    static ScrollPane productsPane;
    List<Pane> menuPane;
    public static ComboBox<Item> itemsComboBox;


    public Products(double width, double height, Stage stage){
        this.stage = stage;
        Pane root = new Pane();


// cart view
        itemsComboBox = new ComboBox<>();
        unitValueLabel = new Label();
        unitPriceValueLabel = new Label();

        quantitySlider = new Slider(0,10,0);
        purchasedUnitsValueLabel = new Label("0");
        //Label shoppingCart = new Label("Shopping Cart");
//        shoppingCart.setLayoutX(14);
//        shoppingCart.setLayoutY(14);
//        shoppingCart.setFont(new Font(25));

        BorderPane cart = new BorderPane();
        GridPane topGrid = new GridPane();
        cart.setTop(topGrid);

        //setup topGrid
        topGrid.setVgap(10);
        topGrid.setHgap(10);

        //create fixed labels
        Label unitLabel = new Label("unit");
        Label pricePerUnitLabel = new Label(("Price/unit"));

        //add all controls to topGrid
        topGrid.add(unitLabel,0,1);
        topGrid.add(pricePerUnitLabel,0,2);
        topGrid.add(itemsComboBox,0,0,2,1);
        topGrid.add(unitValueLabel,1,1);
        topGrid.add(unitPriceValueLabel,1,2);

        //set all column's width
        for (int i =0; i<5; i++){
            topGrid.getColumnConstraints().add(new ColumnConstraints(120));
        }

        itemsComboBox.setPromptText("Select item");
        unitValueLabel.setTextFill(Color.TEAL);
        unitPriceValueLabel.setTextFill(Color.TEAL);
        unitLabel.setFont(Font.font(15));
        pricePerUnitLabel.setFont(Font.font(15));
        unitValueLabel.setFont(Font.font(15));
        unitPriceValueLabel.setFont(Font.font(15));

        quantitySlider.setMinorTickCount(1);
        quantitySlider.setMajorTickUnit(2);
        quantitySlider.setPrefWidth(300);
        quantitySlider.setSnapToTicks(true);
        quantitySlider.setShowTickLabels(true);
        quantitySlider.setShowTickMarks(true);

        Label qtySliderLabel = new Label("Select units");
        Label purchasedUnitsLabel = new Label("Purchased units");

        topGrid.add(qtySliderLabel,2,0);
        topGrid.add(quantitySlider,3,0,2,1);
        topGrid.add(purchasedUnitsLabel,2,1);
        topGrid.add(purchasedUnitsValueLabel,3,1);
//        topGrid.add(shoppingCart,1,18);

        qtySliderLabel.setFont(Font.font(15));
        purchasedUnitsLabel.setFont(Font.font(15));
        purchasedUnitsValueLabel.setFont(Font.font(15));
        purchasedUnitsValueLabel.setTextFill(Color.TEAL);

        topGrid.setPrefSize(700,125);
        cart.setPrefSize(700,150);
        BorderPane.setMargin(topGrid,new Insets(10,10,10,10));



//        //label
//        Label label = new Label("Cart");
//        label.setLayoutX(14);
//        label.setLayoutY(14);
//        label.setFont(new Font(25));


//        // total price
//        Text totalPrice = new Text();
//        totalPrice.setText("Total Price : "); // please fill text here
//        totalPrice.setLayoutX(14);
//        totalPrice.setLayoutY(319);

//        HBox hbox = new HBox();
//        hbox.getChildren().addAll(cancelButton,checkoutButton,logoutButton);
//
//        cart.setBottom(hbox);
        //cart.setBottom(logoutButton);
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
        List<Item> lastfive = new ArrayList<Item>(Arrays.asList(new Item("generic puhtaytoe chips", 4, 1.25), new Item("cheet toes", 1, 2.0)));
        
        Map<String, List<Item>> items = new ProductsHandler().getAllItems();

        List<Pane> productPanes = new ArrayList<>();
        productPanes.add(getProductsPane(lastfive));
        items.keySet().forEach(key -> { productPanes.add(getProductsPane(items.get(key))); });
        menuPane = productPanes;


        ScrollPane products = new ScrollPane();
        products.setPrefHeight(304);
        products.setPrefWidth(418);
        products.setLayoutX(4);
        products.setLayoutY(77);

        productsPane = products;
        products.setContent(menuPane.get(0));


        //cancel button
        Button cancelButton = createButton("Cancel Purchase",600,420,25,120);
        //checkout button
        Button checkoutButton = createButton("Check Out",500,420,25,80);
        //Log out button
        Button logoutButton = createButton("Log Out",18,390,25,127);
        logoutButton.setOnAction(event -> {new LoginScene(width,height,stage).setScene();});


        root.getChildren().addAll(cancelButton,checkoutButton,logoutButton);
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

    public Pane getProductsPane(List<Item> productDisplayItems){
        Pane box = new Pane();
        int row = 0;
        int column = 0;

        for (Item productDisplayItem : productDisplayItems){
            Pane each = new ProductPane(productDisplayItem).getPane();
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
