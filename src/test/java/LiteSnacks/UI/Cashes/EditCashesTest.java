package LiteSnacks.UI.Cashes;
import LiteSnacks.App;
import LiteSnacks.Main;
import LiteSnacks.UI.Cash;
import LiteSnacks.UI.Cashier.EditCashPane;
import LiteSnacks.UI.Cashier.EditCashes;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EditCashesTest {
    @Test
    public void TestEditCashesPage(){

        try {

            EditCashes editCashes = new EditCashes( 640, 480.0,new Stage());
            assertNotNull(editCashes.getScene());

        }catch (Exception e){
            System.out.println("EditCash construct failed");
        }

    }
    @Test
    public void setEditCashesPage(){

        try {
            Stage stage = new Stage();
            EditCashes editCashes = new EditCashes( 640, 480.0,stage);
            assertNotNull(editCashes.getScene());
            editCashes.setScene();

        }catch (Exception e){
            System.out.println("setScene failed");
        }

    }
    @Test
    public void createButtonTest(){
        try{
            EditCashes editCashes = new EditCashes( 640, 480.0,new Stage());
            Button button = editCashes.createButton("button",1,2,3,4);

        }catch (Exception e){
            System.out.println("create buuton failed");
        }
    }
    @Test
    public void buttonPropertyTest(){
        EditCashes editCashes = new EditCashes( 640, 480.0,new Stage());
        Button button = editCashes.createButton("button",1,2,3,4);
        assertEquals("button",button.getText());
        assertEquals(1,button.getLayoutX());
        assertEquals(2,button.getLayoutY());
        assertEquals(3,button.getPrefHeight());
        assertEquals(4,button.getPrefWidth());
    }
    @Test
    public void getEditCashPanesTest(){
        EditCashes editCashes = new EditCashes( 640, 480.0,new Stage());
        List<Cash> cashes = new ArrayList<>();
        for (int i=0; i<5;i++){
            Cash cash = new Cash(i+1,10,"no");
            cashes.add(cash);
        }
        List<EditCashPane> cashPanes = editCashes.getEditCashPanes(cashes);
        assertEquals(5,cashPanes.size());
        for (int i=0; i<cashPanes.size();i++){
            assertNotNull(cashPanes.get(i));
            assertNotNull(cashPanes.get(i).getPane());
            assertNotNull(cashPanes.get(i).getCash());
        }
    }
    @Test
    public void getPaneForCashesTest(){
        EditCashes editCashes = new EditCashes( 640, 480.0,new Stage());
        List<Cash> cashes = new ArrayList<>();
        for (int i=0; i<5;i++){
            Cash cash = new Cash(i+1,10,"no");
            cashes.add(cash);
        }
        List<EditCashPane> cashPanes = editCashes.getEditCashPanes(cashes);
        Pane pane = editCashes.getPaneForCashes(cashPanes);
        assertNotNull(pane);
        assertEquals(5,pane.getChildren());
    }
    @Test
    public void submitTest(){
        try {
            EditCashes editCashes = new EditCashes( 640, 480.0,new Stage());
            editCashes.submit();
        }catch (Exception e){
            System.out.println("can not submitt");
        }


    }

}
