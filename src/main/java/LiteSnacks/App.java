/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package LiteSnacks;

import LiteSnacks.UI.Cashier.CashierMainScene;
import LiteSnacks.UI.Products;
import LiteSnacks.backend.UserLoginHandler;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    Products productScene;
    // CardScene cs;

    UserLoginHandler handler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Vending machine");

        // ********
        // LoginScene loginScene = new LoginScene(1200,600,primaryStage);

        this.productScene = new Products(640, 480, primaryStage);
        CashierMainScene scene = new CashierMainScene(640, 400, primaryStage);
        // new OwnerMainScene(640,400,primaryStage)
        productScene.setScene();

        // this.cs = new CardScene(640, 480, primaryStage, new Cart());
        // cs.setScene();
        primaryStage.show();

        handler = new UserLoginHandler();


        // System.out.println(handler.getUsers());
        // System.out.println(handler.checkUser("Adam","1234231".hashCode()));

        //handler.addUser("KP","1234","KingPin");
        //handler.addUser("KpppP","1234","KingPin");
        // handler.addUser("KPP","678","King");
        // System.out.println(handler.getUsers());
        // System.out.println(handler.getUsers().get(2));
        System.out.println(handler.getUserFile());

    }

    public static void main(String[] args) {
        launch(args);

    }
}