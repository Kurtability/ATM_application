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
        assertEquals(0, t.size());

        Map<String, List<Double>> purchasedProducts = new HashMap<>();
        List<Double> temp = new ArrayList<>();
        temp.add(3.0);
        temp.add(2.0);
        purchasedProducts.put("moop", temp);
        Transaction.addTransaction(purchasedProducts, 6.0, "NA", true);

        t = Transaction.getAllTransactions();
        assertNotNull(t);
        assertEquals(1, t.size());
        assertNotNull(t.get(0));
        assertEquals(1, t.get(0).getProducts().keySet().size());
        assertTrue(t.get(0).getProducts().keySet().contains("moop"));
        assertEquals(3.0, t.get(0).getProducts().get("moop").get(0));
        assertEquals(2.0, t.get(0).getProducts().get("moop").get(1));
        assertEquals(t.get(0).getProducts().get("moop").get(0)*t.get(0).getProducts().get("moop").get(1),
                        t.get(0).getAmount());
        assertEquals("Card", t.get(0).getMethod());
        try {
            assertNotNull(Transaction.generateReport());
        } catch(Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}