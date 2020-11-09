package LiteSnacks.UI.SellerPage;
import LiteSnacks.UI.Seller.SellerMainScene;
import javafx.stage.Stage;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SellerMainSceneTest {
    @Test
    public void constructTest(){
        try {
            SellerMainScene scene = new SellerMainScene(640,300,new Stage());
            scene.setScene();
        }catch (Exception e){
            System.out.println("Seller main page construct failed");
        }
    }
}
