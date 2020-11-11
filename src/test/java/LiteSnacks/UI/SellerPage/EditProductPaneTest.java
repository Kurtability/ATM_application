package LiteSnacks.UI.SellerPage;
import static org.junit.jupiter.api.Assertions.*;

import LiteSnacks.UI.Seller.EditProductPane;
import LiteSnacks.backend.Item;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.junit.Test;
public class EditProductPaneTest {
    @Test
    public void EditProductPaneConstructTest(){
        try{
            Item item = new Item("COCA","DRINKS",1,10,2.2);
            EditProductPane pane = new EditProductPane(item);
            assertNotNull(pane.getPane());
        }catch (Exception e){
            System.out.println("EditProductPane construct failed");
        }
    }

    @Test
    public void ColourTextFieldTest(){
        try{
            TextField field = new TextField();
            EditProductPane pane = new EditProductPane(null);
            pane.NoramlTextField(field);
            pane.RedTextField(field);

        }catch (Exception e){
            System.out.println("Colour TextField failed");
        }
    }
    @Test
    public void ValidTest(){
        EditProductPane pane = new EditProductPane(null);
        assertTrue(pane.checkValid("1","helo","1"));
        //price
        assertFalse(pane.checkValid("","helo","1"));
        assertFalse(pane.checkValid("1.1","helo","1"));
        assertFalse(pane.checkValid("one","helo","1"));
        assertFalse(pane.checkValid(" ","helo","1"));
        //name
        assertFalse(pane.checkValid("1","","1"));
        //code
        assertFalse(pane.checkValid("1","helo",""));
    }
}
