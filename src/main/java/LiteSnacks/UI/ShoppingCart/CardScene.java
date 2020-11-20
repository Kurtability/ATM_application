package LiteSnacks.UI.ShoppingCart;

import LiteSnacks.backend.ResourceHandler;
import LiteSnacks.backend.UserAccount.UserAccount;
import LiteSnacks.backend.UserLoginHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.geometry.*;

import LiteSnacks.backend.CreditCardHandler;
import LiteSnacks.backend.Transaction;

import java.io.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import LiteSnacks.UI.Products;

public class CardScene {
    Scene scene;
    Stage stage;
    Checkout checkoutRef;
    ResourceHandler resourceHandler;
    Scanner sc;
    Scanner sc2;

    public CardScene(double width, double height, Stage stage, Cart cart) {
        this.stage = stage;
        Pane root = new Pane();

        Label price = new Label("Total Price: $" + Double.toString(cart.getTotal()));
        price.setLayoutX(200);
        price.setLayoutY(290);
        price.setFont(new Font("Arial", 18));

        Label title = new Label("Payment Method");
        title.setLayoutX(75);
        title.setLayoutY(30);
        title.setFont(new Font("Arial", 30));

        Label title2 = new Label("Credit Card");
        title2.setLayoutX(75);
        title2.setLayoutY(90);
        title2.setFont(new Font("Arial", 20));

        Label cardLabel = new Label("Enter Card Number:");
        cardLabel.setLayoutX(100);
        cardLabel.setLayoutY(227);
        cardLabel.setFont(new Font("Arial", 15));

        Label givenName = new Label("Enter Full Name:");
        givenName.setLayoutX(125);
        givenName.setLayoutY(177);
        givenName.setFont(new Font("Arial", 15));

        Text fail = new Text("");
        fail.setLayoutX(75);
        fail.setLayoutY(150);
        fail.setFont(new Font("Arial", 20));
        fail.setFill(Color.rgb(255, 0, 0));

        Text yay = new Text("");
        yay.setLayoutX(75);
        yay.setLayoutY(150);
        yay.setFont(new Font("Arial", 20));
        yay.setFill(Color.rgb(0, 180, 0));

        TextField name = new TextField();
        name.setPromptText("Please Enter Full Name");
        name.setLayoutX(275);
        name.setLayoutY(175);

        PasswordField number = new PasswordField();
        number.setPromptText("Enter Card Number");
        number.setLayoutX(275);
        number.setLayoutY(225);

        // back
        Button back = new Button("Back");
        back.setStyle("-fx-background-color: #DC143C");
        back.setTextFill(Color.WHITE);
        back.setOnAction(e -> {
            new Checkout(width, height, stage, cart).setScene();
        });
        back.setLayoutX(500);
        back.setLayoutY(430);

        Label message = new Label("");
        message.setLayoutX(325);
        message.setLayoutY(350);

        Button pay = new Button("Pay Now And Save Credit Card");
        pay.setLayoutX(240);
        pay.setLayoutY(335);
        pay.setTextFill(Color.WHITE);
        pay.setStyle("-fx-background-color: #000000");
        pay.setOnAction(event -> {
            String result = CreditCardHandler.checkCard(name.getText(), number.getText());
            if (!result.equals("Credit Card details are not valid")) {
                Map<String, List<Double>> purchasedProducts = new HashMap<>();
                Map<String, CartItem> cartstuff = cart.getItems();
                for (String item : cartstuff.keySet()) {
                    List<Double> temp = new ArrayList<>();
                    temp.add(cartstuff.get(item).getUnitPrice());
                    temp.add((double) cartstuff.get(item).getQuantity());
                    purchasedProducts.put(item, temp);
                }

                if (checkoutRef.user != null) {
                    try {
                        File file = ResourceHandler.getUserFile();
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line = "", oldtext = "";
                        while ((line = reader.readLine()) != null) {
                            oldtext += line + System.lineSeparator();
                        }
                        reader.close();
                        // replace a word in a file
                        // String newtext = oldtext.replaceAll("drink", "Love");

                        // To replace a line in a file
                        String currentUserName = checkoutRef.user.getUserName();
                        String currentUserPassword = checkoutRef.user.getPassword();
                        String currentUserRole = checkoutRef.user.getRole();

                        String n = name.getText();
                        String num = number.getText();
                        // System.out.println(currentUserName);

                        boolean flag = false;
                        for (String i: oldtext.split("\n")) {
                            if (i.split(",")[0].equals(currentUserName) && i.split(",").length >= 5) {
                                flag = true;
                                yay.setText("we have your card");
                                fail.setText("");
                                return;
                            }
                        }

                        String newtext = oldtext.replaceAll(
                                currentUserName + ", " + currentUserPassword + ", " + currentUserRole, currentUserName
                                        + ", " + currentUserPassword + ", " + currentUserRole + ", " + n + ", " + num);
                        newtext = newtext.strip();
                        FileWriter writer = new FileWriter(file);
                        writer.write(newtext);
                        writer.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }

                Transaction.addTransaction(purchasedProducts, cart.getTotal(), "0", true);
                new CardSceneSuccess(width, height, stage, cart).setScene();
            } else {
                fail.setText(result);
                yay.setText("");
            }
        });

        Text fail2 = new Text("");
        fail2.setLayoutX(75);
        fail2.setLayoutY(150);
        fail2.setFont(new Font("Arial", 20));
        fail2.setFill(Color.rgb(255, 0, 0));


        // try {
        // sc2 = new Scanner(resourceHandler.getUserFile());
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // }
        //
        // while (sc2.hasNextLine()) {
        // String line = sc2.nextLine();
        // String[] details = line.split(",");
        // int size = details.length;
        // if ( details[0].equals(checkoutRef.user.getUserName())){
        // if (size ==5){
        // yay.setText("we have your card");
        // sc.close();
        // break;
        // }
        // }
        //
        // sc2.close();
        // }

        Button saved = new Button("Pay Now With Saved Credit Card");
        saved.setLayoutX(240);
        saved.setLayoutY(365);
        saved.setTextFill(Color.WHITE);
        saved.setStyle("-fx-background-color: #000000");
        saved.setOnAction(event -> {
            try {
                sc = new Scanner(ResourceHandler.getUserFile());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] details = line.split(",");
                int size = details.length;
                if (details[0].equals(checkoutRef.user.getUserName())) {
                    if (size >= 5) {
                        Map<String, List<Double>> purchasedProducts = new HashMap<>();
                        Map<String, CartItem> cartstuff = cart.getItems();
                        for (String item : cartstuff.keySet()) {
                            List<Double> temp = new ArrayList<>();
                            temp.add(cartstuff.get(item).getUnitPrice());
                            temp.add((double) cartstuff.get(item).getQuantity());
                            purchasedProducts.put(item, temp);
                        }
                        Transaction.addTransaction(purchasedProducts, cart.getTotal(), "NA", true);
                        System.out.println("yay");
                        new CardSceneSuccess(width, height, stage, cart).setScene();
                        break;
                    }
                } else {
                    System.out.println("somethins wrong");
                    fail2.setText("Ops You Have No Card, Cheeky Cheeky");
                }
            }
            sc.close();

        });

        Button cancel = new Button("Cancel Transaction");
        cancel.setLayoutX(25);
        cancel.setLayoutY(430);
        cancel.setStyle("-fx-background-color: #DC143C");
        cancel.setTextFill(Color.WHITE);
        cancel.setOnAction(event -> {
            Transaction.cancelTransaction("User cancelled");
            new Products(width, height, stage).setScene();
        });

        root.getChildren().addAll(name, givenName, cardLabel, cancel, title2, pay, price, title, number, fail,
                fail2, yay, back);
        
        if (UserLoginHandler.getCurrentUser() != null) {
            root.getChildren().add(saved);
        } else {
            pay.setText("Pay");
        }

        scene = new Scene(root, width, height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
