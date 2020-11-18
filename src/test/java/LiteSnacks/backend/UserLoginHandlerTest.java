package LiteSnacks.backend;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;


public class UserLoginHandlerTest {

    UserLoginHandler user= new UserLoginHandler();


    @Test
    public void getPassWordTest(){
        assertEquals("1234", user.getPasswords().get(0));
        assertEquals("5678", user.getPasswords().get(1));
    }

    @Test
    public void getRoleTest(){
        assertEquals("cashier", user.getRoles().get(0));
        assertEquals("seller", user.getRoles().get(1));
    }


    @Test
    public void getUsernametest(){
        assertEquals("Adam", user.getUsernames().get(0));
        assertEquals("Jones", user.getUsernames().get(1));
        assertNotEquals("Adm", user.getUsernames().get(0));

    }

    @Test
    public void checkUserTest(){
        assertEquals(true, user.checkUser("Adam","1234".hashCode()));
        assertEquals(true, user.checkUser("Jones","5678".hashCode()));
        assertNotEquals(true, user.checkUser("Adem","134234".hashCode()));



    }

}
