package LiteSnacks.UI.ShoppingCart;

import LiteSnacks.backend.Cash;
import LiteSnacks.backend.CashHandler;
import LiteSnacks.backend.PayCash;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static LiteSnacks.UI.Style.*;

public class CashPayment {
    Scene scene;
    Stage stage;
    List<CashPane> CashPanes;
    List<Cash> cashes;
    Text input;
    Text response;

    double totalPrice;

    public CashPayment(double width, double height, Stage stage,Cart cart) {
        Pane root = new Pane();
        this.stage = stage;
        this.totalPrice = cart.getTotal();


        /***
         * retrieve cash from file
         ***/
        CashHandler ch = new CashHandler();
        cashes = ch.getcashes();
        for (Cash each : cashes){
            each.setQty(0);
        }
        this.CashPanes = getCashPanes(cashes);
        Pane cashWholePane = getPaneForCashes(this.CashPanes);

        // Pane for cashes
        Pane CashPane = new Pane();
        setXY(CashPane,10,45);
        setHW(CashPane,304,580);

        // label
        Text label = new Text("Please Input Cash");
        setXY(label,10,20);
        label.setFont(new Font(20));
        label.setFill(Color.rgb(0, 66, 127));

        response = new Text("");
        setXY(response,10,425);
        response.setFont(new Font(20));
        response.setFill(Color.rgb(200, 0, 0));

        //total cost
        Text price = new Text("Total Cost: $" + totalPrice);
        setXY(price,10,60);
        price.setFont(new Font(20));
        price.setFill(Color.rgb(0, 66, 127));

        //input
        this.input = new Text("Total Input: ");
        setXY(input,10,100);
        input.setFont(new Font(20));
        input.setFill(Color.rgb(0, 66, 127));

        // SUBMIT BUTTON
        Button submit1 = createButton("Pay Now", 500, 400, 27, 81);
        submit1.setOnAction(e -> {
            boolean pass = false;
            String text = PayCash.submitPayment(cashes,cart.getTotal());
            response.setText(text);
            if(!text.equals("Insufficient Change. Please try another payment method")) {
                new CashPaySuccess(width, height, stage, text).setScene();
            }

        });

        // back
        Button back = new Button(">back");
        back.setStyle("-fx-background-color: transparent;");
        back.setFont(new Font(20));
        back.setOnAction(e -> {
            new Checkout(width,height,stage,cart).setScene();
        });
        setXY(back,500,1);

        root.getChildren().addAll(CashPane, cashWholePane, label, back,input,price, response);
        root.getChildren().add(submit1);
        this.scene = new Scene(root, width, height);
    }



  

    public List<CashPane> getCashPanes(List<Cash> cashes) {
        List<CashPane> panes = new ArrayList<CashPane>();
        for (Cash each : cashes) {
            CashPane pane = new CashPane(each,this);
            panes.add(pane);
        }
        return panes;
    }

    public Pane getPaneForCashes(List<CashPane> panes) {
        Pane box = new Pane();
        int row = 0;
        int column = 1;

        for (CashPane cashPane : panes) {
            Pane each = cashPane.getPane();
            each.setLayoutX(120 * row);
            each.setLayoutY(130 * column);
            box.getChildren().add(each);
            row = row + 1;
            if (row == 5) {
                column = column + 1;
                row = 0;
            }

        }
        return box;
    }

    public void setScene() {
        stage.setScene(scene);
    }
    public Scene getScene(){
        return this.scene;
    }

    public void updateInput(){
        double total = 0;
        for (Cash cash : this.cashes){
            total = Math.round((total + (Math.round(cash.getValue()*100.0)/100.0)*cash.getQty())*100.0)/100.0;
        }
        this.input.setText("Total Input: $" + total);
    }
}
