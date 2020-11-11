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
        System.out.println(userFile);
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



    public  boolean checkUser(String username, String password) {
       users = getUsers();

        for (int i = 0; i < users.size(); i++) {
            String nme = users.get(i).getUserName();
            String pass = users.get(i).getPassword();
            if (nme.equals(username) && pass.equals(password)) {
                return true;
            }
        }
        for (int i = 0; i < users.size(); i++) {
            String nme = users.get(i).getUserName();
            String pass = users.get(i).getPassword();
            if (nme.equals(username) && ! pass.equals(password)) {
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

        try{
            PrintWriter writer= new PrintWriter(this.userFile);

            for(int i=0; i<users.size(); i++){

                if(users.get(i).getUserName().equals(name)){

                    System.out.println("Account already Exists. Please change username");
                    //return false;
                }else if(i==users.size()-1){
                    users.add(new UserAccount(name,pass, role));
                    writer.println(name+", "+ pass+", "+ role);
                }

            }
        }catch( FileNotFoundException e){
            e.printStackTrace();
            //return false;
        }
        //return false;

    }
}
