package LiteSnacks.backend;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;


public class UserLoginHandlerTest {

    @Test
    public void getUsertest(){
        UserLoginHandler user= new UserLoginHandler();
        assertEquals(true, user.checkUser("Adam", "1234"));
        assertEquals(false, user.checkUser("Adm", "1234"));

    }

    @Test
    public void addUsertest(){
        UserLoginHandler user =new UserLoginHandler();
        assertEquals(false, user.addUser("Adam", "1234", "Customer"));
        assertEquals(true, user.addUser("Adm", "124", "Seller"));
    }

    @Test
    public void getPassWordTest(){
        UserLoginHandler user=new UserLoginHandler();
        assertEquals("1234", user.getPasswords().get(0));
        assertEquals("2312", user.getPasswords().get(1));
    }

    @Test
    public void getUsernametest(){
        UserLoginHandler user= new UserLoginHandler();
        assertEquals("Adam", user.getUsername().get(0));
        assertNotEquals("Adm", user.getUsername().get(0));

    }





}
