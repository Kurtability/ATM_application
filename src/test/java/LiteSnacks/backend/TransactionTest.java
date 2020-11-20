package LiteSnacks.backend;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class TransactionTest {

    @Test public void addAndGetAllTransactions() {
        ResourceHandlerTest.deleteDirectory(new File(ResourceHandler.root));
        List<Transaction> t = Transaction.getAllTransactions();
        assertNotNull(t);
        assertEquals(6, t.size());

        Map<String, List<Double>> purchasedProducts = new HashMap<>();
        List<Double> temp = new ArrayList<>();
        temp.add(3.0);
        temp.add(2.0);
        purchasedProducts.put("moop", temp);
        Transaction.addTransaction(purchasedProducts, 6.0, "NA", true);

        t = Transaction.getAllTransactions();
        assertNotNull(t);
        assertEquals(7, t.size());
        assertNotNull(t.get(0));
        assertEquals(5, t.get(0).getProducts().keySet().size());
        try {
            assertNotNull(Transaction.generateReport());
        } catch(Exception e) {
            e.printStackTrace();
            fail();
        }
        ResourceHandlerTest.deleteDirectory(new File(ResourceHandler.root));
    }

    
}