package LiteSnacks.backend;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PayCashTest {

    @Test
    public void submitPaymentTest() {
        double[][] sufficientCash = {{50.0, 5}, {20.0, 5}, {10, 5}, {5, 5}, {2, 5}, {1, 5}, {0.5, 5}, {0.2, 5}, {0.1, 5}, {0.05, 5}};
        PayCash.setCashReserves(toList(sufficientCash));

        double[][] customerPayment = {{20, 1}};
        assertEquals("$2.0", PayCash.submitPayment(toList(customerPayment), 18));
        assertEquals("Insufficient Change. Please try another payment method", PayCash.submitPayment((toList(customerPayment)), 21));
        assertEquals("$2.0 $2.0", PayCash.submitPayment(toList(customerPayment), 16));
        assertEquals("Insufficient Change. Please try another payment method", PayCash.submitPayment((toList(customerPayment)), 0.21));
        assertEquals("$0", PayCash.submitPayment((toList(customerPayment)), 20));

        customerPayment = new double[][] {{-1, 2}};
        assertEquals("Something went wrong with 'addCash()", PayCash.submitPayment((toList(customerPayment)), 20));
        customerPayment = new double[][] {{2, -1}};
        assertEquals("Something went wrong with 'addCash()", PayCash.submitPayment((toList(customerPayment)), 20));

    }

    private List<Cash> toList(double[][] newFile) {
        List<Cash> testCash = new ArrayList<>();
        Cash toAdd;
        for(int i=0; i<newFile.length; i++){
            toAdd = new Cash(newFile[i][0], (int)newFile[i][1], "cash.jpg");
            testCash.add(toAdd);
        }
        return testCash;
    }


}
