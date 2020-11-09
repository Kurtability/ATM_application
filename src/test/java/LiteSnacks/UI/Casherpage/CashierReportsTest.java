package LiteSnacks.UI.Casherpage;

import LiteSnacks.UI.Cashier.CashierReportsScene;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CashierReportsTest {
    @Test
    public void ObjectTest(){
        try{
            CashierReportsScene scene = new CashierReportsScene(640,500,new Stage());
            scene.setScene();
        }catch (Exception e){
            System.out.println("cashierReportScene construct failed");
        }
    }
}
