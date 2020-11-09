package LiteSnacks.backend;
import java.io.*;
import java.util.Scanner;


public class Userlogin {
    final static String fle="src"+ File.separator +"main"+ File.separator +"resources"+ File.separator +"Users.txt";


    /*Method to veriy User exists
    * Looks into User.txt to see if the username and password exists
    * Finds if the User is Seller/ Cashier/ Customer
    * */
    public static boolean checkUser(String username, String password){
         boolean selllerAccess=false;
         boolean customerAccess=false;
         boolean CashierAccess=false;
        try{
            Scanner scan =new Scanner(new File(fle));
            String[] details=null;
            String AccessRights=null;
            while (scan.hasNextLine()){
                String line=scan.nextLine();
                if(line.contains(username) && line.contains(password) ){
                    details=line.split(",");
                    if(details[1].equals(username) && details[2].equals(password)){
                        if(details[0].equals("Seller")){selllerAccess=true;}
                        else if (details[0].equals("Customer")){customerAccess=true;}
                        else if (details[0].equals("Cashier")){CashierAccess=true;}
                        return true;
                    }
                }
            }

        }
        catch(FileNotFoundException e){
            System.out.println("An error occured");
        }

        return false;
    }


    /*
    * writes to the User.txt new Users username and Password
    *  */

    public static boolean addUser (String name, String pass){
        try{
            FileWriter writer= new FileWriter(new File(fle));
            if(checkUser(name, pass)==false){
                writer.write(name+","+"pass");
                return true;
            }else{
                System.out.println("User already exists");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("An error occured");
        }
        catch(IOException e){
            System.out.println("An error Occured");
        }
        return false;
    }

}
