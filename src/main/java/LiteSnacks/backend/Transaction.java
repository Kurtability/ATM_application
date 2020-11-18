package LiteSnacks.backend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transaction {

    public static void addTransaction(Map<String, List<Double>> transaction, double amount) {
        String user = "anon";
        File out = ResourceHandler.getTransactionFile();
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(out, true));
            
            pw.println("_");
            pw.println(new Timestamp(System.currentTimeMillis()));
            pw.println(amount);
            pw.println(user);
            
            for(String prod: transaction.keySet())
                pw.println(prod + "," + transaction.get(prod).get(0) + "," +
                    transaction.get(prod).get(1));
            
            pw.close();
                    
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /*
    public static void main(String[] args) {
        HashMap<String, List<Double>> hm = new HashMap<>();
        List<Double> l = new ArrayList<>();
        l.add(2.0);
        l.add(4.5);
        hm.put("tweaky", l);
        addTransaction(hm, 9.0);
    }
    */
}