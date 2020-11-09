package LiteSnacks.backend;

import LiteSnacks.backend.UserAccount.UserAccount;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserLoginHandler {

    private final File userFile;

    List<UserAccount> users;
    private UserAccount user;

    public UserLoginHandler() {
        this.userFile = ResourceHandler.getUserFile();
    }


    public List<String> getUsername(){

        Scanner sc;
        try {
            sc = new Scanner(this.userFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        List<String> usernames = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] details;
            details = line.split(",");

            usernames.add(details[0]);
        }
        sc.close();
        return usernames;
    }



    public List<String> getPasswords(){

        List<String> passwords = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(this.userFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        while (sc.hasNextLine()) {
            String password = null;
            String line = sc.nextLine();
            String[] details;
            details = line.split(",");

            password = details[1];
            passwords.add(password);
        }
        sc.close();
        return passwords;
    }

    public List<String> getRoles(){

        List<String> roles = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(this.userFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        while (sc.hasNextLine()) {
            String role = null;
            String line = sc.nextLine();
            String[] details;
            details = line.split(",");

            role = details[2];
            roles.add(role);
        }
        sc.close();
        return roles;
    }


    public List<UserAccount> getUsers(){
        List<UserAccount> users = new ArrayList<>();

        int i = 0;
        while (i <getUsername().size()){
            user = new UserAccount(getUsername().get(i), getPasswords().get(i), getRoles().get(i));
            users.add(user);
        }
        return users;
    }




//    /*Method to veriy User exists
//    * Looks into User.txt to see if the username and password exists
//    * Finds if the User is Seller/ Cashier/ Customer
//    * */
//    public static boolean checkUser(String username, String password){
//         boolean selllerAccess=false;
//         boolean customerAccess=false;
//         boolean CashierAccess=false;
//        try{
//            Scanner scan =new Scanner(new File(fle));
//            String[] details=null;
//            String AccessRights=null;
//            while (scan.hasNextLine()){
//                String line=scan.nextLine();
//                if(line.contains(username) && line.contains(password) ){
//                    details=line.split(",");
//                    if(details[1].equals(username) && details[2].equals(password)){
//                        if(details[0].equals("Seller")){selllerAccess=true;}
//                        else if (details[0].equals("Customer")){customerAccess=true;}
//                        else if (details[0].equals("Cashier")){CashierAccess=true;}
//                        return true;
//                    }
//                }
//            }
//
//        }
//        catch(FileNotFoundException e){
//            System.out.println("An error occured");
//        }
//
//        return false;
//    }
//
//
//    /*
//    * writes to the User.txt new Users username and Password
//    *  */
//
//    public static boolean addUser (String name, String pass){
//        try{
//            FileWriter writer= new FileWriter(new File(fle));
//            if(checkUser(name, pass)==false){
//                writer.write(name+","+"pass");
//                return true;
//            }else{
//                System.out.println("User already exists");
//            }
//        }
//        catch(FileNotFoundException e){
//            System.out.println("An error occured");
//        }
//        catch(IOException e){
//            System.out.println("An error Occured");
//        }
//        return false;
//    }

}
