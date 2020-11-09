package LiteSnacks.UI.Cashierpage;

import LiteSnacks.backend.Cash;
import LiteSnacks.UI.Cashier.EditCashPane;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class EditCashPaneTest {
    @Test
    public void EditCashPaneObjectTest(){
        try {
            Cash cash = new Cash(10,10,"notpictures");
            EditCashPane pane = new EditCashPane(cash);
        }catch (Exception e){
            System.out.println("EditCashpane construct failed");
        }

    }
    @Test
    public void getPaneTest(){
        Cash cash = new Cash(10,10,"notpictures");
        EditCashPane pane = new EditCashPane(cash);
        assertNotNull(pane.getPane());
        assertNotNull(pane.getCash());
        assertEquals(cash,pane.getCash());

    }
}
