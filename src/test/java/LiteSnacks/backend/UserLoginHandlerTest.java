package LiteSnacks.backend;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;


public class UserLoginHandlerTest {

    UserLoginHandler user= new UserLoginHandler();


//    @Test
//    public void addUsertest(){
//        UserLoginHandler user =new UserLoginHandler();
//        assertEquals(false, user.addUser("Adam", "1234", "Customer"));
//        assertEquals(true, user.addUser("Adm", "124", "Seller"));
//    }

    @Test
    public void getUsersTest(){
        UserLoginHandler user=new UserLoginHandler();
        assertEquals("Username is: Adam, Password is: 1234, role is: customer", user.getUsers().get(0));
        assertEquals("Username is: Jones, Password is: 5678, role is: seller", user.getUsers().get(1));
    }

    @Test
    public void getPassWordTest(){
        UserLoginHandler user=new UserLoginHandler();
        assertEquals("1234", user.getPasswords().get(0));
        assertEquals("5678", user.getPasswords().get(1));
    }

    @Test
    public void getUsernametest(){
        assertEquals("Adam", user.getUsernames().get(0));
        assertNotEquals("Adm", user.getUsernames().get(0));

    }

    @Test
    public void checkUserTest(){
        assertEquals(true, user.checkUser("Adam","1234"));
        assertNotEquals("Adm", user.getUsernames().get(0));

    }

}
