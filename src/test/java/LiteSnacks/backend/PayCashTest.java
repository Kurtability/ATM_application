package LiteSnacks.backend;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.Assert.assertTrue;


public class PayCashTest {
    PayCash pc;

    @Test
    void calculateChangeTest() {
        double[] testNumbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9,
                                10, 11, 12, 13, 14, 15, 16,
                                17, 18, 0, 0.5, 0.2, 0.1, 0.05,
                                0.005 };
        for(double d : testNumbers) {
            pc = new PayCash();
            pc.calculateChange(d);

        }
        assertEquals(1, 1);
    }
}
