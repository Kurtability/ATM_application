package LiteSnacks.backend;

import LiteSnacks.backend.UserAccount.UserAccount;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.naming.spi.ResolveResult;

public class UserLoginHandler {

    private final File userFile;

    List<UserAccount> users;
    private static UserAccount currentUser;
    private PrintWriter writer;

    public UserLoginHandler() {
        this.userFile = ResourceHandler.getUserFile();
        System.out.println(userFile);
    }

    public static UserAccount getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserAccount givenUser) {
        currentUser = givenUser;
    }

    public File getUserFile() {
        return this.userFile;
    }

    public List<String> getUsernames() {

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

    public List<String> getPasswords() {

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
            if (line.equals("")){
                continue;
            }

            String[] details;
            details = line.split(",");

            password = details[1].strip();
            passwords.add(password);
        }
        sc.close();
        return passwords;
    }

    public List<String> getRoles() {

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

    public List<UserAccount> getUsers() {
        users = new ArrayList<>();
        int i = 0;
        UserAccount user;
        while (i < getUsernames().size() && i < getPasswords().size() && i < getRoles().size()) {
            user = new UserAccount(getUsernames().get(i), getPasswords().get(i), getRoles().get(i));
            users.add(user);
            i++;
        }
        return users;
    }

    // check normal user, not special roles
    public boolean checkUser(String username, int passwordHash) {
        users = getUsers();

        for (int i = 0; i < users.size(); i++) {
            String nme = users.get(i).getUserName();
            int pass = users.get(i).getPassword().hashCode();

            if (nme.equals(username) && pass == (passwordHash)) {
                for (UserAccount aUser : users) {
                    if (aUser.getUserName().equals(username)) {
                        currentUser = aUser;
                    }
                }
                return true;
            }
        }
        for (int i = 0; i < users.size(); i++) {
            String nme = users.get(i).getUserName();
            int pass = users.get(i).getPassword().hashCode();
            if (nme.equals(username) && !(pass == passwordHash)) {
                System.out.println("Wrong Password");
                currentUser = null;
                return false;
            } else if (i == users.size() - 1) {
                System.out.println("Account does not exist");
                currentUser = null;
                return false;
            }
        }
        return false;
    }

    public boolean removeUser(String name) {
        // remove the account from list
        List<UserAccount> listOfUsers = getUsers();
        FileWriter w;
        try {
            w = new FileWriter(ResourceHandler.getUserFile());
            UserAccount u;
            for(int i=0; i<listOfUsers.size(); i++) {
                u = listOfUsers.get(i);
                if(!u.getUserName().equals(name)) {
                    if(i < listOfUsers.size() - 1) {
                        w.write(String.format("%s, %s, %s%n", u.getUserName(), u.getPassword(), u.getRole()));
                    }
                    else {
                        w.write(String.format("%s, %s, %s", u.getUserName(), u.getPassword(), u.getRole()));
                    }
                }
            }
            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void addUser(String name, String pass, String role) {
        List<UserAccount> listOfUsers = getUsers();
        FileWriter w;
        try {
            w = new FileWriter(ResourceHandler.getUserFile());
            listOfUsers.add(new UserAccount(name, pass, role));
            UserAccount u;
            for (int i = 0; i < listOfUsers.size(); i++) {
                u = listOfUsers.get(i);
                if (i < listOfUsers.size() - 1) {
                    w.write(String.format("%s, %s, %s%n", u.getUserName(), u.getPassword(), u.getRole()));
                } else {
                    w.write(String.format("%s, %s, %s", u.getUserName(), u.getPassword(), u.getRole()));
                }
            }
            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCustomer(String name, String pass) {
        String role = "customer";
        addUser(name, pass, role);
        System.out.println("added customer");
    }  
}
