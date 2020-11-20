package LiteSnacks.backend;


import LiteSnacks.backend.UserAccount.UserAccount;
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

    @Test
    public void getUserTest(){
        List<UserAccount> usrs= user.getUsers();
        assertEquals("Adam",usrs.get(0).getUserName());
        assertEquals("1234",usrs.get(0).getPassword());
        assertEquals("5678",usrs.get(1).getPassword());
        assertEquals("Jones",usrs.get(1).getUserName());
        assertNotEquals("Kpadasd",usrs.get(0).getUserName());

    }


    @Test
    public void addUserTest(){
        user.addUser("JustCheckin","trying","hahaha");
        List<UserAccount> usrs=user.getUsers();
//        int i=usrs.size()-1;
        //checks if added to the list
        boolean pass=false;
        for(int i=0; i<usrs.size(); i++){
            if(usrs.get(i).getUserName().equals("JustCheckin")  && usrs.get(i).getPassword().equals("trying")){
                pass=true;
            }
        }
        assertTrue(pass, "Added user not found");

    }

    @Test
    public void addCustomerTest(){
        user.addCustomer("checkinCustomer", "check");
        List<UserAccount> usrs=user.getUsers();
        boolean pass=false;
        for(int i=0; i<usrs.size(); i++){
            if(usrs.get(i).getUserName().equals("checkinCustomer")  && usrs.get(i).getPassword().equals("check") && usrs.get(i).getRole().equals("customer")){
                pass=true;
            }
        }
        assertTrue(pass, "Added user not found");

    }

    @Test
    public void setCurrentUserTest(){
        List<UserAccount> usrs=user.getUsers();
        UserAccount first=usrs.get(2);

    }


}
