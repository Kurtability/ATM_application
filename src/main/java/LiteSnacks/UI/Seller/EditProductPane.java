package LiteSnacks.UI.Seller;

import LiteSnacks.UI.Item;
import LiteSnacks.UI.QuantityModifier;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class EditProductPane {
    Pane pane;
    Item item;
    TextField code;
    TextField price;
    TextField name;
    ChoiceBox<String> category;

    public EditProductPane(Item item){
        this.item = item;
        this.pane = new Pane();
        this.pane.setPrefWidth(540);
        this.pane.setPrefHeight(100);

        this.pane.setStyle( "-fx-background-color: #d9d9d9;");




        //category
        category = new ChoiceBox<String>();
        category.setValue(String.valueOf(item.category));
        category.getItems().addAll("DRINKS","CHOCOLATES","CHIPS","CANDIES");
        category.setLayoutX(320);
        category.setLayoutY(40);

        //quantity modifier
        Pane qtymdfier = new QuantityModifier(item).getPane();
        qtymdfier.setLayoutX(460);
        qtymdfier.setLayoutY(40);



        //code
        code = new TextField(item.code);
        NoramlTextField(code);
        code.setLayoutX(120);
        code.setLayoutY(40);
        code.setPrefWidth(50);
        code.setPrefHeight(20);


        //price
        Text symbol = new Text("$");
        symbol.setLayoutX(240);
        symbol.setLayoutY(60);
        price = new TextField(String.valueOf(item.price));
        NoramlTextField(price);
        price.setLayoutX(250);
        price.setLayoutY(40);
        price.setPrefWidth(50);
        price.setPrefHeight(20);

        //product name
        name = new TextField(item.product_name);
        NoramlTextField(name);
        name.setLayoutX(180);
        name.setLayoutY(40);
        name.setPrefWidth(50);
        name.setPrefHeight(20);

        //image
        ImageView image = new ImageView(item.path);

        image.setFitHeight(85);
        image.setFitWidth(85);
        image.setLayoutX(21);
        image.setLayoutY(4);

        this.pane.getChildren().addAll(qtymdfier,symbol,category,code,price,name,image);
    }
    public Pane getPane(){
        return this.pane;
    }

    public boolean update(){
        if(checkValid(price.getText(),name.getText(),code.getText())){
            item.update(Double.valueOf(price.getText()),name.getText(),code.getText(),category.getValue());
            return true;
        }else{
            return false;
        }
        //

        // backend
    }
    public void RedTextField(TextField field){
        field.setStyle("-fx-text-box-border: #B22222;");
    }
    public void NoramlTextField(TextField field){
        field.setStyle("-fx-text-box-border: #ffffff;");
    }
    public boolean checkValid(String price, String name,String code){
        boolean is_valid = true;
        if (price.length() == 0){
            System.out.println("Price can not be null");
            is_valid = false;

            RedTextField(this.price);
        }else{
            NoramlTextField(this.price);
        }


        double doubleprice;
        try{
            doubleprice = Double.valueOf(price);
            NoramlTextField(this.price);
        }catch (Exception e){
            is_valid = false;
            System.out.println("price should be double or integer ");
            RedTextField(this.price);
        }

        if (name.length() == 0){
            System.out.println("Name can not be null");
            is_valid = false;
            RedTextField(this.name);
        }else {
            NoramlTextField(this.name);
        }

        if (code.length() == 0){
            System.out.println("Code can not be null");
            is_valid = false;
            RedTextField(this.code);
        }else{
            NoramlTextField(this.code);
        }
        return is_valid;

    }
}
