package LiteSnacks.backend;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class ReportsHandlerTest {
    @Test
    public void getCashesReportTest(){
        String test = "";
        try{
            ReportsHandler handler = new ReportsHandler();
            test = handler.getCashesReport();
        }catch (Exception e){
            System.out.println("get cashes Report fail");
        }
        assertTrue(test.length()>0);
    }
    @Test
    public void getTransactionsReportTest(){
        String test = "";
        try{
            ReportsHandler handler = new ReportsHandler();
            test = handler.getTransactionsReport();
        }catch (Exception e){
            System.out.println("get cashes Report fail");
        }
        assertTrue(test.length()>0);
    }
    @Test
    public void getStringTest(){
        String test = "";
        try{
            ReportsHandler handler = new ReportsHandler();
            Map<String,List<Double>> map = new HashMap<>();
            map.put("coca",new ArrayList<>(Arrays.asList(1.0,2.0)));
            List<Transaction> tr = new ArrayList<>(Arrays.asList(new Transaction(2,map, new Timestamp(System.currentTimeMillis()),"me","null","card")));
            test = handler.getStringOfTransactionsReport(tr);
        }catch (Exception e){
            System.out.println("get cashes Report fail");
        }
        assertTrue(test.length()>0);
    }
}
