package LiteSnacks.backend;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import LiteSnacks.backend.UserAccount.UserAccount;

public class ReportsHandler {
    ResourceHandler handler ;
    public ReportsHandler(){
        handler = new ResourceHandler();
    }

    public String getCashesReport(){
        File reportFile = handler.getCashesReportFile();
        String st = "Saved at "+String.valueOf(reportFile);
        try{
            FileWriter writer = new FileWriter(reportFile);
            CashHandler handler = new CashHandler();
            String sr = getStringOfCashesReport(handler.readFile());
            writer.write(sr);
            writer.close();
            showReport(reportFile);

        }catch (Exception e){
            st = "Saved fail";
        }
        return st;

    }
    public String getStringOfCashesReport(List<Cash> cashes){
        String value = "\n These below are cashes available in the Vending machine" + "\n";
        for (Cash cash : cashes){
            value += "------------------------- \n\n";
            if (cash.getValue()>=1){
                int m = (int) cash.getValue();
                value += "  value : $"+m+"\n";
            }else {
                int m = (int) (cash.getValue()*100);
                value += "  value : "+m+"c\n";
            }

            value += "  qty : "+cash.getQty()+"\n\n";

        }
        return value;

    }

    public String getTransactionsReport(){
        File reportFile = handler.getTransactionsReportFile();
        String st = "Saved at "+String.valueOf(reportFile);
        try{
            FileWriter writer = new FileWriter(reportFile);
            List<Transaction> trans = Transaction.getAllTransactions();

            String sr = getStringOfTransactionsReport(trans);
            writer.write(sr);
            writer.close();
            showReport(reportFile);

        }catch (Exception e){
            st = "Saved fail";
        }
        return st;

    }
    public String getStringOfTransactionsReport(List<Transaction> transactions){
        String value = "\n These below are transactions details in the Vending machine" + "\n";
        System.out.println(1);
        for (Transaction each : transactions){

            value += "------------------------- \n\n";
            value += "  Date : "+ each.getTimestamp() + "\n";
            value += "  User : "+ each.getUser()+"\n";
            value += "  Price : "+ each.getAmount() + "\n";
            value += "  Change : "+each.getChange()+"\n";
            value += "  Pay Method : "+each.getMethod()+"\n";
            value += "  Items : \n";
            int index = 1;
            Map<String,List<Double>> map = each.getProducts();
            for(String name: map.keySet()){
                String key = name;
                List<Double>price_qty = map.get(key);
                value +="       "+index+". "+key+" $"+price_qty.get(0)+" x "+price_qty.get(1)+"\n";
                index+=1;
            }
            value+="\n";
            System.out.println(map);


        }
        return value;

    }
    public void showReport(File reportFile){
        try{
            Desktop desktop = Desktop.getDesktop();
            desktop.open(reportFile);

        }catch (Exception e){

        }
    }

    public static void writeUsers() {
        File file = ResourceHandler.getUserInfoFile();
        UserLoginHandler userClass = new UserLoginHandler();
        ArrayList<String> allUsers = new ArrayList<String>();
        for (UserAccount account : userClass.getUsers()) {
            allUsers.add("Username: " + account.getUserName() + ", Role: " + account.getRole());
        }

        try {
            FileWriter writer = new FileWriter(file);
            for (String info : allUsers) {
                writer.write(info+'\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUsers() {
        File file = ResourceHandler.getUserInfoFile();
        if (Desktop.isDesktopSupported()) {
            if (Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                new Thread(() -> {
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
        return (String.format("The report is stored at: %s", file.toString()));
    }
}
