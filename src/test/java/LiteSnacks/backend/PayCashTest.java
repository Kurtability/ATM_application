package LiteSnacks.backend;
import LiteSnacks.UI.Cash;
import org.checkerframework.checker.units.qual.C;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PayCashTest {

    @Test
    void calculateAddChangeTest() {
        double[][] sufficientCash = {{50.0, 5}, {20.0, 5}, {10, 5}, {5, 5}, {2, 5}, {1, 5}, {0.5, 5}, {0.2, 5}, {0.1, 5}, {0.05, 5}};
        PayCash.setCashReserves(toList(sufficientCash));

        double[][] toAdd = {{50, 1}};

        assertTrue(PayCash.addCash(toList(toAdd)));

        Cash cashObj = null;
        boolean found = false;
        for(int i=0; !found && i<PayCash.getCashReserves().size(); i++){
            cashObj = PayCash.getCashReserves().get(i);
            if(cashObj.getValue() == 50.0)
                found = true;
        }

        if(cashObj != null)
            assertEquals(6, cashObj.getQty());
        else
            fail();

        double[][] anotherToAdd = {{50, 1}, {20, 3}};

        assertTrue(PayCash.addCash(toList(anotherToAdd)));

        for(int i=0; i<PayCash.getCashReserves().size(); i++){
            cashObj = PayCash.getCashReserves().get(i);
            if(cashObj.getValue() == 50.0)
                assertEquals(7, cashObj.getQty());
            else if(cashObj.getValue() == 20)
                assertEquals(8, cashObj.getQty());
        }

        double[][] failToAdd = { {-1, -1} };

        assertFalse(PayCash.addCash(toList(failToAdd)));

        found = false;
        for(int i=0; !found && i<PayCash.getCashReserves().size(); i++){
            cashObj = PayCash.getCashReserves().get(i);
            if(cashObj.getValue() == -1)
                found = true;
        }
        assertFalse(found);
    }

    @Test
    void calculateChangeTest() {

        // Bountiful money
        double[][] sufficientCash = {{50.0, 5}, {20.0, 5}, {10, 5}, {5, 5}, {2, 5}, {1, 5}, {0.5, 5}, {0.2, 5}, {0.1, 5}, {0.05, 5}};
        PayCash.setCashReserves(toList(sufficientCash));

        double[] passRequiredChange = {0, 1, 3, 4, 1.10, 1.05, 0.05, 0.2, 0.25};
        String[] passResults = {"$0", "$1.0", "$2.0 $1.0", "$2.0 $2.0", "$1.0 $0.1", "$1.0 $0.05", "$0.05", "$0.2", "$0.2 $0.05"};

        for(int i = 0; i < passRequiredChange.length; i++) {
            assertEquals(passResults[i], PayCash.calculateChange(passRequiredChange[i]));
        }

        // Sparse cash
        double[][] sparseCash = {{50.0, 1}, {20.0, 1}, {10, 2}, {5, 1}, {2, 1}, {1, 1}, {0.5, 1}, {0.2, 1}, {0.1, 1}, {0.05, 1}};
        PayCash.setCashReserves(toList(sparseCash));
        double[] passSparseChange = { 1, 0.15, 40};
        String[] passSparseResults = { "$1.0", "$0.1 $0.05", "$20.0 $10.0 $10.0" };
        for(int i = 0; i < passSparseChange.length; i++) {
            assertEquals(passSparseResults[i], PayCash.calculateChange(passSparseChange[i]));
        }

        // Fail cases
        double[] failures = { -1, 0.21 };
        for(int i=0; i<failures.length; i++)
            assertEquals("Insufficient Change. Please try another payment method", PayCash.calculateChange(failures[i]));
    }

    @Test
    public void submitPaymentTest() {
        double[][] sufficientCash = {{50.0, 5}, {20.0, 5}, {10, 5}, {5, 5}, {2, 5}, {1, 5}, {0.5, 5}, {0.2, 5}, {0.1, 5}, {0.05, 5}};
        PayCash.setCashReserves(toList(sufficientCash));

        double[][] customerPayment = {{20, 1}};
        assertEquals("$2.0", PayCash.submitPayment(toList(customerPayment), 18));
        assertEquals("Insufficient Change. Please try another payment method", PayCash.submitPayment((toList(customerPayment)), 21));

        customerPayment = new double[][] {{-1, 2}};
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
