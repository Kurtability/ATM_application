package LiteSnacks.UI.Cashier;

import LiteSnacks.UI.Owner.OwnerEditScene;
import LiteSnacks.UI.Seller.SellerMainScene;
import LiteSnacks.UI.Style;
import LiteSnacks.backend.Cash;
import LiteSnacks.backend.CashHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static LiteSnacks.UI.Style.*;

public class EditCashes {
    Scene scene;
    Stage stage;
    List<EditCashPane> editCashPanes;
    List<Cash> cashes;

    public EditCashes(double width, double height, Stage stage,boolean isOwner) {
        Pane root = new Pane();
        this.stage = stage;

        /***
         * retrieve cash from file
         ***/
        CashHandler ch = new CashHandler();
        cashes = ch.getcashes();
        this.editCashPanes = getEditCashPanes(cashes);
        Pane cashWholePane = getPaneForCashes(this.editCashPanes);

        // Pane for cashes
        setXY(cashWholePane,10,45);
        setHW(cashWholePane,304,580);


        // label
        Text label = new Text("Edit Cashes");
        setXY(label,10,20);
        label.setFont(new Font(20));
        label.setFill(Color.rgb(0, 66, 127));

        //all done
        Text alldone = new Text("All done!");
        alldone.setVisible(false);
        alldone.setFill(Color.rgb(230,0,0));
        setXY(alldone,510,350);


        // SUBMIT BUTTON
        Button submit1 = createButton("submit", 500, 360, 27, 81);
        submit1.setOnAction(e -> {
           CashHandler.Submit(this.cashes);
           alldone.setVisible(true);
        });

        // back
        Button back = new Button(">back");
        back.setStyle("-fx-background-color: transparent;");
        back.setFont(new Font(20));
        back.setOnAction(e -> {
            if(isOwner){
                new OwnerEditScene(width,height,stage).setScene();
            }else{
                new CashierMainScene(width, height, stage).setScene();
            }

        });
        back.setLayoutX(500);
        back.setLayoutY(1);

        root.getChildren().addAll( cashWholePane, label, back,alldone);
        root.getChildren().add(submit1);
        this.scene = new Scene(root, width, height);
    }



    public void submit() {
        System.out.println(1);
        CashHandler ch = new CashHandler();
        ch.Submit(this.cashes);
    }

    public List<EditCashPane> getEditCashPanes(List<Cash> cashes) {
        List<EditCashPane> panes = new ArrayList<EditCashPane>();
        for (Cash each : cashes) {
            EditCashPane pane = new EditCashPane(each);
            panes.add(pane);
        }
        return panes;
    }

    public Pane getPaneForCashes(List<EditCashPane> panes) {
        Pane box = new Pane();
        int row = 0;
        int column = 0;

        for (EditCashPane editProductPane : panes) {
            Pane each = editProductPane.getPane();
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
}
