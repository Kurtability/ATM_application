package LiteSnacks.UI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditProducts {
    Scene scene;
    Stage stage;
    List<Button> menu;
    ScrollPane productsPane;
    List<Pane> menuPane;
    EditProducts(double width, double height,Stage stage){

        this.stage = stage;
        Pane root = new Pane();

        // menu
        List<String> stringForbutton = new ArrayList<>();
        stringForbutton.add("Drinks");
        stringForbutton.add("Chocolates");
        stringForbutton.add("Chips");
        stringForbutton.add("Candies");

        List<Button> buttons = new ArrayList<Button>();
        menu  = buttons;
        int x = 10;
        for (int i=0; i<4 ; i++){
            Button each  = createButton(stringForbutton.get(i),x,10,27,81);
            int finalI = i;
            each.setOnAction(event -> {menuHandler(stringForbutton.get(finalI));});
            buttons.add(each);
            x=x+81;
        }
        menu.get(0).setStyle("-fx-background-color: red");



        //products pane (scroll)
        Item item = new Item(0);
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
                getProductsPane(chocolates),
                getProductsPane(candies)


        ));



        ScrollPane products = new ScrollPane();
        products.setPrefHeight(304);
        products.setPrefWidth(580);
        products.setLayoutX(10);
        products.setLayoutY(77);

        productsPane = products;
        products.setContent(menuPane.get(0));



        root.getChildren().addAll(products);
        root.getChildren().addAll(buttons);
        scene = new Scene(root,width,height);
    }
    public Button createButton(String text, int x, int y, int h, int w){
        Button button = new Button(text);
        this.setXY(button,x,y);
        this.setHW(button,h,w);
        button.setStyle( "-fx-background-color: #000000;");
        button.setTextFill(Color.WHITE);
        return button;


    }
    public void setXY(Control node, double x, double y){
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
            each.setLayoutX(140*row);
            each.setLayoutY(160*column);
            box.getChildren().add(each);
            row = row +1;
            if (row == 4){
                column = column+1;
                row = 0;
            }

        }




        return box;
        //return ;


    }
    public void menuHandler(String menu){
        int index = 0;
        if (menu.equals("Drinks")){
            index = 0;
        }else if (menu.equals("Chocolates")){
            index = 1;
        }else if(menu.equals("Chips")){
            index = 2;
        }else if(menu.equals("Candies")){
            index = 3;
        }
        for (Button b: this.menu){
            b.setStyle("-fx-background-color: black");
        }
        productsPane.setContent(menuPane.get(index));
        this.menu.get(index).setStyle("-fx-background-color: red;");
    }

    public void setScene(){stage.setScene(scene);}


}
