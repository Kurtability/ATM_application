package LiteSnacks.backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CashTest {
    @Test
    public void CashObjectTest() {
        Cash object = new Cash(1, 10, "not_picture");
        assertNotNull(object);
    }

    @Test
    public void getMethodTest() {
        Cash object = new Cash(1, 10, "not_picture");
        assertEquals(1, object.getValue());
        assertEquals(10, object.getQty());
        assertEquals("not_picture", object.getImg());
    }

    @Test
    public void TestModifyQty() {
        Cash object = new Cash(1, 10, "not_picture");
        // normal
        assertTrue(object.modifyqty(1));
        assertEquals(11, object.getQty());
        assertTrue(object.modifyqty(-1));
        assertEquals(10, object.getQty());

        // zero qty
        Cash object2 = new Cash(1, 0, "not_picture");
        assertFalse(object2.modifyqty(-1));
        assertEquals(0, object2.getQty());
        assertTrue(object2.modifyqty(1));
        assertEquals(1, object2.getQty());

    }




}
