package LiteSnacks.backend;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CashHandlerTest {
    @Test
    public void CashHandlerConstructTest(){
        try{
            CashHandler handler = new CashHandler();
        }catch (Exception e){
            System.out.println("CashHandler construct failed");
        }
    }
    @Test
    public void readFileTest(){
        try{
            CashHandler handler = new CashHandler();
            List<Cash> cashes = handler.readFile();

        }catch (Exception e){

            System.out.println("CashHandler read file failed");
        }
        CashHandler handler = new CashHandler();
        List<Cash> cashes = handler.readFile();

        //assertNotNull(cashes);
        assertEquals(10,cashes.size());

    }
    @Test
    public void freshTest(){


        CashHandler handler = new CashHandler();
        handler.fresh();
        assertNotNull(handler.getcashes());


    }
    @Test
    public void submitTest(){

        CashHandler handler = new CashHandler();
        List<Cash> cashes = handler.readFile();
        assertEquals(10,cashes.size());

        try{
            handler.Submit(cashes);
        }catch (Exception e){
            System.out.println("submit failed");
        }
        cashes = handler.readFile();
        assertEquals(10,cashes.size());



    }

}
