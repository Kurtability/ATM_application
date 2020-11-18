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
    private PrintWriter writer;

    public UserLoginHandler() {
        this.userFile = ResourceHandler.getUserFile();
        System.out.println(userFile);
    }

    public File getUserFile(){
        return this.userFile;
    }


    public List<String> getUsernames(){

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


            password = details[1].strip();
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

            role = details[2].strip();
            roles.add(role);
        }
        sc.close();
        return roles;
    }


    public List<UserAccount> getUsers(){
        users = new ArrayList<>();
        int i = 0;
        while (i < getUsernames().size() && i <getPasswords().size() && i< getRoles().size()){
            user = new UserAccount(getUsernames().get(i), getPasswords().get(i), getRoles().get(i));
            users.add(user);
            i ++;
        }
        return users;
    }


    // check normal user, not special roles
    public  boolean checkUser(String username, int passwordHash) {
       users = getUsers();

        for (int i = 0; i < users.size(); i++) {
            String nme = users.get(i).getUserName();
            int pass = users.get(i).getPassword().hashCode();

            if (nme.equals(username) && pass == (passwordHash)) {
                return true;
            }
        }
        for (int i = 0; i < users.size(); i++) {
            String nme = users.get(i).getUserName();
            int pass = users.get(i).getPassword().hashCode();
            if (nme.equals(username) && !(pass == passwordHash)) {
                System.out.println("Wrong Password");
                return false;
            }else if (i == users.size() - 1) {
                System.out.println("Account does not exist");
                return false;
            }
        }
        return false;
    }



    public void addUser (String name, String pass, String role){
        users = getUsers();
        System.out.println(users);
        user = new UserAccount(name,pass,role);
        try{
            writer= new PrintWriter(new FileOutputStream(this.userFile,true));
            writer.println();
            boolean flag = false;
            for(int i=0; i< users.size(); i++){
                if(users.get(i).getUserName().equals(name)){
                    System.out.println("Account already Exists. Please change username");
                    flag = true;
                }
            }
            if (!flag){
                users.add(user);
                writer.print(  name + ", "+ pass + ", "+ role);
                System.out.println(users);
            }
            writer.close();
        }catch( FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public void addCustomer(String name, String pass){
        String role = "customer";
        addUser(name,pass,role);
        System.out.println("added customer");
    }
}
